package Translate_app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicBorders;
import org.json.*;

public class Platform extends JFrame {
	
	
	private JButton btn1,btn2;
    
	private JTextField text1;
	
	private DefaultListModel listmodel_1, listmodel_2, listmodel_3;
	private JList list1,list2,list3;
	
	public Platform(Model model){
		initUI(model);
	}
	
	private void initUI(Model model) {
		// TODO Auto-generated method stub
		TranslateController translatectrl = new TranslateController(model);
		
		JPanel panel = new JPanel();
        GroupLayout gl = new GroupLayout(panel);
        panel.setLayout(gl);
		
        text1 = translatectrl.sourcebox;         
        btn1= translatectrl.translatebtn; //翻譯
        btn2 = translatectrl.savebtn; //儲存
        
        btn1.setFocusPainted(false);
        btn1.setContentAreaFilled(false);        
        btn2.setFocusPainted(false);
        btn2.setContentAreaFilled(false);
        
        listmodel_1 = translatectrl.listmodel_1;
        listmodel_2 = translatectrl.listmodel_2;
        listmodel_3 = translatectrl.listmodel_3;

        list1 = translatectrl.resultbox;
        list2 = translatectrl.posbox;
        list3 = translatectrl.dictbox;
        JLabel label = new JLabel("Copyright\u00a92015 by Eddie Chiu   ");
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));//Brush Script MT
        label.setBackground(Color.lightGray);
		gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        
        text1.setBorder(BorderFactory.createLineBorder(Color.darkGray,2));        
        list1.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        list2.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        list3.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));           
        
        JScrollPane scroll_1 = new JScrollPane(list1);
        JScrollPane scroll_2 = new JScrollPane(list2);
        JScrollPane scroll_3 = new JScrollPane(list3);
        
        text1.setPreferredSize(new Dimension(150,35));        
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
	        				.addComponent(text1)
	        				.addComponent(scroll_1)
	        		)               
	                	.addGroup(
	                		gl.createParallelGroup(GroupLayout.Alignment.CENTER)
	                		.addComponent(btn1)
	                		.addComponent(btn2)
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
	        				.addComponent(text1)
	        				.addComponent(btn1)
	        			)
	                	.addGroup(
	                		gl.createParallelGroup(GroupLayout.Alignment.LEADING)
	                		.addComponent(scroll_1)
	                		.addComponent(btn2)
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				// TODO Auto-generated method stub
				Model model = new Model("translator");
				Platform p = new Platform(model);
				//model.addObserver(p);
				p.setVisible(true);
			}
		});				
	}
}
