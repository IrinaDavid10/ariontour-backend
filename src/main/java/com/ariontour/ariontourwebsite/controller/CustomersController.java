package com.ariontour.ariontourwebsite.controller;


import com.ariontour.ariontourwebsite.business.CreateCustomerUseCase;
import com.ariontour.ariontourwebsite.business.DeleteCustomerUseCase;
import com.ariontour.ariontourwebsite.business.GetCustomerUseCase;
import com.ariontour.ariontourwebsite.business.GetCustomersUseCase;
import com.ariontour.ariontourwebsite.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;


@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = {"http://localhost:3000"})
@AllArgsConstructor
public class CustomersController {
    private final GetCustomersUseCase getCustomersUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final CreateCustomerUseCase createCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<GetCustomersResponse> getCustomers() {
        return ResponseEntity.ok(getCustomersUseCase.getCustomers());
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") final long id) {
        final Optional<Customer> customerOptional = getCustomerUseCase.getCustomer(id);
        if (customerOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(customerOptional.get());
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_CUSTOMER"})
    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody CreateCustomerRequest request) {
        CreateCustomerResponse response = createCustomerUseCase.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCustomer(@PathVariable(value = "id") Long customerId) {
        DeleteCustomerRequest request = new DeleteCustomerRequest(customerId);
        if (deleteCustomerUseCase.deleteCustomer(request)) {
            return ResponseEntity.ok(customerId);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerId);
    }
}
