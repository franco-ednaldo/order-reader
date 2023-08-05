package com.example.orderreader.mapper;

import com.example.orderreader.Http.res.CustomerResponse;
import com.example.orderreader.model.CustomerOrder;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponse customerToResponse(CustomerOrder customerOrder);

    List<CustomerResponse> customerToResponse(List<CustomerOrder> customerOrder);


}
