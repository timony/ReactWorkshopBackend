package com.tieto.mikactom.reactworkshop.backend.bootstrap;

import com.tieto.mikactom.reactworkshop.backend.customer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"local", "test"})
public class LocalDataBootstrap implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(LocalDataBootstrap.class);

    @Autowired
    CustomerService customerService;

    @Override
    public void afterPropertiesSet() {
        LOG.info("Bootstraping data.........");
        setupCustomers();
        LOG.info("..........Bootstraping complete");
    }

    private void setupCustomers() {
        NewCustomerRequest cust1 = ImmutableNewCustomerRequest.builder()
                .firstname("George")
                .lastname("Washington")
                .email("george.washington@some.com")
                .build();
        CustomerResponse customer1 = customerService.createCustomer(cust1);
        customerService.updateCustomerEnabled(customer1.getId(), true);

        NewCustomerRequest cust2 = ImmutableNewCustomerRequest.builder()
                .firstname("John")
                .lastname("Adams")
                .email("john.adams@some.com")
                .build();
        CustomerResponse customer2 = customerService.createCustomer(cust2);
        customerService.updateCustomerEnabled(customer2.getId(), true);

        NewCustomerRequest cust3 = ImmutableNewCustomerRequest.builder()
                .firstname("Thomas")
                .lastname("Jefferson")
                .email("thomas.jefferson@some.com")
                .build();
        customerService.createCustomer(cust3);

        NewCustomerRequest cust4 = ImmutableNewCustomerRequest.builder()
                .firstname("James")
                .lastname("Madison")
                .email("james.madison@some.com")
                .build();
        customerService.createCustomer(cust4);

        NewCustomerRequest cust5 = ImmutableNewCustomerRequest.builder()
                .firstname("James")
                .lastname("Monroe")
                .email("james.monroe@some.com")
                .build();
        customerService.createCustomer(cust5);
    }
}
