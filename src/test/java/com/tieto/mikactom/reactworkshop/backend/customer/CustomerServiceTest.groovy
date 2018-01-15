package com.tieto.mikactom.reactworkshop.backend.customer

import com.tieto.mikactom.reactworkshop.backend.common.exception.NotFoundException
import spock.lang.Specification
import spock.lang.Unroll

import javax.swing.KeyStroke

class CustomerServiceTest extends Specification {

    def 'should return all customers'() {
        given:
        def repo = Mock(CustomerRepository)
        def customer1 = new Customer(
                id: 1L,
                firstname: 'User1',
                lastname: 'UserLast1',
                email: 'email1@some.com',
                enabled: true,
                registrationDate: new Date() - 2
        )
        def underTest = new CustomerService(
                customerRepository: repo
        )

        when:
        def customers = underTest.getAllCustomers()

        then:
        1 * repo.findAllByOrderByLastname() >> [customer1]
        customers.size() == 1
        customers[0].firstname == customer1.firstname
        customers[0].email == customer1.email
    }

    def 'should return customer'() {
        given:
        def repo = Mock(CustomerRepository)
        def customer1 = new Customer(
                id: 1L,
                firstname: 'User1',
                lastname: 'UserLast1',
                email: 'email1@some.com',
                enabled: true,
                registrationDate: new Date() - 2
        )
        def underTest = new CustomerService(
                customerRepository: repo
        )

        when:
        def fromRepo = underTest.getCustomer(1L)

        then:
        1 * repo.findById(1L) >> Optional.of(customer1)
        noExceptionThrown()
        fromRepo.firstname == customer1.firstname
        fromRepo.lastname == customer1.lastname
        fromRepo.email == customer1.email
        fromRepo.enabled == customer1.enabled
        fromRepo.registrationDate == customer1.registrationDate
    }

    def 'should throw exception when customer does not exist'() {
        given:
        def repo = Mock(CustomerRepository)
        def underTest = new CustomerService(
                customerRepository: repo
        )

        when:
        underTest.getCustomer(1L)

        then:
        1 * repo.findById(1L) >> Optional.empty()
        thrown(NotFoundException)
    }

    def 'should crate new customer'() {
        given:
        def repo = Mock(CustomerRepository)
        def newCustomerRequest = Mock(NewCustomerRequest)
        def underTest = new CustomerService(
                customerRepository: repo
        )
        def saved = new Customer(
                id: 1L,
                firstname: 'User1',
                lastname: 'UserLast1',
                email: 'email1@some.com',
                enabled: true,
                registrationDate: new Date() - 2
        )

        when:
        def fromDb = underTest.createCustomer(newCustomerRequest)

        then:
        1 * repo.save(_ as Customer) >> saved
        fromDb.id == saved.id
        fromDb.firstname == saved.firstname
        fromDb.lastname == saved.lastname
        fromDb.email == saved.email
        fromDb.enabled == saved.enabled
        fromDb.registrationDate == saved.registrationDate
    }

    @Unroll
    def 'should update customer enanbled #updateEnabledTo'() {
        given:
        def repo = Mock(CustomerRepository)
        def beforeUpdate = Mock(Customer)
        def underTest = new CustomerService(
                customerRepository: repo
        )
        def saved = new Customer(
                id: 1L,
                firstname: 'User1',
                lastname: 'UserLast1',
                email: 'email1@some.com',
                enabled: updateEnabledTo,
                registrationDate: new Date() - 2
        )

        when:
        def enabled = underTest.updateCustomerEnabled(1L, updateEnabledTo)

        then:
        1 * repo.findById(1L) >> Optional.of(beforeUpdate)
        1 * beforeUpdate.setEnabled(updateEnabledTo)
        1 * repo.save(beforeUpdate) >> saved
        enabled.isEnabled() == updateEnabledTo

        where:
        updateEnabledTo << [true, false]
    }
}
