package com.ariontour.ariontourwebsite.persistance;

import com.ariontour.ariontourwebsite.persistance.entity.TicketEnum;
import com.ariontour.ariontourwebsite.persistance.entity.TicketTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TicketTypeRepository extends JpaRepository<TicketTypeEntity, Long> {
     Optional<TicketTypeEntity> getByticketType(TicketEnum ticketType);
}
