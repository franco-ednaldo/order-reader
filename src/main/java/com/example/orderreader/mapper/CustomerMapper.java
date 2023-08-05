package com.example.orderreader.mapper;

import com.example.orderreader.Http.res.CustomerResponse;
import com.example.orderreader.model.Customer;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponse customerToResponse(Customer customer);

    List<CustomerResponse> customerToResponse(List<Customer> customer);


}
