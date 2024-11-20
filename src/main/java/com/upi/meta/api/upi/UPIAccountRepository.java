package com.upi.meta.api.upi;

import org.springframework.data.repository.CrudRepository;

public interface UPIAccountRepository extends CrudRepository<UPIAccountEntity, String> {

	UPIAccountEntity findByMobileNo(String mobileNo);

	UPIAccountEntity findByVpa(String vpa);

	UPIAccountEntity findByVpaAndUpiPin(String vpa, String upiPin);

}
