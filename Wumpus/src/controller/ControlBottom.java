package controller;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

import model.Map;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Author:Hermosbird
//Description: The class of the controller of move and shoot bottom
public class ControlBottom extends JPanel {
	private Map map;
	private BasicArrowButton north;
	private BasicArrowButton south;
	private BasicArrowButton west;
	private BasicArrowButton east;

	private class ShootListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (map.GG()) {
				north.setEnabled(false);
				south.setEnabled(false);
				west.setEnabled(false);
				east.setEnabled(false);
			} else {
				if (e.getSource() == north) {
					map.shoot("north");
				}
				if (e.getSource() == south) {
					map.shoot("south");
				}
				if (e.getSource() == west) {
					map.shoot("west");
				}
				if (e.getSource() == east) {
					map.shoot("east");
				}
			}
		}
	}

	private class MoveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (map.GG()) {
				north.setEnabled(false);
				south.setEnabled(false);
				west.setEnabled(false);
				east.setEnabled(false);
			} else {
				if (e.getSource() == north) {
					map.move("north");
				}
				if (e.getSource() == south) {
					map.move("south");
				}
				if (e.getSource() == west) {
					map.move("west");
				}
				if (e.getSource() == east) {
					map.move("east");
				}
			}
		}
	}

	public ControlBottom(Map map, String str) {
		super();
		int i = 0;
		this.map = map;
		north = new BasicArrowButton(SwingConstants.NORTH);
		south = new BasicArrowButton(SwingConstants.SOUTH);
		west = new BasicArrowButton(SwingConstants.WEST);
		east = new BasicArrowButton(SwingConstants.EAST);

		this.setLayout(new GridLayout(3, 3));
		this.setPreferredSize(new Dimension(200, 200));

		north.setFocusable(false);
		south.setFocusable(false);
		east.setFocusable(false);
		west.setFocusable(false);

		JLabel shoot = new JLabel(" Shoot");
		JLabel move = new JLabel(" Move");

		move.setFont(new Font(null, Font.BOLD, 18));
		shoot.setFont(new Font(null, Font.BOLD, 18));

		add(new JPanel());
		add(north);
		add(new JPanel());
		add(west);
		if (str.equals("Shoot")) {
			add(shoot);
			i = 1;
		}
		if (str.equals("Move")) {
			add(move);
		}
		add(east);
		add(new JPanel());
		add(south);

		if (i == 1)
			Shoot();
		else
			Move();

	}

	private void Move() {
		ActionListener ls = new MoveListener();
		north.addActionListener(ls);
		south.addActionListener(ls);
		west.addActionListener(ls);
		east.addActionListener(ls);

	}

	private void Shoot() {
		ActionListener ls = new ShootListener();
		north.addActionListener(ls);
		south.addActionListener(ls);
		west.addActionListener(ls);
		east.addActionListener(ls);

	}

}
