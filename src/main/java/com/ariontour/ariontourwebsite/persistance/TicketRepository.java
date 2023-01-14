package com.ariontour.ariontourwebsite.persistance;

import com.ariontour.ariontourwebsite.persistance.entity.TicketEntity;
import com.ariontour.ariontourwebsite.persistance.entity.TicketEnum;
import com.ariontour.ariontourwebsite.persistance.entity.TicketTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    @Query("SELECT COUNT (t) FROM TicketEntity t WHERE t.ticketType.id=:ticket_type_id AND t.event.id=:event_id")
    Long AmountOfTicketsByType(@Param("ticket_type_id") Long ticket_type_id, @Param("event_id") Long event_id);
    List<TicketEntity> findByTicketType(TicketTypeEntity ticketType);
    List<TicketEntity> findAllByEventId(Long eventId);
    List<TicketEntity> findAllByTicketTypeAndEventId(TicketTypeEntity ticketType, Long eventId);

}
