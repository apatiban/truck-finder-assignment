package com.tf.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.tf.app.exception.DataParserException;
import com.tf.app.exception.TruckRepoNotFoundException;
import com.tf.app.service.TestService;
import com.tf.app.service.TruckFinderService;
import com.tf.data.Truck;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TruckFinderServiceApplicationTests {

	@Autowired
	private TruckFinderService ts;

	@Test
	void contextLoads() {
		List<Truck> trucks = null;
		try {
			trucks = ts.getTrucks(37.78520535858918, -122.41594524663745);
		} catch (DataParserException e) {
			e.printStackTrace();
		} catch (TruckRepoNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(5, trucks.size());
	}

}
