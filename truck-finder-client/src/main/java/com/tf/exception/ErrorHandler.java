package com.tf.exception;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class ErrorHandler implements ResponseErrorHandler {

	private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {

		return (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR
				|| httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {

		log.error("Http error code = {} ", httpResponse.getStatusCode());

	}

}
