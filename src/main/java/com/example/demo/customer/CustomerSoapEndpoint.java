package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.Customer;
import io.spring.guides.gs_producing_web_service.DeleteCustomerRequest;
import io.spring.guides.gs_producing_web_service.DeleteCustomerResponse;
import io.spring.guides.gs_producing_web_service.GetCustomerRequest;
import io.spring.guides.gs_producing_web_service.GetCustomerResponse;
import io.spring.guides.gs_producing_web_service.NewCustomerRequest;
import io.spring.guides.gs_producing_web_service.NewCustomerResponse;

@Endpoint
public class CustomerSoapEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerSoapEndpoint(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        System.out.println("CustomerEndpoint.CustomerEndpoint()");
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCustomerRequest")
    @ResponsePayload
    public GetCustomerResponse getCustomer(@RequestPayload GetCustomerRequest request) {
        System.out.println("CustomerEndpoint.getCustomer()");
        GetCustomerResponse response = new GetCustomerResponse();
        CustomerEntity ce = customerRepository.findById(request.getId());
        Customer customer = new Customer();
        customer.setId(ce.getId());
        customer.setFirstName(ce.getFirstName());
        customer.setLastName(ce.getLastName());
        customer.setEmail(ce.getEmail());
        response.setCustomer(customer);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCustomerRequest")
    @ResponsePayload
    public DeleteCustomerResponse deleteCustomer(@RequestPayload DeleteCustomerRequest request) {
        System.out.println("CustomerEndpoint.getCustomer()");
        DeleteCustomerResponse response = new DeleteCustomerResponse();
        // remove element from repository
        try {
            customerRepository.deleteById(request.getId());
            response.setId(request.getId());
        } catch (Exception e) {
            response.setId(-1L);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "newCustomerRequest")
    @ResponsePayload
    public NewCustomerResponse getCustomer(@RequestPayload NewCustomerRequest request) {
        System.out.println("CustomerEndpoint.getCustomer()");
        CustomerEntity ce = new CustomerEntity(request.getName(), request.getSurname(), request.getMail());
        customerRepository.save(ce);
        Customer customer = new Customer();
        customer.setId(ce.getId());
        customer.setFirstName(ce.getFirstName());
        customer.setLastName(ce.getLastName());
        customer.setEmail(ce.getEmail());
        NewCustomerResponse response = new NewCustomerResponse();
        response.setCustomer(customer);
        return response;
    }
}