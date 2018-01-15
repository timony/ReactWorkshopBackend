package com.tieto.mikactom.reactworkshop.backend.customer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findAllByOrderByLastname();

    Optional<Customer> findById(Long id);

}
