package model;


//This is the class of the situation
// Contains the situation of a romm
public enum Situation {
	Empty, Slime, Blood, Goop, Wumpus, Pit;

	public String getSituation() {
		switch (this) {
		case Blood:
			return "B";
		case Slime:
			return "S";
		case Goop:
			return "G";
		case Wumpus:
			return "W";
		case Pit:
			return "P";
		default:
			return " ";
		}
	}

}
