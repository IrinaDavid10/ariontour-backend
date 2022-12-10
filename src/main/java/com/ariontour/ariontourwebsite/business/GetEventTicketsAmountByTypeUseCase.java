package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.GetEventTicketsAmountByTypeResponse;

public interface GetEventTicketsAmountByTypeUseCase {
    GetEventTicketsAmountByTypeResponse getTicketsAmountByType(Long typeId, Long eventId);
}
