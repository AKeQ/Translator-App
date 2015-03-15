package Translate_app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PromptView extends JFrame {
	
	private JLabel pathLabel = new JLabel("File Path :");
	private JTextField pathBox = new JTextField(System.getProperty("user.dir")+"\\translate.json");
	private JButton browserBtn = new JButton("Browser...");
	private JButton submitBtn = new JButton("OK");
	private String str; //json or path
	private String path;
	private boolean readFile;
	private int dialogResult;
	
	public PromptView(){	
		initUI();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		GroupLayout gl = new GroupLayout(panel);
		panel.setLayout(gl);
		
		JLabel label = new JLabel("Copyright\u00a92015 by Eddie Chiu   ");
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));//Brush Script MT        
		
		pathBox.setPreferredSize(new Dimension(350,25));		
		Font font = new Font("Cambria", Font.PLAIN, 14);
		pathLabel.setFont(font);
		pathBox.setFont(font);
		browserBtn.setFont(font);
		submitBtn.setFont(font);
		gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
		gl.setHorizontalGroup(//¥­¦æ
			gl.createSequentialGroup()
					.addComponent(pathLabel)
					.addComponent(pathBox)
				.addGroup(
					gl.createParallelGroup(GroupLayout.Alignment.LEADING, false)						
						.addComponent(browserBtn)
						.addComponent(submitBtn)
				)
		);
		browserBtn.setFocusPainted(false);
		submitBtn.setFocusPainted(false);
		gl.linkSize(SwingConstants.HORIZONTAL, browserBtn, submitBtn);
		gl.setVerticalGroup(//««ª½
			gl.createSequentialGroup()				
				.addGroup(
					gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(pathLabel)
					.addComponent(pathBox)
					.addComponent(browserBtn)							
				)
					.addComponent(submitBtn)				
		);
		pathBox.selectAll();	
		label.setHorizontalAlignment(JLabel.RIGHT);
		label.setBackground(Color.lightGray);
		panel.setBackground(Color.lightGray);		
		add(panel, BorderLayout.CENTER);
		add(label, BorderLayout.SOUTH);
		pack();
		setTitle("Select a File");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
	}	
	public void addPathBoxListener(DocumentListener listenForPathBox){
		pathBox.getDocument().addDocumentListener(listenForPathBox);
	}
	public void addSubmitBtnListener(ActionListener listenForSubmitBtn){
		submitBtn.addActionListener(listenForSubmitBtn);
	}
	public void addBrowserBtnListener(ActionListener listenForBrowserBtn){
		browserBtn.addActionListener(listenForBrowserBtn);
	}
	public String getPathValue(){
		return pathBox.getText().toString();
	}
	public void displaySubmitBtn(boolean flag){
		submitBtn.setEnabled(flag);
	}	
	public void displayError(String error, int message){
		JOptionPane.showMessageDialog(this, error, "Information", message);
	}
	public void displayConfirm(String a, String b){ //content , title		
		dialogResult = JOptionPane.showConfirmDialog (this, a, b, JOptionPane.YES_NO_OPTION);		
	}
	public int getDiaResult(){
		return dialogResult;
	}
	public void setStr_Falg(String s,boolean b1){
		str = s; //json or path
		readFile = b1;		
	}	
	public String getStr(){
		return str; 
	}
	public boolean getReadFile(){
		return readFile;
	}	
	public void setPmpVisible(boolean b){this.setVisible(b);}
	public void setPathBox(String s){
		pathBox.setText(s);
	}
}
