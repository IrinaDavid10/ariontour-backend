package com.ariontour.ariontourwebsite.persistance.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ticket_type")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "type_name")
    @Enumerated(EnumType.STRING)
    private TicketEnum ticketType;

}
