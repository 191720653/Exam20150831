package com.hand.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hand.entity.Address;
import com.hand.entity.Customer;

public interface ICustomerDao {

	public void setFactory(SessionFactory factory);
	
	public Short add(Customer customer);
	
	public Customer getCustomer(int id);
	
	public Address checkAddressId(int addressId);
	
	public void delete(Short customer);

	public List<Customer> listAll();
	
	public Session getSession();

}
