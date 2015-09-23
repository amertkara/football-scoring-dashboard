package com.amertkara.dashboard.data;

/**
 * Score class.
 * 
 * @author Mert Kara (amertkara@gmail.com)
 * @version 0.1.0
 * @since 0.1.0
 */
public class Score {
	private String playerName;
	private String time;
	
	public Score(String playerName, String time) {
		this.playerName = playerName;
		this.time = time;
	}

	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s'", playerName, time);
	}
}