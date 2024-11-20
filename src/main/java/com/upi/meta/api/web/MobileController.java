package com.upi.meta.api.web;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.upi.meta.api.mobile.MobileEntity;
import com.upi.meta.api.mobile.MobileService;

@CrossOrigin
@RestController
public class MobileController {

	private static final Logger logger = LoggerFactory.getLogger(MobileController.class);

	@Autowired
	private MobileService mobileService;

	@PostMapping(path = "/mobile/register")
	public ResponseEntity<Object> registerNewDevice(@RequestBody MobileEntity mobileRequest) {
		try {
			logger.info("new mobile registration request received : {}", mobileRequest);
			return new ResponseEntity<>(mobileService.registerNewDevice(mobileRequest), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/mobile/authenticate")
	public ResponseEntity<Object> authenticateMobile(@RequestBody MobileEntity mobileRequest) {
		try {
			logger.info("Authenticate device for the Primary Mobile: {}", mobileRequest.getPrimaryMobile());
			MobileEntity existingDevice = mobileService.authenticateDevice(mobileRequest);

			if (Objects.nonNull(existingDevice)) {
				return new ResponseEntity<>(existingDevice, HttpStatus.OK);
			}

			return new ResponseEntity<>("Device doesn't exist", HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping(path = "/mobile/update")
	public ResponseEntity<Object> updateMobileDevice(@RequestBody MobileEntity mobileRequest) {
		try {
			logger.info("update mobile device for the Primary Mobile: {}", mobileRequest.getPrimaryMobile());
			MobileEntity existingDevice = mobileService.findByPrimaryOrSecondaryMobile(mobileRequest.getPrimaryMobile());

			if (Objects.nonNull(existingDevice)) {
				return new ResponseEntity<>(mobileService.updateDevice(mobileRequest), HttpStatus.OK);
			}

			return new ResponseEntity<>("Device doesn't exist", HttpStatus.NOT_FOUND);

		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/mobile/{mobileNo}")
	public ResponseEntity<Object> findByMobile(@PathVariable(required = true) String mobileNo) {
		try {
			logger.info("Find existing device for the Mobile No : {}", mobileNo);
			MobileEntity existingDevice = mobileService.findByPrimaryOrSecondaryMobile(mobileNo);

			if (Objects.nonNull(existingDevice)) {
				return new ResponseEntity<>(existingDevice, HttpStatus.OK);
			}

			return new ResponseEntity<>("Device doesn't exist", HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
