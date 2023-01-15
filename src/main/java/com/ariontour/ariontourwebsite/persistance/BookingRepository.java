package com.ariontour.ariontourwebsite.persistance;

import com.ariontour.ariontourwebsite.persistance.entity.BookingEntity;
import com.ariontour.ariontourwebsite.persistance.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    @Query("SELECT b FROM BookingEntity b JOIN b.ticket t WHERE t.event.id = :eventId")
    List<BookingEntity> findAllByTicketEventId(@Param("eventId") Long eventId);
    @Query("SELECT b FROM BookingEntity b JOIN b.ticket t JOIN b.customer c WHERE t.event.id = :eventId AND c.id = :customerId")
    List<BookingEntity> findAllByTicketEventIdAndCustomerId(@Param("eventId") Long eventId, @Param("customerId") Long customerId);

    @Query("SELECT DISTINCT t.event FROM BookingEntity b JOIN b.ticket t JOIN b.customer c WHERE c.id = :customerId")
    List<EventEntity> findAllEventsInBookingByCustomerId(@Param("customerId") Long customerId);

}
