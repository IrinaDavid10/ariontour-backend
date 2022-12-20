package com.ariontour.ariontourwebsite.persistance.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "support_chat")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LiveSupportChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column (name = "subject")
    private String subject;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private UserEntity admin;

}
