package com.amertkara.dashboard.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CLI Utility functions.
 * 
 * @author Mert Kara (amertkara@gmail.com)
 * @version 0.1.0
 * @since 0.1.0
 */
public class CLIUtils {

    public final static Pattern START_GAME_PATTERN = Pattern.compile(CLIConstants.REGEX_START_GAME);
    public final static Pattern SCORE_UPDATE_PATTERN = Pattern.compile(CLIConstants.REGEX_SCORE_UPDATE);
    
    /**
     * Parses the provided string and extracts the team names.
     * 
     * @param input Provided string {@see CLIConstants#REGEX_START_GAME}
     * @return A list of team names in the order of homeTeam and awayTeam
     * @throws IllegalStateException It occurs if the provided input doesn't 
     * satisfy the expected format.
     */
    public static List<String> getTeamsFromInput(String input) throws IllegalStateException {
    	List<String> result = new ArrayList<String>();
    	Matcher matcher = START_GAME_PATTERN.matcher(input);
    	matcher.find();
    	result.add(matcher.group(1));
    	result.add(matcher.group(2));
    	
    	return result;
    }
    
    /**
     * Parses the provided string and extracts the score update details.
     * 
     * @param input Provided string {@see CLIConstants#REGEX_SCORE_UPDATE}
     * @return A list of time, team and player (in the same order)
     * @throws IllegalStateException It occurs if the provided input doesn't 
     * satisfy the expected format.
     */
    public static List<String> getScoreUpdateFromInput(String input) throws IllegalStateException {
    	List<String> result = new ArrayList<String>();
    	Matcher matcher = SCORE_UPDATE_PATTERN.matcher(input);
    	matcher.find();
    	result.add(matcher.group(1));
    	result.add(matcher.group(2));
    	result.add(matcher.group(3));
    	
    	return result;
    }
    
    /**
     * Processes the provided input and determines the command. It also outputs
     * corresponding error statement to STDERR.
     * 
     * ASSUMPTION: RE. Requirement 5.a "If the Football Scoring Dashboard is given 
     * any commands while a game is not in progress it should report 'No game 
     * currently in progress'.";
     * 	"any commands" in the requirement is assumed to exclude the Start game
     *  command because that command is executed when there are no games in 
     *  progress. Therefore, if the user issues "Print", "End" or "<minute> 
     *  '<Team>' <name of scorer>" and there are no games, it will output "No 
     *  game currently in progress"
     * 
     * @param input Provided string
     * @return Returns the command. If the assumption case holds, it will return
     * unknown command.
     */
    public static CLICommands getCommand(String input, CLIStatuses status) {
    	@SuppressWarnings("unused")
		List<String> dummy;
    	CLICommands result = CLICommands.UNKNOWN;
    	
    	if (input.equals(CLIConstants.PRINT_SCORE)) {
    		result = CLICommands.PRINT;
    	} else if (input.equals(CLIConstants.FINISH_GAME)) {
    		result = CLICommands.FINISH;
    	} else {
        	try {
        		dummy = getScoreUpdateFromInput(input);
        		result = CLICommands.UPDATE;
        	} catch (IllegalStateException ise) {}
    	}
    	/*
    	 *  The input is a known command (except Start game) but there is no
    	 *  game
    	 */
    	if (!result.equals(CLICommands.UNKNOWN) && status.equals(CLIStatuses.IDLE)) {
    		System.err.println(CLIConstants.ERROR_NO_GAME_IP);
    		return CLICommands.UNKNOWN;
    	}
    	
    	/*
    	 *  The input is an unknown command (except Start game) but there is a 
    	 *  game in progress
    	 */
    	if (result.equals(CLICommands.UNKNOWN) && status.equals(CLIStatuses.GAME_IN_PROGRESS)) {
    		System.err.println(CLIConstants.ERROR_PRINT_GAME_DETAILS);
    	}
    	
    	if (!status.equals(CLIStatuses.GAME_IN_PROGRESS)) {
        	try {
        		dummy = getTeamsFromInput(input);
        		result = CLICommands.START;
        	} catch (IllegalStateException ise) {
        		if (result.equals(CLICommands.UNKNOWN) && status.equals(CLIStatuses.IDLE)) {
        			// The command is unknown
        			System.err.println(CLIConstants.ERROR_START_GAME );
        		}
        	}
    	}
    	
    	return result;
    }
}
