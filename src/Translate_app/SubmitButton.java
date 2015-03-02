package Translate_app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import javax.swing.JTextField;

//ºÊÅ¥ Enter«ö¶s/Â½Ä¶«ö¶s
public class SubmitButton extends KeyAdapter implements ActionListener {
	
	private String textFieldValue;
	private Model model;
	
	private JTextField sourcebox ;
	
	public SubmitButton(Model m , JTextField t){
		this.model = m;
		this.sourcebox = t;		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub			
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			textFieldValue = sourcebox.getText();			
			try {
				model.receiveText(textFieldValue);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		textFieldValue = sourcebox.getText();			
		try {
			model.receiveText(textFieldValue);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
