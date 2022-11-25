package com.ariontour.ariontourwebsite.domain;

import com.ariontour.ariontourwebsite.persistance.entity.LocationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private Long id;
    private String title;
    private Location location;
    LocalDateTime dateTime;
    private String description;
}
