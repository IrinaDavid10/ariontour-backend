package com.ariontour.ariontourwebsite.business;


import com.ariontour.ariontourwebsite.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
