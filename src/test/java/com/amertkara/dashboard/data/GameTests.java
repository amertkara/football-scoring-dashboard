package com.amertkara.dashboard.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amertkara.dashboard.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfig.class)
public class GameTests {
	
	@Autowired
	public Game game;
	
	private static String TEAM1 = "team1";
	private static String TEAM2 = "team2";
	private static String TEAM3 = "team3";
	private static String PLAYER1 = "player1";

	@Test
	public void testStart() throws Exception {
		try {
			game.start(TEAM1, TEAM1);
			TestCase.fail("Same team names should cause a fail!");
		} catch (IllegalArgumentException iae) {
			System.out.println("Expected: " + iae.getMessage());
		}
		
		// When there is no game, the object should print empty string
		assertEquals("", game.toString());
		
		game.start(TEAM1, TEAM2);
		
		// Check if the teams are set
		assertNotNull(game.getHomeTeam());
		assertNotNull(game.getAwayTeam());
	}

	@Test
	public void testUpdateScore() throws Exception {
		game.start(TEAM1, TEAM2);
		
		try {
			game.updateScore("110", TEAM3, PLAYER1);
			TestCase.fail("Non existent team name should cause a fail!");
		} catch (IllegalArgumentException iae) {
			System.out.println("Expected: " + iae.getMessage());
		}
		
		game.updateScore("110", TEAM1, PLAYER1);
		
		assertEquals(1, game.getHomeTeam().getScores().size());
	}
	
	@Test
	public void testFinish() throws Exception {
		game.start(TEAM1, TEAM2);
		assertNotNull(game.getHomeTeam());
		
		game.finish();
		assertNull(game.getHomeTeam());
	}
}
