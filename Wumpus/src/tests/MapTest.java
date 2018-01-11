package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Map;
import model.Room;
import model.Situation;


//Description: This is the test of map class
public class MapTest {
	private static Situation E = Situation.Empty;
	private Situation B = Situation.Blood;
	private Situation G = Situation.Goop;
	private Situation P = Situation.Pit;
	private Situation S = Situation.Slime;
	private Situation W = Situation.Wumpus;

	@Test
	public void test() {
		int c = 0;
		Map map = new Map(10, 10);
		map.Set();
		assertEquals("You are now in the World of Hunt the Wumps\n", map.getin());

		for (int i = 0; i < map.getRow(); i++)
			for (int j = 0; j < map.getCol(); j++)
				if (map.getmap()[i][j].equals(S))
					c++;

		assertTrue((c >= 3 || c <= 5));
		assertFalse(map.GG());
		map.move("north");
		assertFalse(map.GG());
		map.move("south");
		assertEquals("You are in an Empty room right now.\n", map.getin());

		assertFalse(map.GG());
		map.move("east");
		assertFalse(map.GG());
		map.move("west");
		assertEquals("You are in an Empty room right now.\n", map.getin());
		assertFalse(map.GG());

		assertEquals(" ", map.getHunter().getSituation());

		Room hunter = map.getmap()[map.getHunterR()][map.getHunterC()];
		assertEquals(map.getHunter(), hunter);

		assertTrue(map.isHunter(map.getHunterR(), map.getHunterC()));

		assertTrue(hunter.isVisit());

		map.shoot("north");
		assertTrue(map.GG());
		// assertEquals("You killed yourself!\n So sad it's GG!\n",
		// map.getin());

		// assertEquals("");
	}

	@Test
	public void t() {
		Map map = new Map(10, 10);
		map.Set();
		map.shoot("south");
		assertTrue(map.GG());
		// assertEquals("You killed yourself!\n So sad it's GG!\n",
		// map.getin());

	}

	@Test
	public void e() {
		Map map = new Map(10, 10);
		map.Set();
		map.shoot("east");
		assertTrue(map.GG());
		// assertEquals("You killed yourself!\n So sad it's GG!\n",
		// map.getin());
	}

	@Test
	public void te() {
		Situation[][] room = { { E, E, E, S, E, E, E, E, E, E }, { E, E, S, P, S, E, E, E, B, E },
				{ E, E, E, S, E, E, E, B, B, B }, { B, E, S, P, S, E, B, B, W, B }, { E, E, E, S, E, E, E, B, B, B },
				{ E, E, E, E, E, E, E, E, B, E }, { E, E, E, E, E, E, E, E, E, S }, { S, E, E, E, E, E, E, E, S, P },
				{ E, E, E, E, E, E, E, E, E, S }, { E, E, E, E, E, E, E, E, E, E }, };

		Map map = new Map(room);

		map.setHunter(0, 0);
		assertTrue(map.isHunter(0, 0));

		map.move("east");// 0,1

		assertFalse(map.isHunter(0, 0));
		assertTrue(map.isHunter(0, 1));

		assertFalse(map.GG());
		assertEquals("You are in an Empty room right now.\n", map.getin());

		map.move("south");// 1,1
		map.move("east");// 1,2
		assertFalse(map.isHunter(0, 0));
		assertTrue(map.isHunter(1, 2));

		assertFalse(map.GG());
		assertEquals("You are in a slime.\nTake care a pit is near.\n", map.getin());

		map.move("east");// 1,3
		assertTrue(map.isHunter(1, 3));
		assertTrue(map.GG());
		assertEquals("You fell into a pit! GG!\n", map.getin());
	}

	@Test
	public void tet() {
		Situation[][] room = { { E, E, E, S, E, E, E, E, E, E }, { E, E, S, P, S, E, E, E, B, E },
				{ E, E, E, S, E, E, E, B, B, B }, { B, E, S, P, S, E, B, B, W, B }, { E, E, E, S, E, E, E, B, B, B },
				{ E, E, E, E, E, E, E, E, B, E }, { E, E, E, E, E, E, E, E, E, S }, { S, E, E, E, E, E, E, E, S, P },
				{ E, E, E, E, E, E, E, E, E, S }, { E, E, E, E, E, E, E, E, E, E }, };

		Map map = new Map(room);

		map.setHunter(2, 0);
		map.shoot("west");
		assertTrue(map.GG());
		assertEquals("You killed yourself!\n So sad it's GG!\n", map.getin());
	}

	@Test
	public void tett() {
		Situation[][] room = { { E, E, E, S, E, E, E, E, E, E }, { E, E, S, P, S, E, E, E, B, E },
				{ E, E, E, S, E, E, E, B, B, B }, { B, E, S, P, S, E, B, B, W, B }, { E, E, E, S, E, E, E, B, B, B },
				{ E, E, E, E, E, E, E, E, B, E }, { E, E, E, E, E, E, E, E, E, S }, { S, E, E, E, E, E, E, E, S, P },
				{ E, E, E, E, E, E, E, E, E, S }, { E, E, E, E, E, E, E, E, E, E }, };

		Map map = new Map(room);

		map.setHunter(8, 0);
		map.shoot("south");
		assertTrue(map.GG());
		assertEquals("Shoot the Wumpus! GG!\n", map.getin());
	}

	@Test
	public void tette() {
		Situation[][] room = { { E, E, E, S, E, E, E, E, E, E }, { E, E, S, P, S, E, E, E, B, E },
				{ E, E, E, S, E, E, E, B, B, B }, { B, E, S, P, S, E, B, B, W, B }, { E, E, E, S, E, E, E, B, B, B },
				{ E, E, E, E, E, E, E, E, B, E }, { E, E, E, E, E, E, E, E, E, S }, { S, E, E, E, E, E, E, E, S, P },
				{ E, E, E, E, E, E, E, E, E, S }, { E, E, E, E, E, E, E, E, E, E }, };

		Map map = new Map(room);

		map.setHunter(8, 0);
		map.shoot("north");
		assertTrue(map.GG());
		assertEquals("Shoot the Wumpus! GG!\n", map.getin());
	}

	@Test
	public void tettet() {
		Situation[][] room = { { E, E, E, S, E, E, E, E, E, E }, { E, E, S, P, S, E, E, E, B, E },
				{ E, E, E, S, E, E, E, B, B, B }, { B, E, S, P, S, E, B, B, W, B }, { E, E, E, S, E, E, E, B, B, B },
				{ E, E, E, E, E, E, E, E, B, E }, { E, E, E, E, E, E, E, E, E, S }, { S, E, E, E, E, E, E, E, S, P },
				{ E, E, E, E, E, E, E, E, E, S }, { E, E, E, E, E, E, E, E, E, E }, };

		Map map = new Map(room);

		map.setHunter(2, 0);
		map.move("south");

		assertEquals("There is some strange smell.\n Take care a wumpus is near.\n", map.getin());

		map.shoot("south");
		assertEquals("Shoot the Wumpus! GG!\n", map.getin());
		assertTrue(map.GG());
	}

	@Test
	public void tettete() {
		Situation[][] room = { { E, E, E, S, E, E, E, E, E, E }, { E, E, S, P, S, E, E, E, B, E },
				{ E, E, E, S, E, E, E, B, B, B }, { B, E, S, P, S, E, B, B, W, B }, { E, E, E, S, E, E, E, B, B, B },
				{ E, E, E, E, E, E, E, E, B, E }, { E, E, E, E, E, E, E, E, E, S }, { S, E, E, E, E, E, E, E, S, P },
				{ E, E, E, E, E, E, E, E, E, S }, { E, E, E, E, E, E, E, E, E, E }, };

		Map map = new Map(room);

		assertEquals("************************************\n" + "[O] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n" + "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n" + "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n" + "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n" + "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n" + "************************************\n"
				+ "\nWelcome to Hunt the Wumpus!\nYou are in an empty room.\n" + "\n\n", map.String());

		assertEquals("X", map.roomString(2, 0));
		map.setHunter(1, 0);
		assertEquals("O", map.roomString(1, 0));

		map.move("south");

		assertEquals(" ", map.roomString(1, 0));
		assertEquals("O", map.roomString(2, 0));

		assertEquals("************************************\n" + "[ ] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[ ] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n" + "[O] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n" + "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n" + "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n" + "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n" + "************************************\n"
				+ "You are in an Empty room right now.\n" + "\n\n", map.String());

	}
}
