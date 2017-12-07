package com.fibanez.spring4basic.dao;

import java.util.ArrayList;
import java.util.List;

import com.fibanez.spring4basic.model.Address;
import com.fibanez.spring4basic.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDao {

    // Dummy database. Initialize with some dummy values.
    private static List<Customer> customers;
    {
        Address address = new Address("Address", "Postcode");

        customers = new ArrayList();
        customers.add(new Customer(101l, "John", "Doe", "djohn@gmail.com", "121-232-3435", address));
        customers.add(new Customer(201l, "Russ", "Smith", "sruss@gmail.com", "343-545-2345", address));
        customers.add(new Customer(301l, "Kate", "Williams", "kwilliams@gmail.com", "876-237-2987", address));
    }

    /**
     * Returns list of customers from dummy database.
     *
     * @return list of customers
     */
    public List list() {
        return customers;
    }

    /**
     * Return customer object for given id from dummy database. If customer is
     * not found for id, returns null.
     *
     * @param id
     *            customer id
     * @return customer object for given id
     */
    public Customer get(Long id) {

        for (Customer c : customers) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Create new customer in dummy database. Updates the id and insert new
     * customer in list.
     *
     * @param customer
     *            Customer object
     * @return customer object with updated id
     */
    public Customer create(Customer customer) {
        customer.setId(System.currentTimeMillis());
        customers.add(customer);
        return customer;
    }

    /**
     * Delete the customer object from dummy database. If customer not found for
     * given id, returns null.
     *
     * @param id
     *            the customer id
     * @return id of deleted customer object
     */
    public Long delete(Long id) {

        for (Customer c : customers) {
            if (c.getId().equals(id)) {
                customers.remove(c);
                return id;
            }
        }

        return null;
    }

    /**
     * Update the customer object for given id in dummy database. If customer
     * not exists, returns null
     *
     * @param id
     * @param customer
     * @return customer object with id
     */
    public Customer update(Long id, Customer customer) {

        for (Customer c : customers) {
            if (c.getId().equals(id)) {
                customer.setId(c.getId());
                customers.remove(c);
                customers.add(customer);
                return customer;
            }
        }

        return null;
    }

}