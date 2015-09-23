package com.amertkara.dashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.amertkara.dashboard.cli.CLIHandler;
import com.amertkara.dashboard.data.Game;

/**
 * Application test configuration class.
 * 
 * @author Mert Kara (amertkara@gmail.com)
 * @version 0.1.0
 * @since 0.1.0
 */
@Configuration
public class TestConfig {

    @Bean
    @Scope("prototype")
    public Game game() {
    	return new Game();
    }
    
    @Bean
    public CLIHandler cLIHandler() {
        return new CLIHandler();
    }
}
