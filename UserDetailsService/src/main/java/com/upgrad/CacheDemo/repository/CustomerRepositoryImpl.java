package com.upgrad.CacheDemo.repository;

import com.upgrad.CacheDemo.entity.Address;
import com.upgrad.CacheDemo.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private static HashMap<Integer,Customer> customerData = new HashMap<>();

    @PostConstruct
    private void fillUsers() {
        customerData.put(1, new Customer(1,"Ram",25,"Street A", "Delhi", 100001));
        customerData.put(2, new Customer(2,"Mohan",35,"Street B", "Delhi", 200001));
        customerData.put(3, new Customer(3,"Shyam",45,"Street Ali Darbar", "Delhi", 123001));
        customerData.put(4, new Customer(4,"James",12,"Street Main", "Mumbai", 810210));
        customerData.put(5, new Customer(5,"Ali",32,"Street Gandhi Path", "Delhi", 812011));
        customerData.put(6, new Customer(6,"John",49,"Street BoringRoad", "Patna", 231144));
        customerData.put(7, new Customer(7,"Parvinder",30,"Street Manyawar Chowk", "Pune", 121221));
    }

    @Override
    public Customer getCustomerDetails(int customerId) {
       return customerData.get(customerId);
    }

    @Override
    public List<Customer> getAllCustomer() {
        Collection<Customer> customerCollection = customerData.values();
        Iterator<Customer> customerIterator = customerCollection.iterator();
        List<Customer> customers = new ArrayList<>();
        while(customerIterator.hasNext()){
            customers.add(customerIterator.next());
        }
        return customers;
    }

    @Override
    public Customer saveCustomerDetails(Customer customer) {
        customerData.put(customer.getCustomerId(), customer);
        return customer;
    }

    @Override
    public List<Address> getAllCustomerAddress() {
        Collection<Customer> customerCollection = customerData.values();
        Iterator<Customer> customerIterator = customerCollection.iterator();
        List<Address> addresses = new ArrayList<>();
        while(customerIterator.hasNext()){
            addresses.add(customerIterator.next().getCustomerAddress());
        }
        return addresses;
    }

    @Override
    public Address getCustomerAddress(int customerId) {
        Customer savedCustomer = customerData.get(customerId);
        if(savedCustomer != null){
            return savedCustomer.getCustomerAddress();
        }else{
            return null;
        }
    }
}
