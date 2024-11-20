package com.upi.meta.api.mobile;

import org.springframework.data.repository.CrudRepository;

public interface MobileRepository extends CrudRepository<MobileEntity, String> {

	MobileEntity findByPrimaryMobileAndDob(String primaryMobile, String dob);

	MobileEntity findByPrimaryMobileOrSecondaryMobile(String primaryMobile, String secondaryMobile);

	MobileEntity findByPrimaryMobile(String primaryMobile);

	MobileEntity findBySecondaryMobile(String secondaryMobile);

}
