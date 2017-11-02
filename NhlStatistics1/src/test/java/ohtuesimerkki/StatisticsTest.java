package ohtuesimerkki;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchForUserThatExistsReturnsPlayer() {
        Player p = stats.search("Lemieux");
        assertNotNull(p);
    }

    @Test
    public void searchForUserThatDoesNotExistReturnsNull() {
        assertNull(stats.search("Does not exist"));
    }

    @Test
    public void existingTeamReturnsNonEmptyList() {
        List<Player> players = stats.team("EDM");
        assertNotEquals(0, players.size());
    }

    @Test
    public void topScorersReturnsListOfCorrectLength() {
        assertEquals(1, stats.topScorers(1).size());
    }

    @Test
    public void topScorersReturnsCorrectPlayer() {
        Player p = stats.topScorers(1).get(0);
        assertEquals("Gretzky", p.getName());
    }

}
