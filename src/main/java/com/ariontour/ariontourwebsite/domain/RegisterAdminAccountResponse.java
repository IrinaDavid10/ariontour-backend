package com.ariontour.ariontourwebsite.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterAdminAccountResponse {
    private Long adminId;
}
