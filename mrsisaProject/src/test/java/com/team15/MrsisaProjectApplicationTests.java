package com.team15;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MrsisaProjectApplicationTests {

	@Test
	public void contextLoads() {

		assertTrue(2==3);
	}

	@Test
	public void passMatch() {
		assertTrue(2==2);
	}
}
