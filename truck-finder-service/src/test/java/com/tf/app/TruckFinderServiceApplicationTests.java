package com.tf.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.tf.app.exception.DataParserException;
import com.tf.app.exception.TruckRepoNotFoundException;
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
	void testFromFixedCorodinates() {
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

	@Test
	void testFromWrongCorodinates() {
		List<Truck> trucks = null;
		try {
			trucks = ts.getTrucks(45454D, 43434D);
		} catch (DataParserException e) {
			e.printStackTrace();
		} catch (TruckRepoNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(0, trucks.size());
	}

}
