package com.ariontour.ariontourwebsite.persistance;

import com.ariontour.ariontourwebsite.persistance.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    boolean existsByCityCityNameAndCountryCountryCode(String city_name, String country_code);
    LocationEntity findByCityCityNameAndCountryCountryCode(String city_name, String country_code);

}
