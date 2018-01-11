package model;


//This is the class of a map

import java.util.*;

public class Map extends Observable {
	private boolean hasHunter = false;
	private Room[][] map;
	private String information;
	private boolean gg;
	int HunterR;
	int HunterC;
	int Row;
	int Col;
	int WumpusR;
	int WumpusC;

	public Map(int i, int j) {
		Row = i;
		Col = j;
		map = new Room[i][j];
		gg = false;
		information = "You are now in the World of Hunt the Wumps\n";
		this.setChanged();
		notifyObservers();
	}

	public String getin() {
		return information;
	}
	
	
	public Map(Situation[][] amap) {
		map = new Room[amap.length][amap[0].length];
		for (int i = 0; i < amap.length; i++) {
			for (int j = 0; j < amap[0].length; j++) {
				map[i][j] = new Room(amap[i][j]);
			}
		}
		Col = amap[0].length;
		Row = amap.length;
		information = "\nWelcome to Hunt the Wumpus!\nYou are in an empty room.\n";
		gg = false;
	}

	//////////

	public void setHunter(int A, int B) {
		HunterR = A;
		HunterC = B;
	}

	public void setWumpus(int i, int j) {
		WumpusR = i;
		WumpusC = j;
	}

	public Room[][] getmap() {
		return map;
	}

	public boolean GG() {
		return gg;
	}

	public int getRow() {
		return Row;
	}

	public int getCol() {
		return Col;
	}

	public int getHunterR() {
		return HunterR;
	}

	public int getHunterC() {
		return HunterC;
	}
	//////////

	public void Set() {
		for (int r = 0; r < Row; r++) {
			for (int c = 0; c < Col; c++) {
				map[r][c] = new Room(Situation.Empty);
				map[r][c].setR(r);
				map[r][c].setC(c);
			}
		}
		set();
		this.setChanged();
		notifyObservers();
	}// method to set the map and put all rooms empty

	private void set() {
		Random rand = new Random();

		// ***********************
		// set the wumpus
		int r = rand.nextInt(Row);
		int c = rand.nextInt(Col);
		map[r][c].setsituation(Situation.Wumpus);
		WumpusR = r;
		WumpusC = c;
		// The room wumpus in is empty

		// ***********************

		// ***********************
		// set bloods
		setBlood(map[wraparound(WumpusR - 2)][WumpusC]);// wraparound
		setBlood(map[wraparound(WumpusR - 1)][WumpusC]);
		setBlood(map[wraparound(WumpusR + 2)][WumpusC]);
		setBlood(map[wraparound(WumpusR + 1)][WumpusC]);

		setBlood(map[wraparound(WumpusR)][wraparound(WumpusC + 2)]);
		setBlood(map[wraparound(WumpusR)][wraparound(WumpusC - 2)]);
		setBlood(map[wraparound(WumpusR)][wraparound(WumpusC + 1)]);
		setBlood(map[wraparound(WumpusR)][wraparound(WumpusC - 1)]);

		setBlood(map[wraparound(WumpusR - 1)][wraparound(WumpusC - 1)]);
		setBlood(map[wraparound(WumpusR - 1)][wraparound(WumpusC + 1)]);
		setBlood(map[wraparound(WumpusR + 1)][wraparound(WumpusC - 1)]);
		setBlood(map[wraparound(WumpusR + 1)][wraparound(WumpusC + 1)]);

		// *******************************

		// ***********************
		// set pits and slimes
		int pitnumber = 3 + rand.nextInt(3);
		int count = 0;
		while (count < pitnumber) {
			int R = rand.nextInt(Row);
			int C = rand.nextInt(Col);

			if (map[R][C].isempty() || map[R][C].getSituation().equals(Situation.Slime.getSituation())) {
				map[R][C].setsituation(Situation.Pit); // set of one pit

				setslimeorgoop(map[wraparound(R - 1)][C]);// setsituation(Situation.Slime);
				setslimeorgoop(map[wraparound(R + 1)][C]);
				setslimeorgoop(map[R][wraparound(C + 1)]);
				setslimeorgoop(map[R][wraparound(C - 1)]);

				// The four slimes around
				count += 1;
			}

		} // End of setting of pit and 4 slimes around it
			// ***********************

		// ***********************
		// set a hunter
		while (!hasHunter) {
			int M = rand.nextInt(Row);
			int N = rand.nextInt(Row);
			if (map[M][N].isempty()) {
				HunterR = M;
				HunterC = N;
				hasHunter = true;
				map[M][N].setVisit(true);
			}

		} // while
			// ***********************

	}//

	// ############################
	// 1.setBlood
	private void setBlood(Room room) {
		room.setsituation(Situation.Blood);

	}
	// This function will set a room around a pit to slime or goop

	// 2.setslime or setgoop
	private void setslimeorgoop(Room room) {

		if (room.getSituation().equals("B"))
			room.setsituation(Situation.Goop);
		else
			room.setsituation(Situation.Slime);
	}
	// This function will set a room around a pit to slime or goop

	// 3.wraparound(int i)
	private int wraparound(int n) {
		if (n < 0)
			return n + 10;
		if (n > 9)
			return n - 10;
		return n;
	}// wraparound function

	// ##################################

	public boolean isHunter(int i, int j) {
		return (i == HunterR) && (j == HunterC);
	}
	
	public Room getHunter(){
		Room room=null;
		for(int i=0;i<Row;i++)
			for(int j=0;j<Col;j++)
		if(i==HunterR&&j==HunterC)
		room=map[i][j];
		return room;
	}
	
	public void shoot(String str) {
		gg = true;
		if (str.equals("north") || str.equals("south")) {
			if (HunterC == WumpusC)
				information = "Shoot the Wumpus! GG!\n";
			else
				information = "You killed yourself!\n So sad it's GG!\n";
		}
		if (str.equals("west") || str.equals("east")) {
			if (HunterR == WumpusR)
				information = "Shoot the Wumpus! GG!\n";
			else
				information = "You killed yourself!\n So sad it's GG!\n";
		}
		this.setChanged();
		notifyObservers();
	}

	public void move(String str) {
		int r = HunterR;
		int c = HunterC;
		if (str.equals("north")) {
			r = wraparound(HunterR - 1);
			HunterR = r;
		}
		if (str.equals("south")) {
			r = wraparound(HunterR + 1);
			HunterR = r;
		}
		if (str.equals("west")) {
			c = wraparound(HunterC - 1);
			HunterC = c;
		}
		if (str.equals("east")) {
			c = wraparound(HunterC + 1);
			HunterC = c;
		}

		if (map[HunterR][HunterC].getSituation().equals(" ")) {
			information = "You are in an Empty room right now.\n";

		}
		if (map[HunterR][HunterC].getSituation().equals("B")) {
			information = "There is some strange smell.\n Take care a wumpus is near.\n";

		}
		if (map[HunterR][HunterC].getSituation().equals("G")) {
			information = "You are in a goop.\nTake care a wumpus is near.\n";

		}
		if (map[HunterR][HunterC].getSituation().equals("S")) {
			information = "You are in a slime.\nTake care a pit is near.\n";

		}
		if (map[HunterR][HunterC].getSituation().equals("P")) {
			information = "You fell into a pit! GG!\n";
			gg = true;
			;
		}
		if (map[HunterR][HunterC].getSituation().equals("W")) {
			information = "The wumpus kills you! GG!\n";
			gg = true;
		}
		this.setChanged();
		notifyObservers();
	}

	// return a string of the map
	public String String() {
		if (gg) {
			for (int a = 0; a < Row; a++) {
				for (int b = 0; b < Col; b++) {
					map[a][b].setVisit(true);
				}
			}
		}

		String result = "";
		result += "************************************\n";
		for (int i = 0; i < Row; i++) {
			for (int j = 0; j < Col; j++) {
				result += "[" + roomString(i, j) + "]" + " ";
			}
			result += "\n";
		} //
		result += "************************************\n";
		result += information;
		result+="\n\n";
		return result;
	}

	public String roomString(int r, int c) {

		if (r == HunterR && c == HunterC) {
			map[r][c].setVisit(true);
			return "O";
		}
		if (map[r][c].isVisit())
			return map[r][c].getSituation();
		else
			return "X";
	}
	

}
