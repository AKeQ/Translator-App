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
					theView.displayInfo("單字新增完成!",JOptionPane.INFORMATION_MESSAGE);
					char upChar = theView.getSourceValue().toUpperCase().charAt(0);					
					tableBtn.updateTable(String.valueOf(upChar),false);
				}else{
					theView.displayInfo("此單字已新增過囉!",JOptionPane.WARNING_MESSAGE);
				}			
			}else{
				boolean flag = theModel.saveTranslateData(
									theView.getSourceValue(), 
									theView.getPosBoxValue(), 
									theView.getDictBoxValue());
				if(flag){
					theView.displayInfo("單字新增完成!",JOptionPane.INFORMATION_MESSAGE);
					char upChar = theView.getSourceValue().toUpperCase().charAt(0);					
					tableBtn.updateTable(String.valueOf(upChar),false);
				}else{
					theView.displayInfo("這個單字已新增過啦!",JOptionPane.WARNING_MESSAGE);
				}		
			}
		}catch(Exception e){			
			theView.displayInfo("沒有東西可以儲存!",JOptionPane.ERROR_MESSAGE);			
		}
	}
}
