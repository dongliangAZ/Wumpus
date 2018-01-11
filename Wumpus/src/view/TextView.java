package view;

import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Map;


//Description: The class of text view
public class TextView extends JPanel implements Observer {
	private Map map;
	private String str;
	private JTextArea SA;

	@Override
	public void update(Observable arg0, Object arg1) {
		map = (Map) arg0;
		str = map.String();
		SA.setText(str);
	}

	public TextView(Map map) {
		str = map.String();
		SA = new JTextArea(str);
		SA.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
		SA.setLocation(80, 0);
		this.add(SA);
	}
}
