package Translate_app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

public class SaveTranslateBtn implements ActionListener {

	Model theModel;
	TranslateView theView;
	ShowTranslateTableBtn tableBtn;
	public SaveTranslateBtn (Model theModel,TranslateView theView){
		this.theModel = theModel;
		this.theView = theView;
		tableBtn = new ShowTranslateTableBtn(theModel,theView);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try{
			if(theView.getDisableResultFlag()){
				boolean flag = theModel.saveTranslateData(
									theView.getSourceValue(),
									theView.getResultValue());
				if(flag){
					theView.displayInfo("��r�s�W����!",JOptionPane.INFORMATION_MESSAGE);
					char upChar = theView.getSourceValue().toUpperCase().charAt(0);					
					tableBtn.updateTable(String.valueOf(upChar),false);
				}else{
					theView.displayInfo("����r�w�s�W�L�o!",JOptionPane.WARNING_MESSAGE);
				}			
			}else{
				boolean flag = theModel.saveTranslateData(
									theView.getSourceValue(), 
									theView.getPosBoxValue(), 
									theView.getDictBoxValue());
				if(flag){
					theView.displayInfo("��r�s�W����!",JOptionPane.INFORMATION_MESSAGE);
					char upChar = theView.getSourceValue().toUpperCase().charAt(0);					
					tableBtn.updateTable(String.valueOf(upChar),false);
				}else{
					theView.displayInfo("�o�ӳ�r�w�s�W�L��!",JOptionPane.WARNING_MESSAGE);
				}		
			}
		}catch(Exception e){			
			theView.displayInfo("�S���F��i�H�x�s!",JOptionPane.ERROR_MESSAGE);			
		}
	}
}
