package com.amertkara.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amertkara.dashboard.cli.CLIHandler;

/**
 * Application main class. Once the application starts running, CommandLine
 * runners are executed. 
 * 
 * Currently only cLIHandler bean is executed. 
 * 
 * @author Mert Kara (amertkara@gmail.com)
 * @version 0.1.0
 * @since 0.1.0
 */
@SpringBootApplication
public class Dashboard {
    
    @Autowired
    public CLIHandler cLIHandler;

    /**
     * Entrance point.
     * 
     * @param args void
     */
    public static void main(String[] args) {
        SpringApplication.run(Dashboard.class, args);
    }
}
