
package controller;

import model.Map;
import java.util.*;
import java.awt.*;
import javax.swing.*;

import view.GraphicView;
import view.TextView;

import java.util.Observer;

//Use this class to play!
//Author:Hermosbird
//Description: The control game GUI of Hunt the Wumpus which contains a main()
public class GameGUI {
	private JFrame window;
	private Map map;
	private JPanel JP;
	private ControlBottom shoot;
	private ControlBottom move;
	private JTabbedPane tab;

	private TextView textView;
	private GraphicView graphicView;

	public GameGUI() {
		window = new JFrame("Hunt the Wumpus");
		window.setSize(1000, 800);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tab = new JTabbedPane();

		map = new Map(10, 10);
		map.Set();

		textView = new TextView(map);
		graphicView = new GraphicView(map);

		JP = new JPanel();
		JP.setLayout(new BoxLayout(JP, BoxLayout.Y_AXIS));

		move = new ControlBottom(map, "Move");
		move.setAlignmentX(Component.CENTER_ALIGNMENT);
		shoot = new ControlBottom(map, "Shoot");
		shoot.setAlignmentX(Component.CENTER_ALIGNMENT);

		JP.add(new JPanel());
		JP.add(shoot);
		JP.add(new JPanel());
		JP.add(move);
		JP.add(new JPanel());

		tab.add(textView, "Text Version");
		tab.add(graphicView, "Graphic Version");

		window.add(JP, BorderLayout.WEST);
		window.add(tab, BorderLayout.CENTER);
		window.setVisible(true);

		map.addObserver((Observer) textView);
		map.addObserver((Observer) graphicView);
	}

	// This is the main method to play the game!
	public static void main(String[] args) {
		new GameGUI();
	}
}
