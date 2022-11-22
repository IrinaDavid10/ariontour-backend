package com.ariontour.ariontourwebsite.persistance;

import com.ariontour.ariontourwebsite.persistance.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
}
