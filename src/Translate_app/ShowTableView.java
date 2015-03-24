package Translate_app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import org.json.JSONObject;

public class ShowTableView extends JFrame{
	Model theModel;
	TranslateView theView;
	private JLabel[] labelChar;
	private JPanel rootPanel;
	private JPanel topPanel;
	private JTable showTable;
	private TableModel tableModel;
	private Object[][] tableData;	
	private Object[][] init = {{"", "", "", "", ""}};
	private String[] columns = {"單字","翻譯","詞性","頻率","反向翻譯"};
	private TableRowSorter<TableModel> sorter ;
	private boolean flag = false;
	private JTextField filterText ;
	 JButton filterBtn ;
	public ShowTableView(Model theModel,TranslateView theView){
		this.theModel = theModel;
		this.theView = theView;
		intiUI();
	}
	private void intiUI() {
		// TODO Auto-generated method stub		
		rootPanel = new JPanel();
		GroupLayout group = new GroupLayout(rootPanel);
		rootPanel.setLayout(group);
		
		topPanel = new JPanel();		
		GridLayout grid = new GridLayout(2,13);
		topPanel.setLayout(grid);
		topPanel.setBackground(new Color(56,78,119));
		topPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
		
		labelChar = new JLabel[26];		
		for(int i = 0 ; i < labelChar.length; i++){
			int temp = i+65;
			labelChar[i] = new JLabel(Character.toString((char)temp));			
			labelChar[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			labelChar[i].setPreferredSize(new Dimension(30,30));
			labelChar[i].setForeground(new Color(221,223,229));
			topPanel.add(labelChar[i]);			
		}
		
		JSeparator separator = new JSeparator();
        separator.setForeground(Color.gray);
        filterText = new JTextField("");
        filterText.setBorder(BorderFactory.createLineBorder(Color.darkGray,2));
        filterText.setFont(new Font("Cambria", Font.PLAIN, 20));
        filterBtn = new JButton("尋找");
    	filterBtn.setFocusPainted(false);    	
    	filterBtn.setBackground(new Color(224,233,205));
    	filterBtn.setFont(new Font("細明體", Font.PLAIN, 20));    	
        setTableData(init);
        tableModel = new TableModel();               
        showTable = new JTable(tableModel){
        	public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
				Component c = super.prepareRenderer(renderer, row, column);
				String versionVal = showTable.getValueAt(row, 1).toString();			
				c.setFont(new Font("細明體", Font.CENTER_BASELINE, 14));				
				if(isRowSelected(row)){
					c.setBackground(new Color(255,255,255));
				}else{
					c.setBackground(new Color(224,233,205));
				}
        		return c;        		
        	}
        };
        showTable.getTableHeader().setFont(new Font("細明體", Font.BOLD, 14));
        showTable.getTableHeader().setForeground(new Color(24,49,79));
        showTable.getTableHeader().setBackground(new Color(139,190,178));
        
        showTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);        
        showTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        showTable.setBackground(new Color(224,233,205));
        showTable.setGridColor(new Color(0,0,0));
        showTable.setShowHorizontalLines(false); 
        TableColumn column2 = showTable.getColumnModel().getColumn(2);
        TableColumn column3 = showTable.getColumnModel().getColumn(3);
		TableColumn column4 = showTable.getColumnModel().getColumn(4);
		column2.setPreferredWidth(50);
		column3.setPreferredWidth(50);
	    column4.setPreferredWidth(200);
	    showTable.setPreferredScrollableViewportSize(new Dimension(500,300));
	    showTable.setFillsViewportHeight(true);
	    sorter = new TableRowSorter<TableModel>(tableModel);
		
	    showTable.setRowSorter(sorter);
	    sorter.setSortable(1, true);
	    JScrollPane scroll = new JScrollPane(showTable); 		
		scroll.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
		scroll.setBackground(new Color(224,233,205));		
		group.setAutoCreateContainerGaps(true);
		group.setAutoCreateGaps(true);
		
		group.setHorizontalGroup(				
				group.createParallelGroup()
					.addGroup(
						group.createSequentialGroup()	
							.addComponent(topPanel)
				)
				.addGroup(
            		group.createSequentialGroup()
                	.addComponent(separator)                	                	
                )
                .addGroup(
            		group.createSequentialGroup()
                	.addComponent(filterText)
                	.addComponent(filterBtn)
                )
				.addGroup(
						group.createSequentialGroup()
						.addComponent(scroll)
				)
		);
		group.setVerticalGroup(
				group.createSequentialGroup()
					.addGroup(
							group.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(topPanel)
					)
					.addGroup(
							group.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(separator)            		
					)
					.addGroup(
							group.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(filterText)
							.addComponent(filterBtn)            		
					)
					.addGroup(
							group.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(scroll)
					)
		);
		JLabel label = new JLabel("Copyright\u00a92015 by Eddie Chiu   ");
	    label.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
	    
	    label.setBackground(Color.lightGray);
	    label.setHorizontalAlignment(JLabel.RIGHT);
		rootPanel.setBackground(Color.lightGray);
	    add(rootPanel,BorderLayout.CENTER);
		add(label,BorderLayout.SOUTH);
		pack();
		setTitle("單字表 - "+theModel.getFileName());        
		setLocation(theView.getX()+theView.getWidth()+25,theView.getY()-100);
		setResizable(false);
        setAlwaysOnTop(true);
	}
	public void addLabelCharListener(MouseListener listenForLabelChar){
		for(int i = 0;i <26; i++){
			labelChar[i].addMouseListener(listenForLabelChar);
		}
	}
	public void addFilterBtnListener(ActionListener listenForFilterBtn){
		filterBtn.addActionListener(listenForFilterBtn);
	}
	public String getFilterText(){
		return filterText.getText().toString();
	}
	public void setSorter(RowFilter<? super TableModel, ? super Integer> s){
		sorter.setRowFilter(s);
	}
	public void setLabelCharColor(int x){
		for(int i = 0;i <26; i++){
			labelChar[i].setForeground(new Color(221,223,229));
		}
		labelChar[x-65].setForeground(Color.GREEN);
	}
	public void setTableData(Object[][] obj){		
		if(flag == true){
			tableData = obj;			
			tableModel.fireTableDataChanged();
			//tableModel.fireTableRowsInserted(0,obj.length-1);
		}else{
			tableData = obj;				
		}		
		flag = true;
	}	
	public void displayInfo(String error , int info ){
		JOptionPane.showMessageDialog(this, error, "提示訊息", info);
	}
	class TableModel extends AbstractTableModel  {//		
		  public int getColumnCount() {return columns.length;}
		  public int getRowCount() {return tableData.length;}
		  public Object getValueAt(int row, int col) {return tableData[row][col];}
		  public String getColumnName(int col) {return columns[col];} 		 
		  public boolean isCellEditable(int row,int col) {return false;
		}		  		  	 
	}
}
