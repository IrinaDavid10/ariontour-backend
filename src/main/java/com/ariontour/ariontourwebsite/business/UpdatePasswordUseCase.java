package com.ariontour.ariontourwebsite.business;

import com.ariontour.ariontourwebsite.domain.UpdatePasswordRequest;
import com.ariontour.ariontourwebsite.domain.UpdatePasswordResponse;

public interface UpdatePasswordUseCase {

    UpdatePasswordResponse updatePassword(UpdatePasswordRequest request);
}
