package Translate_app;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;

public class Platform {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				// TODO Auto-generated method stub				
				final PromptView pmptView = new PromptView();
				PromptController pmptCtrl = new PromptController(pmptView);
				pmptView.setVisible(true);												
				pmptView.addComponentListener(new ComponentAdapter() {
					  @Override
					  public void componentHidden(ComponentEvent e) {
						  Model theModel = new Model(pmptView);				
						  TranslateView theView = new TranslateView();
						  TranslateController translateCtrl = new TranslateController(theView,theModel);
						  theView.setVisible(true);
					  }
				});
				
			}
		});
	}
}
