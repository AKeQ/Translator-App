package Translate_app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.*;

public class Model {
	
	private String url;	
	private String term;	
	private String Json;
	private PromptView pmptView;
	private JSONObject obj;
	private String path;
	public Model(PromptView pmptView){
		this.pmptView = pmptView;
		path = pmptView.getPathValue();
		init(); 
	}	
	private void init() {
		// TODO Auto-generated method stub
		if(pmptView.getReadFile()){			
			obj = new JSONObject(pmptView.getStr());
			/*
			System.out.println("read file: String = \n"+pmptView.getStr()+"\n"+obj);			
			String a = "ABC";
			char b = a.charAt(0);
			System.out.println(obj.get(Character.toString(b)));
			JSONArray c = obj.getJSONArray(Character.toString(b));
			System.out.println(c.get(0)+"\n"+c.get(1));
			if(c.get(0).toString().equals(c.get(1).toString())){
				System.out.println("true");
			}
			*/			
		}else{			
			obj = new JSONObject();
			obj.put("SerialVersion","Translator App 1.0");
			for(int i = 65 ; i<=90; i++){				
				JSONArray array = new JSONArray();								
				obj.put(Character.toString((char)i), array);				
			}
			WriteFile(obj.toString(), path, false);
		}
	}
	private void WriteFile(String string, String path, boolean append) {
		// TODO Auto-generated method stub
		// 寫檔
		try{
			File file = new File(path);// 建立檔案，準備寫檔
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(file, append), "utf8"));// 設定為BIG5格式
			// 參數append代表要不要繼續許入該檔案中
			writer.write(string); // 寫入該字串
			//writer.newLine(); // 寫入換行
			writer.close(); 
		}catch (IOException e){
			e.printStackTrace();
			System.out.println(path + "寫檔錯誤!!");			
		}
	}
	public boolean receiveText(String text) throws UnsupportedEncodingException{
		url = "https://translate.google.com.tw/translate_a/t?client=p&sl=en&tl=zh-TW&hl=zh-TW&dt=bd&dt=ex&dt=ld&dt=md&dt=qc&dt=rw&dt=rm&dt=ss&dt=t&dt=at&q=";
		term = URLEncoder.encode(text,"utf-8");
		try{			
			URL target = new URL(url +term);
			HttpURLConnection connection = (HttpURLConnection)target.openConnection();
			//模擬瀏覽器請求(User-Agent)
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
				InputStreamReader isr = new InputStreamReader(connection.getInputStream(),"UTF-8");
				BufferedReader br = new BufferedReader(isr); 
				Json = br.readLine();			
				return true;
			}else{
				return false;
			}
			
		}catch(Exception e){			
			//e.printStackTrace(); 
			return false;
		}
	}	
	public String getJson(){
		return Json;		
	}
	public boolean saveTranslateData(String a,String b,String c){
		String sourceValue = a;
		String posValue = b;
		String dictValue = c;
		//System.out.println(a+b+c);		
		String dict [] = dictValue.split(",");		
		Pattern pattern1 = Pattern.compile("^[0-9]+(\\.[0-9]+)?%");
		Pattern pattern2 = Pattern.compile("%\\s[^\\d|\\w]*\\s");		
		Matcher matcher1 = pattern1.matcher(dict[0]);
		Matcher matcher2 = pattern2.matcher(dict[0]);		
		double score = 0.0 ;
		String translate = "";
		while(matcher1.find()){
			String temp =  matcher1.group();			
			temp = temp.replaceAll("%","");
			score = Float.parseFloat(temp)/100;			
		}
		while(matcher2.find()){
			String temp = matcher2.group();
			
			temp = temp.replaceAll("^%\\s","");
			
			temp = temp.replaceAll("\\s", "");
			translate = temp;			
		}
		
		dict[0] = dict[0].replaceAll("^[0-9]+(\\.[0-9]+)?%\\s[^\\d|\\w]*\\s","");			
		/*
		System.out.printf("%f %s ",score,translate);
		for(int i = 0; i < dict.length; i++){
			System.out.printf("%s ",dict[i]);
		}
		*/
		JSONObject tempObj = new JSONObject();
		tempObj.put("word", sourceValue);
		tempObj.put("pos", posValue);
		tempObj.put("score", score);
		tempObj.put("trans", translate);
		JSONArray array = new JSONArray();
		for(int i = 0; i < dict.length; i++){
			array.put(i,dict[i]);
		}
		tempObj.put("rev_trans", array);
		char upChar = sourceValue.toUpperCase().charAt(0);
		//System.out.println(tempObj.toString());
		
		if(confirmRep(upChar,tempObj)){
			JSONArray mapping = obj.getJSONArray(Character.toString(upChar));
			obj.getJSONArray(Character.toString(upChar)).put(mapping.length(),tempObj);
			WriteFile(obj.toString(), path, false);
			return true;
		}else{
			return false;
		}
	}
	public boolean saveTranslateData(String a,String b){
		String sourceValue = a;
		String resultValue = b;
		JSONObject tempObj = new JSONObject();
		tempObj.put("word", sourceValue);
		tempObj.put("trans", resultValue);
		
		char upChar = sourceValue.toUpperCase().charAt(0);
		System.out.println(tempObj.toString());
		
		if(confirmRep(upChar,tempObj)){
			JSONArray mapping = obj.getJSONArray(Character.toString(upChar));
			obj.getJSONArray(Character.toString(upChar)).put(mapping.length(),tempObj);			
			WriteFile(obj.toString(), path, false);
			return true;
		}else{
			return false;
		}
	}
	private boolean confirmRep(char a, JSONObject b) {
		// TODO Auto-generated method stub
		char upChar = a;
		JSONObject tempObj = b;
		boolean flag = true;		
		JSONArray mapping = obj.getJSONArray(Character.toString(upChar));		
		for(int i = 0; i < mapping.length(); i++){
			JSONObject mappObj = mapping.getJSONObject(i);
			if(tempObj.toString().equals(mappObj.toString())){
				flag =  false; //有重覆
			}
		}
		return flag;
	}
	public String getFileName(){
		File file = new File(path);
		String name = file.getName();
		name = name.replaceFirst("\\.json$", "");
		return name;
	}
	public JSONObject getJSONObj(){
		return obj;
	}
}
