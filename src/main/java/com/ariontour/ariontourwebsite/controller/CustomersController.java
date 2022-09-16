package com.ariontour.ariontourwebsite.controller;


import com.ariontour.ariontourwebsite.business.CreateCustomerUseCase;
import com.ariontour.ariontourwebsite.business.GetCustomerUseCase;
import com.ariontour.ariontourwebsite.business.GetCustomersUseCase;
import com.ariontour.ariontourwebsite.domain.CreateCustomerRequest;
import com.ariontour.ariontourwebsite.domain.CreateCustomerResponse;
import com.ariontour.ariontourwebsite.domain.Customer;
import com.ariontour.ariontourwebsite.domain.GetCustomersResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;


@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomersController {
    private final GetCustomersUseCase getCustomersUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final CreateCustomerUseCase createCustomerUseCase;

    @GetMapping
    public ResponseEntity<GetCustomersResponse> getCustomers(){
        return ResponseEntity.ok(getCustomersUseCase.getCustomers());
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") final long id){
        final Optional<Customer> customerOptional = getCustomerUseCase.getCustomer(id);
        if(customerOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(customerOptional.get());
    }

    @PostMapping
    public ResponseEntity<CreateCustomerResponse>createCustomer(@RequestBody CreateCustomerRequest request){
        CreateCustomerResponse response = createCustomerUseCase.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
