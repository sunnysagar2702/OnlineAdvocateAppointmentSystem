package com.advocate.service;
import com.advocate.dao.CustomerDAO;
import com.advocate.model.Customer;
import java.util.List;
public class CustomerService {
    private CustomerDAO customerDAO;
    public CustomerService() {
        customerDAO = new CustomerDAO();
    }
    public boolean addCustomer(Customer customer) {
        try {
            customerDAO.addCustomer(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Customer getCustomerById(int customerId) {
        return customerDAO.getCustomerById(customerId);
    }
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
    public boolean updateCustomer(Customer customer) {
        try {
            customerDAO.updateCustomer(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteCustomer(int customerId) {
        try {
            customerDAO.deleteCustomer(customerId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
