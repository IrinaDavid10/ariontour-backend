package com.ariontour.ariontourwebsite.persistance;

import com.ariontour.ariontourwebsite.persistance.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

}
