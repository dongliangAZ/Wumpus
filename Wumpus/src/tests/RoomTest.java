package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Room;
import model.Situation;


//Description: This is the test of room class
public class RoomTest {

	@Test
	public void test() {
		Room a = new Room(Situation.Empty);
		assertEquals(false, a.isVisit());
		assertEquals(" ", a.getSituation());
		assertEquals(true, a.isempty());
		a.setsituation(Situation.Blood);
		assertEquals("B", a.getSituation());
		assertEquals(false, a.isempty());
		a.setVisit(true);
		assertEquals(true, a.isVisit());

		Room b = new Room(Situation.Goop);
		Room c = new Room(Situation.Pit);
		Room d = new Room(Situation.Slime);
		Room e = new Room(Situation.Wumpus);

		assertEquals("G", b.getSituation());
		assertEquals("P", c.getSituation());
		assertEquals("S", d.getSituation());
		assertEquals("W", e.getSituation());
	}

}
