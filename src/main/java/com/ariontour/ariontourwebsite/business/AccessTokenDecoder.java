package com.ariontour.ariontourwebsite.business;


import com.ariontour.ariontourwebsite.domain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
