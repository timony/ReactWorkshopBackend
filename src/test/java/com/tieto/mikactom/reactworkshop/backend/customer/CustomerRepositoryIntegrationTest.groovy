package com.tieto.mikactom.reactworkshop.backend.customer

import com.tieto.mikactom.reactworkshop.backend.RepositoryTest
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Unroll

public class CustomerRepositoryIntegrationTest extends RepositoryTest {

    @Autowired
    CustomerRepository underTest

    @Unroll
    def 'should correctly return customer = #result'() {
        when:
        def fromDb = underTest.findById(customerId)

        then:
        fromDb.isPresent() == result

        where:
        customerId | result
        1L         | true
        1111111L   | false
    }

    def 'should find all customer'() {
        when:
        def customers = underTest.findAllByOrderByLastname()
        def customersById = underTest.findAll()

        then:
        customers.size() == customersById.size()
    }

}
