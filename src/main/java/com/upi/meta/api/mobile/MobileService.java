package com.upi.meta.api.mobile;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upi.meta.api.utils.CommonUtils;

@Service
public class MobileService {

	@Autowired
	private MobileRepository mobileRepository;

	public MobileEntity registerNewDevice(MobileEntity mobileRequest) {
		mobileRequest.setDob(CommonUtils.converCalenderDateToReadable(mobileRequest.getDob()));
		System.out.println("selectedDob ==" + mobileRequest);
		return mobileRepository.save(mobileRequest);
	}

	public MobileEntity authenticateDevice(MobileEntity mobileRequest) {
		String selectedDob = CommonUtils.converCalenderDateToReadable(mobileRequest.getDob());
		return mobileRepository.findByPrimaryMobileAndDob(mobileRequest.getPrimaryMobile(), selectedDob);
	}

	public MobileEntity findByPrimaryOrSecondaryMobile(String mobileNo) {
		return mobileRepository.findByPrimaryMobileOrSecondaryMobile(mobileNo, mobileNo);
	}

	public MobileEntity updateDevice(MobileEntity mobileRequest) {
		MobileEntity existingDevice = findByPrimaryOrSecondaryMobile(mobileRequest.getPrimaryMobile());

		if (Objects.nonNull(existingDevice)) {
			existingDevice.setSecondaryMobile(mobileRequest.getSecondaryMobile());
			existingDevice.setSecondaryNetworkProvider(mobileRequest.getSecondaryNetworkProvider());
			existingDevice.setSecondaryImei(CommonUtils.generateImei());
			mobileRepository.save(existingDevice);
		}

		return existingDevice;
	}
}
