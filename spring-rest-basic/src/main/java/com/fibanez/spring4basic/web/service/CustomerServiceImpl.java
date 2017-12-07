package com.fibanez.spring4basic.web.service;

import com.fibanez.spring4basic.model.Customer;
import com.fibanez.spring4basic.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fernandoi on 07/12/17.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDAO;

    public List list() {
        return customerDAO.list();
    }

    public Customer get(Long id) {
        return customerDAO.get(id);
    }

    public Customer create(Customer customer) {
        return customerDAO.create(customer);
    }

    public Long delete(Long id) {
        return customerDAO.delete(id);
    }

    public Customer update(Long id, Customer customer) {
        return customerDAO.update(id, customer);
    }
}
