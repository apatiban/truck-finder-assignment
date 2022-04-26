package com.tf.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tf.app.service.TestService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TruckFinderServiceApplicationTests {

	@Autowired
	private TestService ts;

	@Test
	void contextLoads() {
		assertEquals("ppp", ts.ping());
	}

}
