package com.tieto.mikactom.reactworkshop.backend.customer;

import com.tieto.mikactom.reactworkshop.backend.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.beanutils.BeanUtils.setProperty;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAllByOrderByLastname().stream()
                .map(customer -> toCustomerResponse(customer))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerResponse getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return toCustomerResponse(customer);
    }

    @Transactional
    public CustomerResponse createCustomer(NewCustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setFirstname(customerRequest.getFirstname());
        customer.setLastname(customerRequest.getLastname());
        customer.setEmail(customerRequest.getEmail());
        customer.setEnabled(false);
        customer.setRegistrationDate(LocalDateTime.now());
        Customer persisted = customerRepository.save(customer);

        return toCustomerResponse(persisted);
    }

    @Transactional
    public CustomerResponse updateCustomerPartial(Long customerId, Map<String, Object> updates) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException(customerId));

        updates.forEach((key, value) -> {
            try {
                setProperty(customer, key, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return toCustomerResponse(customerRepository.save(customer));
    }

    @Transactional
    public CustomerResponse updateCustomerEnabled(long userId, Boolean enabled) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("enabled", enabled);
        return updateCustomerPartial(userId, updates);
    }
    private CustomerResponse toCustomerResponse(Customer customer) {
        return ImmutableCustomerResponse.builder()
                .id(customer.getId())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .isEnabled(customer.getEnabled())
                .registrationDate(customer.getRegistrationDate())
                .build();
    }

}
