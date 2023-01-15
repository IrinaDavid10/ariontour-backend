package com.ariontour.ariontourwebsite.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetBookedEventsResponse {
    private List<Event> events;
}
