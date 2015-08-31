package com.hand.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hand.dao.ICustomerDao;
import com.hand.entity.Address;
import com.hand.entity.Customer;
import com.hand.interceptor.MyInterceptor;

public class CustomerDaoImpl implements ICustomerDao {

	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		// TODO Auto-generated method stub
		this.factory = factory;
	}

	public Short add(Customer customer) {
		// TODO Auto-generated method stub
		Short id = (Short) getSession().save(customer);
		getSession().close();
		return id;
	}

	public Address checkAddressId(int addressId) {
		// TODO Auto-generated method stub
		Session session = factory.withOptions().interceptor(new MyInterceptor()).openSession();
		Address address = (Address) session.get(Address.class, Short.parseShort(addressId + ""));
		// System.out.println(address.toString());
		session.close();
		return address;
	}

	public List<Customer> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Session getSession() {
		// TODO Auto-generated method stub
		return factory.openSession();
	}

	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		Customer customer = (Customer) getSession().get(Customer.class, Short.parseShort(id + ""));
		// System.out.println(customer.toString());
		getSession().close();
		return customer;
	}

	public void delete(Short customerId) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Customer customer = (Customer) session.get(Customer.class, Short.parseShort(customerId + ""));
		session.delete(customer);
		session.close();
	}

}
