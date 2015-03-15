package Translate_app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BrowserPmptBtn implements ActionListener{
	
	PromptView pmptView;
	public BrowserPmptBtn(PromptView pmptView){
		this.pmptView = pmptView;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JFileChooser fileopen = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("c files", "c");
        fileopen.addChoosableFileFilter(filter);
        int ret = fileopen.showDialog(pmptView, "¶}±Ò");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();            
            pmptView.setPathBox(file.getPath());
        }
	}
}
