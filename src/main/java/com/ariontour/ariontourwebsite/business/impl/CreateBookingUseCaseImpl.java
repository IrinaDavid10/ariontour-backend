package com.ariontour.ariontourwebsite.business.impl;

import com.ariontour.ariontourwebsite.business.CreateBookingUseCase;
import com.ariontour.ariontourwebsite.business.exception.InvalidLocationException;
import com.ariontour.ariontourwebsite.business.exception.UsernameNotFoundException;
import com.ariontour.ariontourwebsite.domain.*;
import com.ariontour.ariontourwebsite.persistance.BookingRepository;
import com.ariontour.ariontourwebsite.persistance.CustomerRepository;
import com.ariontour.ariontourwebsite.persistance.UserRepository;
import com.ariontour.ariontourwebsite.persistance.entity.BookingEntity;
import com.ariontour.ariontourwebsite.persistance.entity.CustomerEntity;
import com.ariontour.ariontourwebsite.persistance.entity.EventEntity;
import com.ariontour.ariontourwebsite.persistance.entity.LocationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateBookingUseCaseImpl implements CreateBookingUseCase {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    @Override
    @Transactional
    public CreateBookingResponse createBooking(CreateBookingRequest request){
        if(customerRepository.existsById(request.getCustomerId())){
            BookingEntity savedBooking = saveNewBooking(request);
            return CreateBookingResponse.builder()
                    .bookingId(savedBooking.getId())
                    .build();
        }else

            throw new UsernameNotFoundException();

    }

    private BookingEntity saveNewBooking(CreateBookingRequest request) {

        CustomerEntity customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException(request.getCustomerId().toString()));

        BookingEntity newBooking = BookingEntity.builder()
                .customer(customer)
                .dateTime(LocalDateTime.now())
                .build();

        return bookingRepository.save(newBooking);

    }
}
