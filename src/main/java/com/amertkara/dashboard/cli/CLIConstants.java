package com.amertkara.dashboard.cli;

/**
 * Constants (incl. regular expressions) are kept here. They are roughly grouped
 * by using a GROUP_CONSTANTNAME convention.
 * 
 * @author Mert Kara (amertkara@gmail.com)
 * @version 0.1.0
 * @since 0.1.0
 */
public class CLIConstants {
    public static String REGEX_START_GAME = "^Start: '([a-zA-Z]+)' vs. '([a-zA-Z]+)'$";
    /*
     * A football game can last maximum 120 minutes, i.e., a final game. 
     * 2 45-minute halves and 2 15-minute halves for the extended period. 
     * To accommodate exceptions, the maximum allowed minute is 130 minutes.
     */
    public static String REGEX_SCORE_UPDATE = "^([0-9]{1,2}|1[0-2]{1}[0-9]{1}|130) '([a-zA-Z]+)' ([a-zA-Z]+)$";
    public static String FINISH_GAME = "End";
    public static String PRINT_SCORE = "print";
    public static String ERROR_START_GAME = "input error - please start a "
    		+ "game through typing 'Start: '<Name of "
			+ "Home Team>' vs. '<Name of Away Team>'";
    public static String ERROR_PRINT_GAME_DETAILS = "input error - please type "
    		+ "'print' for game details'";
    public static String ERROR_STD_IO = "An input error occured, quitting.";
    public static String ERROR_NO_GAME_IP = "No game currently in progress";
    public static String ERROR_TEAMS_SAME = "Team names cannot be same.";
    public static String ERROR_TEAM_NO_MATCH = "Team name does not match any of the teams.";
    public static String GREETING = "Football Scoring Dashboard Application. "
    		+ "\nPlease enter your command";
    public static String GAME_STARTED = "Game between %s and %s started.";
    public static String GAME_FINISHED = "Game between %s and %s finished,\n%s";
}
