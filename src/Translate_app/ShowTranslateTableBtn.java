package Translate_app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;

import org.json.JSONArray;
import org.json.JSONObject;

public class ShowTranslateTableBtn  implements ActionListener {
	
	Model theModel;
	TranslateView theView;
	ShowTableView tableView;
	Object[][] data;
	static String labelText = "";
	public ShowTranslateTableBtn(Model theModel, TranslateView theView ){
		this.theModel = theModel;
		this.theView = theView;
		initUI();
	}
	

	private void initUI() {
		// TODO Auto-generated method stub
		tableView = new ShowTableView(theModel,theView);
		tableView.addLabelCharListener(new LableCharPressed());
		tableView.addFilterBtnListener(new FilterTableValue());
		tableView.addWindowListener(new WindowAdapter(){			
			public void windowClosing(WindowEvent e) { 			
				theView.showTableBtn.setSelected(false);
			}															
		});		
	}
	public void updateTable(String s, boolean f){
		
		boolean flag = f;		

		if(flag == false){			
			if(s.toString().equals(labelText.toString())){				
				flag = true; //reload tableModel but not response...
			}
		}
		if(flag){			
			JSONObject obj = theModel.getJSONObj();
			JSONArray mapping = obj.getJSONArray(s);		
			String[] key ={"word","trans","pos","score","rev_trans"};
			data = new Object[mapping.length()][key.length];			
			
			for(int i = 0; i < mapping.length(); i++){//單字個數
				JSONObject temp = mapping.getJSONObject(i);				
				for(int j = 0; j < key.length; j++ ){//項目					
					if(temp.isNull(key[j])){
						data[i][j] = "";
					}else{
						if(key[j].toString().equals("score")){//整數四捨五入							
							Double x = Math.ceil(temp.getDouble(key[j])*10000.0)/100.0;							
							data[i][j] = x;
						}else{						
							if(key[j].toString().equals("rev_trans")){//單字組成字串列
								int ary_leng = temp.getJSONArray(key[j]).length();
								String rev_trans = "";
								for(int k = 0; k < ary_leng; k++){
									if(k == ary_leng-1){
										rev_trans += temp.getJSONArray(key[j]).getString(k);
									}else{
										rev_trans += temp.getJSONArray(key[j]).getString(k)+", ";
									}
								}
								data[i][j] = rev_trans;
							}else{							
								data[i][j] = temp.get(key[j]);
							}
						}
					}
				}				
			}
			if(data.length>0){
				tableView.setTableData(data);
			}else{
				tableView.displayInfo("沒有字首 "+s+" 的單字", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	} 
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(theView.showTableBtn.isSelected()){
			tableView.setVisible(true);
		}else{
			tableView.setVisible(false);
		}
	}	
	class FilterTableValue extends KeyAdapter implements ActionListener{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				String text = tableView.getFilterText();  
	            if (text.length() == 0) {  
	                tableView.setSorter(null);  
	            } else {  
	            	tableView.setSorter(RowFilter.regexFilter(text));  
	            }
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String text = tableView.getFilterText();  
            if (text.length() == 0) {  
                tableView.setSorter(null);  
            } else {  
            	tableView.setSorter(RowFilter.regexFilter(text));  
            }   
		}
		
	}
	
	class LableCharPressed extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			Object o = e.getSource();
			JLabel label = null;			
			if(o instanceof JLabel){
				label = (JLabel)o;
			}
			if(label != null){
				labelText = label.getText();
			}			
			int ascii = (int)labelText.charAt(0);
			tableView.setLabelCharColor(ascii);
			updateTable(labelText,true);
		}		
	}	
}
