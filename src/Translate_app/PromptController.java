package Translate_app;

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
	}
}
