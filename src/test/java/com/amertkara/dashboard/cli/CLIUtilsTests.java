package com.amertkara.dashboard.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amertkara.dashboard.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
public class CLIUtilsTests {
	
	@Autowired
	public CLIHandler cLIHandler;
	
	@Test
	public void testgetTeamsFromInput() throws Exception {
		
	}
}
