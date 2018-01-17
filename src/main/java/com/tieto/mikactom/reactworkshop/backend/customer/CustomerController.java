package com.tieto.mikactom.reactworkshop.backend.customer;

import com.tieto.mikactom.reactworkshop.backend.common.response.ImmutableRestResponse;
import com.tieto.mikactom.reactworkshop.backend.common.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "${app.route.path}/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public List<CustomerResponse> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("{customerId}")
    @ResponseBody
    public CustomerResponse getCustomer(
            @PathVariable(value = "customerId") Long customerId
    ) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping()
    public ResponseEntity<RestResponse> createCustomer(
            @Valid @RequestBody final NewCustomerRequest customerRequest
    ) {
        CustomerResponse customer = customerService.createCustomer(customerRequest);
        ImmutableRestResponse response = ImmutableRestResponse.builder()
                .message("Customer created")
                .id(String.valueOf(customer.getId()))
                .build();
        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

}
