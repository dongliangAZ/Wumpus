package view;

import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Map;

//Description: The class of Graphic view
public class GraphicView extends JPanel implements Observer {
	private Map map;
	private BufferedImage ground;
	private BufferedImage uplayer;
	private BufferedImage theHunter;

	public GraphicView(Map mp) {
		super();
		map = mp;

		try {
			ground = ImageIO.read(new File("Ground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		map = (Map) arg0;
		repaint();
	}

	public void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		Graphics2D minor = (Graphics2D) graph;
		Draw(minor);
	}

	private void Draw(Graphics2D g) {
		// First(g);
		// Second(g);
		// Third(g);
		if (ground != null)
			for (int i = 0; i < map.getRow(); i++)
				for (int j = 0; j < map.getCol(); j++)
					g.drawImage(ground, (j * 60) + 100, i * 60, 60, 60, null);

		String file = "";
		String image = "";
		for (int i = 0; i < map.getRow(); i++) {
			for (int j = 0; j < map.getCol(); j++) {
				image = map.getmap()[i][j].getSituation();
				if (image.equals(" ")) {
				} else {
					if (image.equals("S"))
						file = "Slime.png";
					else if (image.equals("P"))
						file = "SlimePit.png";
					else if (image.equals("B"))
						file = "Blood.png";
					else if (image.equals("G"))
						file = "Goop.png";
					else if (image.equals("W"))
						file = "Wumpus.png";
					try {
						uplayer = ImageIO.read(new File(file));
					} catch (IOException e) {
						e.printStackTrace();
					}
					g.drawImage(uplayer, (j * 60) + 100, i * 60, 60, 60, null);
				}
			}
		}

		image = "";
		for (int i = 0; i < map.getRow(); i++) {
			for (int j = 0; j < map.getCol(); j++) {
				image = map.roomString(i, j);

				if (map.isHunter(i, j)) {
					try {
						theHunter = ImageIO.read(new File("TheHunter.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					g.drawImage(theHunter, (j * 60) + 100, i * 60, 60, 60, null);
				} //
				else if (!map.GG() && !map.getmap()[i][j].isVisit()) {
					g.setColor(Color.BLACK);
					g.fillRect((j * 60) + 100, i * 60, 60, 60);
				} else {

				}
				//
			}
		}

	}

}
