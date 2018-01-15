package com.tieto.mikactom.reactworkshop.backend.customer;

import org.springframework.beans.factory.annotation.Autowired;
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
}
