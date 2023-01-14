package com.ariontour.ariontourwebsite.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class CreateBookingResponse {
    private List<Long> bookingIds;
}
