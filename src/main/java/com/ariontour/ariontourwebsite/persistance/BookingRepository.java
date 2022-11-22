package com.ariontour.ariontourwebsite.persistance;

public interface BookingRepository {
    boolean existsByUsername(String username);
}
