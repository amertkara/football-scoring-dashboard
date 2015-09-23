package com.amertkara.dashboard.data;

import org.springframework.stereotype.Component;

import com.amertkara.dashboard.cli.CLIConstants;

/**
 * Game class encapsulates the game data/logic.
 * 
 * @author Mert Kara (amertkara@gmail.com)
 * @version 0.1.0
 * @since 0.1.0
 */
@Component
public class Game {
	private Team homeTeam;
	private Team awayTeam;
	
	public Game() {}
	
	/**
	 * Starts the game.
	 * 
	 * ASSUMPTION: team names cannot be same.
	 * 
	 * @param homeTeamName Home team name, it is the team on the left in the
	 * score board.
	 * @param awayTeamName Away team name, it is the team on the right in the
	 * score board.
	 * @throws IllegalArgumentException If the team names are same.
	 */
	public void start(String homeTeamName, String awayTeamName) throws IllegalArgumentException {
		if (homeTeamName.equals(awayTeamName)) {
			throw new IllegalArgumentException(CLIConstants.ERROR_TEAMS_SAME);
		}
		
		homeTeam = new Team(homeTeamName);
		awayTeam = new Team(awayTeamName);
		System.out.println(String.format(CLIConstants.GAME_STARTED, homeTeamName, awayTeamName));
	}
	
	/**
	 * Updates the score.
	 * 
	 * @param time Time of the goal.
	 * @param teamName Name of the team.
	 * @param playerName Name of the player who scored.
	 * @throws IllegalArgumentException If the team name doesn't match any of
	 * the teams.
	 */
	public void updateScore(String time, String teamName, String playerName) throws IllegalArgumentException {
		if (!teamName.equals(homeTeam.getTeamName()) && !teamName.equals(awayTeam.getTeamName())) {
			throw new IllegalArgumentException(CLIConstants.ERROR_TEAM_NO_MATCH);
		} else if (teamName.equals(homeTeam.getTeamName())) {
			homeTeam.setScore(playerName, time);
		} else {
			awayTeam.setScore(playerName, time);
		}
	}
	
	/**
	 * Finishes the game.
	 */
	public void finish() {
		System.out.println(String.format(CLIConstants.GAME_FINISHED, homeTeam.getTeamName(), awayTeam.getTeamName(), toString()));
		homeTeam = null;
		awayTeam = null;
	}
	
	@Override
	public String toString() {
		if (homeTeam != null && awayTeam != null) {
			return String.format("%s vs. %s", homeTeam.toString(), awayTeam.toString());
		} else {
			return "";
		}
	}
	
	/**
	 * @return the homeTeam
	 */
	public Team getHomeTeam() {
		return homeTeam;
	}

	/**
	 * @return the awayTeam
	 */
	public Team getAwayTeam() {
		return awayTeam;
	}
}
