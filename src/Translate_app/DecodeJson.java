package Translate_app;

import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class DecodeJson {
	
	private JSONObject Json;
	private static int dictlen;
	private JSONArray ary_Dict;
	
	public DecodeJson(String j){
		Json = new JSONObject(j);
	}
	
	public String getTrans(){
		JSONArray sent = Json.getJSONArray("sentences");
        int sentlen = sent.length();
        String trans = "";
        for (int i = 0; i < sentlen; i++){
        	trans += sent.getJSONObject(i).getString("trans");
        }        
        return trans;
	}
	
	public boolean has_Dict(){
		boolean flag = Json.isNull("dict");
		if(flag == false){
			return true;
		}else{
			return false;
		}
	}
	
	public ArrayList<String> getPos(){
		ArrayList<String> list_Pos = new ArrayList<String>();
		ary_Dict = Json.getJSONArray("dict");		
		dictlen = ary_Dict.length();
		for(int i = 0; i < dictlen; i++){
			String pos = (String) ary_Dict.getJSONObject(i).get("pos");
			list_Pos.add(pos);					
		}
		return list_Pos;
	}
	
	public List getEntry(){		
		List<List<String>> Dicts = new ArrayList<List<String>>();
		int entrylen;
		for(int i = 0; i < dictlen ; i++){ //
			entrylen = getlen((JSONObject) ary_Dict.get(i));
			Dicts.add(new ArrayList<String>()); //
			for(int j = 0; j < entrylen ; j++){ //
				String word = getWord((JSONObject) ary_Dict.get(i),j);
				Dicts.get(i).add(j, word);
			}			
		}
		/* print test
		for(int i = 0; i < Dicts.size(); i++){
			System.out.println(Dicts.get(i).getClass());
			for(int j = 0; j < Dicts.get(i).size(); j++){
				System.out.println(Dicts.get(i).get(j));
			}
		}
		*/
		return Dicts;
	}
		
	public String getWord(JSONObject object ,int j){
		String string = "";
		Double score;
		JSONObject obj_Entry = object.getJSONArray("entry").getJSONObject(j);
		//*score
		boolean flag = obj_Entry.isNull("score");
		if(flag == false){
			score = obj_Entry.getDouble("score");
			string += Math.ceil(score*10000.0)/100.0+"%"+" ";
		}else{
			string += 0+".0%"+" ";
		}
		//*word
		string += obj_Entry.get("word")+" ";
		//*reverse_translation
		int rev_Trans_len =  obj_Entry.getJSONArray("reverse_translation").length();
		for(int i = 0; i < rev_Trans_len; i++){
			
			if(i < rev_Trans_len-1){				
				string += obj_Entry.getJSONArray("reverse_translation").get(i)+", ";				
			}else{
				string += obj_Entry.getJSONArray("reverse_translation").get(i);
			}
		}
		return string;
	} 
	
	public int getlen(JSONObject object){		
		JSONArray ary_Entry = object.getJSONArray("entry");
		int len = ary_Entry.length();
		return len;
	}
}
