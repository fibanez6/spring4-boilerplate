package com.fibanez.spring4basic.web.service;

import com.fibanez.spring4basic.model.Customer;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by fernandoi on 07/12/17.
 */
@Validated
public interface CustomerService {

    List list();

    @NotNull
    Customer get(Long id);

    Customer create(Customer customer);

    Long delete(Long id);

    Customer update(Long id, Customer customer);

}
