package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.exception.InvalidCountryException;
import com.ariontour.ariontourwebsite.business.exception.InvalidLocationException;
import com.ariontour.ariontourwebsite.domain.Event;
import com.ariontour.ariontourwebsite.domain.Location;
import com.ariontour.ariontourwebsite.persistance.entity.EventEntity;

import java.util.Optional;

public class EventConverter {


    public static Event convert(EventEntity event) {
        Location location = Optional.ofNullable(event.getLocation())
                .map(LocationConverter::convert)
                .orElseThrow(() -> new InvalidLocationException("404"));

        return Event.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .location(location)
                .dateTime(event.getDateTime())
                .build();
    }
}
