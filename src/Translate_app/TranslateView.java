package Translate_app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class TranslateView extends JFrame{
	private JTextField sourceBox ;
	private JButton translateBtn ;
	private JButton saveBtn ;
	private DefaultListModel listModel1,listModel2,listModel3;
	private static JList resultBox, posBox, dictBox;
	private JPanel panel;
	private boolean disableResultFlag;
	public TranslateView(){
		initUI();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		panel = new JPanel();
        GroupLayout gl = new GroupLayout(panel);
        panel.setLayout(gl);
		
        sourceBox = new JTextField("");
		translateBtn = new JButton("翻譯");
		saveBtn = new JButton("儲存");
          
		translateBtn.setFocusPainted(false);
		translateBtn.setContentAreaFilled(false);        
		saveBtn.setFocusPainted(false);
		saveBtn.setContentAreaFilled(false);
        
		listModel1 = new DefaultListModel();
        listModel2 = new DefaultListModel();
        listModel3 = new DefaultListModel();

        resultBox = new JList(listModel1);
		posBox = new JList(listModel2);
		dictBox = new JList(listModel3);
		//saveBtn.setEnabled(false);
        JLabel label = new JLabel("Copyright\u00a92015 by Eddie Chiu   ");
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));//Brush Script MT
        label.setBackground(Color.lightGray);
        Font font_en = new Font("Andalus", Font.PLAIN, 20);
		Font font_list = new Font("細明體", Font.PLAIN, 14);
		Font font_tw = new Font("細明體", Font.PLAIN, 20);
		sourceBox.setFont(font_en);
		translateBtn.setFont(font_tw);
		resultBox.setFont(font_tw);
		saveBtn.setFont(font_tw);
		//set list font
		posBox.setFont(font_list);
		dictBox.setFont(font_list);
		gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
               
        //resultBox.setSelectionModel(new DisabledItemSelectionModel());
        //resultBox.setCellRenderer(new DisabledItemListCellRenderer());
               
        sourceBox.setBorder(BorderFactory.createLineBorder(Color.darkGray,2));        
        resultBox.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        posBox.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        dictBox.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));           
        
        JScrollPane scroll_1 = new JScrollPane(resultBox);
        JScrollPane scroll_2 = new JScrollPane(posBox);
        JScrollPane scroll_3 = new JScrollPane(dictBox);
        
        sourceBox.setPreferredSize(new Dimension(150,35));        
        scroll_1.setPreferredSize(new Dimension(150,45));
        scroll_2.setPreferredSize(new Dimension(50,100));
        scroll_3.setPreferredSize(new Dimension(250,120));
        //平行
        gl.setHorizontalGroup(
    		gl.createParallelGroup() //並行
        		.addGroup( // 1
	        		gl.createSequentialGroup() //序列
	        			.addGroup(
	        				gl.createParallelGroup(GroupLayout.Alignment.CENTER)
	        				.addComponent(sourceBox)
	        				.addComponent(scroll_1)
	        		)               
	                	.addGroup(
	                		gl.createParallelGroup(GroupLayout.Alignment.CENTER)
	                		.addComponent(translateBtn)
	                		.addComponent(saveBtn)
	                )	                	               
               )
               .addGroup( // 2
            		gl.createSequentialGroup()
                	.addComponent(scroll_2)                	
                	.addComponent(scroll_3)
                )                    
        );
        //垂直
        gl.setVerticalGroup(
    		gl.createSequentialGroup() //序列
    			.addGroup(// 1
	        		gl.createSequentialGroup()
	        			.addGroup(
	        				gl.createParallelGroup(GroupLayout.Alignment.CENTER)
	        				.addComponent(sourceBox)
	        				.addComponent(translateBtn)
	        			)
	                	.addGroup(
	                		gl.createParallelGroup(GroupLayout.Alignment.LEADING)
	                		.addComponent(scroll_1)
	                		.addComponent(saveBtn)
	                	)	                	
    			)
    			.addGroup(// 2
            		gl.createParallelGroup(GroupLayout.Alignment.CENTER)
            		.addComponent(scroll_2)
                	.addComponent(scroll_3)
            	)                                                                 
        );
        
        label.setHorizontalAlignment(JLabel.RIGHT);
        add(panel, BorderLayout.CENTER);
        add(label,BorderLayout.SOUTH);
        panel.setBackground(Color.lightGray);
        pack();
        setTitle("Translator App 1.0");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
	}
	
	public String getSourceValue(){
		return sourceBox.getText().toString();
	}
	public String getPosBoxValue(){
		return posBox.getSelectedValue().toString();
	}
	public String getDictBoxValue(){
		return dictBox.getSelectedValue().toString();
	}
	public String getResultValue(){
		return resultBox.getSelectedValue().toString();
	}
	//User Pressed Button
	void addTranslateBtnListener(ActionListener listenForTranslateBtn){
		translateBtn.addActionListener(listenForTranslateBtn);
	}
	
	//User Pressed Enter
	void addSourceBoxListener(KeyListener listenForSourceBox){
		sourceBox.addKeyListener(listenForSourceBox);
	}	
	//User DoubleClicked posBox List  
	void addPosBoxListener(MouseListener listenForPosBox){
		posBox.addMouseListener(listenForPosBox);
	}
	void addSaveBtnListener(ActionListener listenForSaveBtn){
		saveBtn.addActionListener(listenForSaveBtn);
	}
	//clean All List 
	public void cleanListData(){
		listModel1.clear();
		listModel2.clear();
		listModel3.clear();
	}	
	//Refresh resultBox Data
	public void setResultBox(String getTrans){		
		listModel1.addElement(getTrans);		
	}
	//Refresh posBox Data
	public void setPosBox(ArrayList<String> listPos){		
		for(int i = 0; i < listPos.size(); i++ ){
			listModel2.addElement(listPos.get(i));
		}
	}
	//Refresh dictBox Data
	public void setDictBox(List<List<String>> dicts){		
		for(int i = 0; i < dicts.get(0).size(); i++ ){
			listModel3.addElement(dicts.get(0).get(i));
		}
	}
	public int getPosIndex(){
		return posBox.getSelectedIndex();
	}
	public void displayInfo(String error){
		JOptionPane.showMessageDialog(this, error, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	//Form posBox item refresh dictBox data
	public void changeDictBox(ArrayList ary){
		listModel3.clear();
		for(int i = 0; i < ary.size(); i++){
			listModel3.addElement(ary.get(i));
		}
	}	
	public void disableResultBox(boolean flag){
			resultBox.setEnabled(flag);
			disableResultFlag = flag;  
	}
	public boolean getDisableResultFlag(){
			return disableResultFlag; 
	}
}
