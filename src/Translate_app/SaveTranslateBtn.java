package Translate_app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveTranslateBtn implements ActionListener {

	Model theModel;
	TranslateView theView;
	
	public SaveTranslateBtn (Model theModel,TranslateView theView){
		this.theModel = theModel;
		this.theView = theView;
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
					theView.displayInfo("��r�s�W����!");
				}else{
					theView.displayInfo("����r�w�s�W�L�o!");
				}			
			}else{
				boolean flag = theModel.saveTranslateData(
									theView.getSourceValue(), 
									theView.getPosBoxValue(), 
									theView.getDictBoxValue());
				if(flag){
					theView.displayInfo("��r�s�W����!");
				}else{
					theView.displayInfo("�o�ӳ�r�w�s�W�L��!");
				}		
			}
		}catch(Exception e){			
			theView.displayInfo("�S���F��i�H�x�s!");			
		}
	}
}
