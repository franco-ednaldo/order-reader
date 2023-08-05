package com.example.order.reader.mapper;

import com.example.order.reader.http.res.CustomerResponse;
import com.example.order.reader.model.CustomerOrder;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponse customerToResponse(CustomerOrder customerOrder);

    List<CustomerResponse> customerToResponse(List<CustomerOrder> customerOrder);


}
