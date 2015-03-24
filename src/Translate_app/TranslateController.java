package Translate_app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class TranslateController {
	Model theModel;
	TranslateView theView;
	List<List<String>> dicts;	
	public TranslateController(TranslateView theView ,Model theModel){		
		this.theModel = theModel;
		this.theView = theView;		
		init();		
	}
	
	private void init() {
		// TODO Auto-generated method stub
		theView.addTranslateBtnListener(new SubmitTranslateBtn(theModel, theView, this));;
		theView.addSourceBoxListener(new SubmitTranslateBtn(theModel, theView, this));		
		theView.addSaveBtnListener(new SaveTranslateBtn(theModel, theView));
		theView.addShowTableBtnListener(new ShowTranslateTableBtn(theModel, theView));		
		theView.addPosBoxListener(new ChangeList());
		theView.addWindowCloseListener(new WindowHandler());
	}
	
	public void parseJson(String Json){		
		theView.cleanListData();		
		DecodeJson reqJson = new DecodeJson(Json);		
		theView.setResultBox(reqJson.getTrans());
		if(reqJson.has_Dict()){
			theView.setPosBox(reqJson.getPos());
			theView.setDictBox(reqJson.getEntry());
			theView.disableResultBox(false);
			dicts = reqJson.getEntry();
		}else{
			theView.disableResultBox(true);
		}
	}
	class WindowHandler extends WindowAdapter {
		
		public void windowClosing(WindowEvent e){
			 int result=JOptionPane.showConfirmDialog(theView,
		               "�T�w�n�����{����?",
		               "�T�{�T��",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.WARNING_MESSAGE);
			 if(result==JOptionPane.YES_OPTION){System.exit(0);}
		}
	}
	class ChangeList extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			try{				
				int index = theView.getPosIndex();				
				//System.out.println(Dicts.get(index).size());
				//System.out.println(index+","+Dicts.get(index).size());
				ArrayList new_Dict = new ArrayList();
				for(int i = 0; i < dicts.get(index).size(); i++){
					new_Dict.add(dicts.get(index).get(i));
				}
				theView.changeDictBox(new_Dict);				
			}catch(Exception a){
				theView.displayInfo("�S���ﶵ",JOptionPane.ERROR_MESSAGE);
				//a.printStackTrace();			
			}				
		}
	}
}




 