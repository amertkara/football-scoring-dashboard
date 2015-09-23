package com.amertkara.dashboard.cli;

import static org.junit.Assert.assertEquals;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CLIUtilsTests {
	
	@Autowired
	public CLIHandler cLIHandler;
	
	protected final static String MALFORMED_START_NO_TEAM_NAME = "Start: 'a' vs. ''";
	protected final static String PROPER_START = "Start: 'a' vs. 'b'";
	protected final static String MALFORMED_UPDATE_NO_TIME = " 't' p";
	protected final static String MALFORMED_UPDATE_OVER_TIME = "131 't' p";
	protected final static String PROPER_UPDATE = "87 't' p";
	
	@Test
	public void testGetTeamsFromInput() throws Exception {
		try {
			CLIUtils.getTeamsFromInput(MALFORMED_START_NO_TEAM_NAME);
			TestCase.fail("Empty team name should cause a fail!");
		} catch (IllegalStateException ise) {
			System.out.println("Expected: " + ise.getMessage());
		}
		
		List<String> teamNames = CLIUtils.getTeamsFromInput(PROPER_START);
		assertEquals(2, teamNames.size());
		assertEquals("a", teamNames.get(0));
		assertEquals("b", teamNames.get(1));
	}
	
	@Test
	public void testGetScoreUpdateFromInput() throws Exception {
		try {
			CLIUtils.getTeamsFromInput(MALFORMED_UPDATE_NO_TIME);
			TestCase.fail("Empty time should cause a fail!");
		} catch (IllegalStateException ise) {
			System.out.println("Expected: " + ise.getMessage());
		}
		
		try {
			CLIUtils.getTeamsFromInput(MALFORMED_UPDATE_OVER_TIME);
			TestCase.fail("Time over 130 should cause a fail!");
		} catch (IllegalStateException ise) {
			System.out.println("Expected: " + ise.getMessage());
		}
		
		List<String> updateDetails = CLIUtils.getScoreUpdateFromInput(PROPER_UPDATE);
		assertEquals(3, updateDetails.size());
		assertEquals("87", updateDetails.get(0));
		assertEquals("t", updateDetails.get(1));
		assertEquals("p", updateDetails.get(2));
	}
	
	@Test
	public void testGetCommand() throws Exception {
		CLICommands command = CLIUtils.getCommand("end", CLIStatuses.IDLE);
		assertEquals(CLICommands.UNKNOWN, command);
		
		command = CLIUtils.getCommand("End", CLIStatuses.IDLE);
		assertEquals(CLICommands.UNKNOWN, command);
		
		command = CLIUtils.getCommand("print", CLIStatuses.IDLE);
		assertEquals(CLICommands.UNKNOWN, command);
		
		command = CLIUtils.getCommand(PROPER_UPDATE, CLIStatuses.IDLE);
		assertEquals(CLICommands.UNKNOWN, command);
		
		command = CLIUtils.getCommand(PROPER_START, CLIStatuses.GAME_IN_PROGRESS);
		assertEquals(CLICommands.UNKNOWN, command);
		
		command = CLIUtils.getCommand(PROPER_START, CLIStatuses.IDLE);
		assertEquals(CLICommands.START, command);
		
		command = CLIUtils.getCommand("End", CLIStatuses.GAME_IN_PROGRESS);
		assertEquals(CLICommands.FINISH, command);
		
		command = CLIUtils.getCommand("print", CLIStatuses.GAME_IN_PROGRESS);
		assertEquals(CLICommands.PRINT, command);
		
		command = CLIUtils.getCommand(PROPER_UPDATE, CLIStatuses.GAME_IN_PROGRESS);
		assertEquals(CLICommands.UPDATE, command);
	}
}
