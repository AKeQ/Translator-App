package Translate_app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.*;

import javax.swing.JOptionPane;

public class SubmitPmptBtn implements ActionListener{
	
	private PromptView pmptView;
	 
	public SubmitPmptBtn(PromptView pmptView){
		this.pmptView = pmptView;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String filePath = pmptView.getPathValue();
		File file = new File(filePath);				
		
		if(confirmPath(file)){
			if(getExtensionName(filePath)){
				try{	
					BufferedReader br = 
						new BufferedReader(
							new InputStreamReader(new FileInputStream(filePath),"UTF-8"));		
					String tempJson = br.readLine();
					/*
					while(br.ready()){
						tempJson +=  br.readLine();			
					}
					*/
					//System.out.println(tempJson);
					//System.out.println(confirmVersion(tempJson));//D:\Java-Example\A.json
					if(confirmVersion(tempJson)){
						pmptView.setStr_Falg(tempJson, true);
						pmptView.setPmpVisible(false);
						//set JsonString, can read file, disablePmpt
					}
				}catch(IOException e){
					pmptView.displayError("系統找不到指定的檔案!", JOptionPane.WARNING_MESSAGE);
					pmptView.displayConfirm("是否要新增檔案", "提示");				
					if(pmptView.getDiaResult() == JOptionPane.YES_OPTION){
						pmptView.setStr_Falg(filePath, false);
						pmptView.setPmpVisible(false);
					}
					//set JsonString, can not read file, disablePmpt
				}
			}else{
				pmptView.displayError("請選擇JSON檔案!", JOptionPane.WARNING_MESSAGE);
			}
		}else{
			pmptView.displayError("檔案路徑格式錯誤", JOptionPane.WARNING_MESSAGE);
		}
	}	
    private boolean confirmVersion(String s) {
		// TODO Auto-generated method stub
    	try{
    		JSONObject Json = new JSONObject(s);
    		if(Json.isNull("SerialVersion")){
    			pmptView.displayError("非本程式使用的JSON檔案!", JOptionPane.WARNING_MESSAGE);
    			return false;
    		}else{
    			//System.out.println(Json);
    			return true;
    		}
    	}catch(Exception e){
    		pmptView.displayError("請選擇JSON檔案!", JOptionPane.WARNING_MESSAGE);
    		return false;
    	}
	}
	private boolean getExtensionName(String path) {
        int startIndex = path.lastIndexOf('.') + 1;
        String extName = path.substring(startIndex);
        if(extName.equals("json")){			
			return true;
		}else{						
			return false;
		}    
    }
	private boolean confirmPath(File file){
		try{			
			file.getCanonicalFile();
			return true;
		}catch(IOException e){
			
			return false;
		}
		
	}
}
