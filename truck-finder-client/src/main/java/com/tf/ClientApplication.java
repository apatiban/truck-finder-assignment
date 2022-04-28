package com.tf;

import com.tf.entity.TruckResponsePayload;
import com.tf.util.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication implements ApplicationRunner {

	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (!isValidInput(args))
			System.exit(0);

		TruckResponsePayload payLoad = restTemplate.getForObject("http://localhost:8080/trucks?" + getAllParams(args),
				TruckResponsePayload.class);
		if (payLoad != null) {
			if (payLoad.getCount() > 0) {
				System.out.println(payLoad);
			} else {
				System.out.println(payLoad.getErrorDetails());
			}

		}
		System.exit(0);
	}

	private String getAllParams(ApplicationArguments args) {
		return "latitude=" + getParamString("latitude", args) + "&" + "longitude="
				+ getParamString("longitude", args);
	}

	private String getParamString(String paramName, ApplicationArguments args) {
		if (args.containsOption(paramName)) {
			return args.getOptionValues(paramName).get(0);
		}
		return null;
	}

	private boolean isValidInput(ApplicationArguments args) {
		String latitude = null;
		String longitude = null;
		if (!args.containsOption("latitude")) {
			System.out.println("No Latitude parameter specified");
			return false;
		} else if (!args.containsOption("longitude")) {
			System.out.println("No longitude parameter specified");
			return false;
		}
		latitude = getParamString("latitude", args);
		longitude = getParamString("longitude", args);
		if (latitude.isEmpty()) {
			System.out.println("No Latitude parameter specified");
			return false;
		} else if (longitude.isEmpty()) {
			System.out.println("No longitude parameter specified");
			return false;
		} else if (Double.isNaN(Utils.getDouble(latitude))) {
			System.out.println("Not a valid latitude");
			return false;
		} else if (Double.isNaN(Utils.getDouble(longitude))) {
			System.out.println("Not a valid longitude");
			return false;

		} else
			return true;
	}

}
