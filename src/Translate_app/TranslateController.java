package Translate_app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class TranslateController implements Observer{
	Model model;
	JTextField sourcebox ;
	JButton translatebtn ;
	JButton savebtn ;
	String textFieldValue;
	List<List<String>> Dicts;
	ArrayList list_Pos;
	DefaultListModel listmodel_1,listmodel_2,listmodel_3;
	JList resultbox, posbox, dictbox;
		
	public TranslateController(Model m){
		model = m;
		init(model);
	}
		
	private void init(Model model) {		
		// TODO Auto-generated method stub
		model.addObserver(this);
		
		sourcebox = new JTextField();
		translatebtn = new JButton("翻譯");
		savebtn = new JButton("儲存");
		
		listmodel_1 = new DefaultListModel();
		listmodel_2 = new DefaultListModel();
		listmodel_3 = new DefaultListModel();		
		
		resultbox = new JList(listmodel_1);
		posbox = new JList(listmodel_2);
		dictbox = new JList(listmodel_3);
		
		Font font_en = new Font("Andalus", Font.PLAIN, 20);
		Font font_list = new Font("細明體", Font.PLAIN, 14);
		Font font_tw = new Font("細明體", Font.PLAIN, 20);
		
		resultbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		dictbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		sourcebox.setFont(font_en);
		translatebtn.setFont(font_tw);
		resultbox.setFont(font_tw);
		savebtn.setFont(font_tw);
		//set list font
		posbox.setFont(font_list);
		dictbox.setFont(font_list);
		
		translatebtn.addActionListener(new SubmitButton(model,sourcebox));
		sourcebox.addKeyListener(new SubmitButton(model,sourcebox));
	}

	@Override
	public void update(Observable arg0, Object arg) {
		// TODO Auto-generated method stub
		
		listmodel_1.clear();		
		listmodel_2.clear();
		listmodel_3.clear();
		DecodeJson reqJson = new DecodeJson((String)arg);
		listmodel_1.addElement(reqJson.getTrans());		
		if(reqJson.has_Dict()){			
			list_Pos = reqJson.getPos();			
			// add JList content
			for(int i = 0; i < list_Pos.size(); i++ ){
				listmodel_2.addElement(list_Pos.get(i));
			}
			Dicts = reqJson.getEntry();
			for(int i = 0; i < Dicts.get(0).size(); i++ ){
				listmodel_3.addElement(Dicts.get(0).get(i));
			}
			posbox.addMouseListener(new ChangeList(listmodel_3,posbox,Dicts));
			//dictbox.addMouseListener(new SaveItem(listmodel_3,posbox,Dicts));
		}		
	}
	
}




 