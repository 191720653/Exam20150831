package com.hand.Exam20150831;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hand.dao.ICustomerDao;
import com.hand.entity.Address;
import com.hand.entity.Customer;
import com.hand.entity.Store;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		System.out.println("Hello World!");

		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ICustomerDao iCustomerDao = (ICustomerDao) context.getBean("CustomerDao");

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Customer customer = new Customer();
		Store store = new Store();
		store.setStoreId(Short.parseShort("1"));
		customer.setStore(store);
		System.out.println("请输入first_name:");
		customer.setFirstName(scanner.nextLine());
		System.out.println("请输入last_name:");
		customer.setLastName(scanner.nextLine());
		System.out.println("请输入email:");
		customer.setEmail(scanner.nextLine());
		Address address = null;
		boolean bool = true;
		customer.setAddress(null);
		while (bool) {
			System.out.println("请输入addressId:");
			int addressId = scanner.nextInt();
			address = iCustomerDao.checkAddressId(addressId);
			bool = address == null ? true : false;
			if (bool) {
				System.out.println("addressId无效！请重新输入！");
			}
		}
		customer.setActive(true);
		customer.setAddress(address);
		customer.setCreateDate(new Timestamp(new Date().getTime()));
		int id = iCustomerDao.add(customer);

		Customer customer2 = iCustomerDao.getCustomer(id);
		System.out.println("已保存的数据如下：");
		System.out.println("ID: " + customer2.getCustomerId());
		System.out.println("FirstName: " + customer2.getFirstName());
		System.out.println("LastName" + customer2.getLastName());
		System.out.println("Email: " + customer2.getEmail());
		System.out.println("Adderss" + customer2.getAddress().getAddress());
		System.out.println("CreateDate: " + customer2.getCreateDate());

		System.out.println();
		bool = true;
		while (bool) {
			System.out.println("请输入要删除的Customer的Id:");
			int customerId = scanner.nextInt();
			Customer customer3 = iCustomerDao.getCustomer(customerId);
			bool = customer3 == null;
			if (bool) {
				System.out.println("您输入的customerId不存在！请重新输入！");
			} else {
				Short temp = customer3.getCustomerId();
				iCustomerDao.delete(temp);
				System.out.println("您输入的Id为" + customerId + "的Customer已经删除！");
				System.out.println("The End!");
				bool = false;
			}
		}

	}
}
