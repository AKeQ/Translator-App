package Translate_app;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ChangeList extends MouseAdapter {
	DefaultListModel listmodel_3;
	JList posbox;
	List<List<String>> Dicts;
	public ChangeList(DefaultListModel i, JList j, List k ){		
		this.listmodel_3 = i;
		this.posbox = j;
		this.Dicts = k;		
	}
	@Override
	public void mouseClicked(MouseEvent e){
		try{
		if(e.getClickCount() == 2){
			int index = posbox.locationToIndex(e.getPoint());
			listmodel_3.clear();
			for(int i = 0; i < Dicts.get(index).size(); i++){
				listmodel_3.addElement(Dicts.get(index).get(i));
			}			
		}
		}catch(Exception a){
			/*
			JOptionPane.showMessageDialog(posbox,
				    "Null","Information", JOptionPane.INFORMATION_MESSAGE);			
		*/}				
	}
}
