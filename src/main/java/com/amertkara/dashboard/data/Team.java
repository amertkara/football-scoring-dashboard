package com.amertkara.dashboard.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Team class.
 * 
 * @author Mert Kara (amertkara@gmail.com)
 * @version 0.1.0
 * @since 0.1.0
 */
public class Team {
	private String teamName;
	private List<Score> scores = new ArrayList<Score>();
	
	public Team(String teamName) {
		this.teamName = teamName; 
	}
	
	public void setScore(String playerName, String time) {
		scores.add(new Score(playerName, time));
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @return the scores
	 */
	public List<Score> getScores() {
		return scores;
	}

	@Override
	public String toString() {
		Integer scoreCount = Integer.valueOf(scores.size());
		String scoresString = "";
		
		scoresString = "(";
		for (Score score : scores) {
			scoresString += score.toString() + " ";
		}
		scoresString = scoresString.trim() + ")";
		
		if (scoreCount.intValue() > 0) {
			return String.format("%s %d %s", teamName, scoreCount, scoresString);
		} else {
			return String.format("%s %d", teamName, scoreCount);
		}

		
	}
}
