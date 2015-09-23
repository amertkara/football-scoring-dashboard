package com.amertkara.dashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amertkara.dashboard.cli.CLIHandler;
import com.amertkara.dashboard.data.Game;

/**
 * Application configuration class.
 * 
 * @author Mert Kara (amertkara@gmail.com)
 * @version 0.1.0
 * @since 0.1.0
 */
@Configuration
public class DashboardConfig {

    @Bean
    public CLIHandler cLIHandler() {
        return new CLIHandler();
    }
    
    @Bean
    public Game game() {
    	return new Game();
    }
}
