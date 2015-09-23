package com.amertkara.dashboard.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.amertkara.dashboard.data.Game;

/**
 * A command line runner class that executes the initial business requirements.
 * 
 * @author Mert Kara (amertkara@gmail.com)
 * @version 0.1.0
 * @since 0.1.0
 */
@Component
public class CLIHandler implements CommandLineRunner {

	private CLIStatuses status = CLIStatuses.IDLE;
    
    @Autowired
    public Game game;
    
    /**
     * Highest precedence is given to make sure that this runner is executed 
     * first.
     * 
     * {@inheritDoc}
     */
    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void run(String... args) throws Exception {
        String input;
        
        // Greet the user
        System.out.println(CLIConstants.GREETING);
        
        // Bind a reader to the STDIN
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Enter the read command line loop
        try {
            while((input=br.readLine()) != null) {
            	CLICommands command = CLIUtils.getCommand(input, status);
            	switch (command) {
					case START:
						List<String> teamNames = CLIUtils.getTeamsFromInput(input);
						try {
							game.start(teamNames.get(0), teamNames.get(1));
						} catch (IllegalArgumentException iae) {
							System.err.println(iae.getMessage());
							break;
						}
						
						// Finally set the dashboard status
						status = CLIStatuses.GAME_IN_PROGRESS;
						break;
					
					case UPDATE:
						List<String> scoreUpdate = CLIUtils.getScoreUpdateFromInput(input);
						try {
							game.updateScore(scoreUpdate.get(0), scoreUpdate.get(1), scoreUpdate.get(2));
						} catch (IllegalArgumentException iae) {
							System.err.println(iae.getMessage());
						}
						break;
						
					case PRINT:
						System.out.println(game.toString());
						break;
						
					case FINISH:
						game.finish();
						// Finally set the dashboard status
						status = CLIStatuses.IDLE;
						break;
	
					default:
						break;
				}
            }  
        } catch (IOException ioe) {
            System.err.println(CLIConstants.ERROR_STD_IO);
        }
    }
}
