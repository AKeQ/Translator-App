package Translate_app;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class PromptController {
	
	PromptView pmptView;
	
	public PromptController(PromptView pmptView){
		this.pmptView = pmptView;
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		pmptView.addPathBoxListener(new ChangePmptText(pmptView));
		pmptView.addSubmitBtnListener(new SubmitPmptBtn(pmptView));
		pmptView.addBrowserBtnListener(new BrowserPmptBtn(pmptView));
		pmptView.addWindowCloseListener(new WindowHandler());
	}
	
	class WindowHandler extends WindowAdapter {
		
		public void windowClosing(WindowEvent e){
			 int result=JOptionPane.showConfirmDialog(pmptView,
		               "確定要結束程式嗎?",
		               "確認訊息",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.WARNING_MESSAGE);
			 if(result==JOptionPane.YES_OPTION){System.exit(0);}
		}
	}
}
