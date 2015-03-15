package Translate_app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import javax.swing.JTextField;

//監聽 Enter按鈕/翻譯按鈕
public class SubmitTranslateBtn extends KeyAdapter implements ActionListener {
	
	Model theModel;
	TranslateView theView;
	TranslateController theCtrl;
	
	private String textFieldValue;
	
	public SubmitTranslateBtn(Model theModel, TranslateView theView ,TranslateController theCtrl){
		this.theModel = theModel;
		this.theView = theView;
		this.theCtrl = theCtrl;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub			
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			textFieldValue = theView.getSourceValue();			
			try {
				if(theModel.receiveText(textFieldValue)){
					theCtrl.parseJson(theModel.getJson());
				}else{
					theView.displayInfo("我需要網路!!!");
				}				
			} catch (Exception e1) {
				// TODO Auto-generated catch block				
				e1.printStackTrace();				
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		textFieldValue = theView.getSourceValue();			
		try {
			if(theModel.receiveText(textFieldValue)){
				theCtrl.parseJson(theModel.getJson());
			}else{
				theView.displayInfo("我需要網路!!!");
			}				
		} catch (Exception e1) {
			// TODO Auto-generated catch block				
			e1.printStackTrace();				
		}
	}
}
