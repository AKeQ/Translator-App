package Translate_app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Observable;

public class Model extends Observable{
	
	private String name;
	
	private String url;
	
	private String term;
	
	//private String trans;
	private String getJson;
	
	private String test = null;
	public Model(String name) {
		this.name = name;	
	}
	
	public void receiveText(String text) throws UnsupportedEncodingException{
		url = "https://translate.google.com.tw/translate_a/t?client=p&sl=en&tl=zh-TW&hl=zh-TW&dt=bd&dt=ex&dt=ld&dt=md&dt=qc&dt=rw&dt=rm&dt=ss&dt=t&dt=at&q=";
		term = URLEncoder.encode(text,"utf-8");
		try{
			System.out.println(url+term);
			URL target = new URL(url +term);
			HttpURLConnection connection = (HttpURLConnection)target.openConnection();
			//¼ÒÀÀÂsÄý¾¹½Ð¨D(User-Agent)
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			InputStreamReader isr = new InputStreamReader(connection.getInputStream(),"UTF-8");
			BufferedReader br = new BufferedReader(isr); 
			String getJson = br.readLine();
            System.out.println(getJson);
                     
            setChanged();
    		notifyObservers(getJson);           
		}catch(Exception e){
			e.printStackTrace(); 
		}
	}
	
}
