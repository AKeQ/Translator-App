package Translate_app;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ChangePmptText implements DocumentListener{
	
	PromptView pmptView;
	public ChangePmptText(PromptView pmptView){
		this.pmptView = pmptView;
	}
	
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		eventChange();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		eventChange();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		eventChange();
	}
	public void eventChange(){
		int length = pmptView.getPathValue().length();
		if (length<=0){
	    	 pmptView.displaySubmitBtn(false);
	     }else{
	    	 pmptView.displaySubmitBtn(true);
	     }
	}
}
