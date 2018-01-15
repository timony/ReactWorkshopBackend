package com.tieto.mikactom.reactworkshop.backend.customer

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.tieto.mikactom.reactworkshop.backend.ControllerIntegrationTest
import org.springframework.beans.factory.annotation.Autowired

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class CustomerControllerIntegrationTest extends ControllerIntegrationTest {

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    CustomerRepository customerRepository

    def 'should return all customers'() {
        given:
        def inDb = customerRepository.findAllByOrderByLastname()
        when:
        def response = mockMvc.perform(
                get("${getContextPath()}/customer")
        )
                .andExpect(status().isOk())
                .andReturn().response

        then:
        List<CustomerResponse> entity = objectMapper.readValue(
                response.contentAsString,
                objectMapper.getTypeFactory().constructCollectionType(List.class, CustomerResponse.class)
        )
        entity.size() == inDb.size()
    }

    def 'should return customer detail'() {
        given:
        def inDb = customerRepository.findAllByOrderByLastname().stream()
                .findFirst()
                .orElseThrow({return new IllegalStateException("No records in the database")})

        when:
        def response = mockMvc.perform(
                get("${getContextPath()}/customer/${inDb.id}")
        )
                .andExpect(status().isOk())
                .andReturn().response

        def returnedCustomer = objectMapper.readValue(response.contentAsString, CustomerResponse)
        then:
        returnedCustomer.id == inDb.id
    }

    def 'should throw exception when customer of the given id does not exist'() {
        when:
        def response = mockMvc.perform(
                get("${getContextPath()}/customer/-1")
        )
                .andExpect(status().isNotFound())
                .andReturn().response

        then:
        //TODO timony: get rid of it
        1 == 1
    }
}
