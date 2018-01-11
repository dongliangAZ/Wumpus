package model;

//Author:Dong Liang

// A room contain its type and row and column
public class Room {
	private String situation;
	private int row;// store row coordinate
	private int col;// store column coordinate
	private boolean isvisit;

	public Room(Situation s) {
		situation = s.getSituation();
		isvisit = false;
	}

	public void setR(int i) {
		this.row = i;
	}

	public void setsituation(Situation situation) {
		this.situation = situation.getSituation();
	}

	public void setC(int i) {
		this.col = i;
	}

	public void setVisit(boolean b) {
		this.isvisit = b;
	}

	public boolean isVisit() {
		return this.isvisit;
	}

	public String getSituation() {
		return situation;
	}

	public boolean isempty() {
		return this.getSituation() == " ";
	}

}
