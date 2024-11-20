package com.upi.meta.api.upi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UPIAccountService {

	@Autowired
	private UPIAccountRepository upiAccountRepository;

	public UPIAccountEntity registerNewAccount(UPIAccountEntity accountRequest) {
		return upiAccountRepository.save(accountRequest);
	}

	public UPIAccountEntity findByMobileNo(String mobileNo) {
		return upiAccountRepository.findByMobileNo(mobileNo);
	}

	public UPIAccountEntity findByVpa(String vpa) {
		return upiAccountRepository.findByVpa(vpa);
	}

	public UPIAccountEntity authenticatePin(UPIAccountEntity pinRequest) {
		return upiAccountRepository.findByVpaAndUpiPin(pinRequest.getVpa(), pinRequest.getUpiPin());
	}

}
