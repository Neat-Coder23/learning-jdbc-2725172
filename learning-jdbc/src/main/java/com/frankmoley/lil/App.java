package com.frankmoley.lil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.frankmoley.lil.data.dao.CustomerDao;
import com.frankmoley.lil.data.dao.ServiceDao;
import com.frankmoley.lil.data.dao.SimpleProductDao;
import com.frankmoley.lil.data.entity.Customer;
import com.frankmoley.lil.data.entity.Service;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ServiceDao dao = new ServiceDao();
        List<Service> services = dao.getAll();
        System.out.println("*** GET ALL ***");
        services.forEach(System.out::println);
        Optional<Service> service = dao.getOne(services.get(0).getServiceId());
        System.out.println("\n*** GET ONE ***\n" + service.get());
        Service newService = new Service();
        newService.setName("FooBarBaz" + System.currentTimeMillis());
        newService.setPrice(new BigDecimal(4.35));
        newService = dao.create(newService);
        System.out.println("\n*** CREATE ***\n" + newService);
        newService.setPrice(new BigDecimal(13.45));
        newService = dao.update(newService);
        System.out.println("\n*** UPDATE ***\n" + newService);
        dao.delete(newService.getServiceId());
        System.out.println("\n*** DELETE ***");

        System.out.println("\n\n******* CUSTOMERS *******");
        CustomerDao customerDao = new CustomerDao();
        List<Customer> customers = customerDao.getAll();        
        System.out.println("*** GET ALL ***");
        customers.forEach(System.out::println);
        Optional<Customer> customer = customerDao.getOne(customers.get(0).getCustomerId());
        System.out.println("\n*** GET ONE ***\n" + customer.get());
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("Ron");
        newCustomer.setLastName("Swanson");
        newCustomer.setEmail("ronswanson@example.com");
        newCustomer.setPhone("515.555.1235");
        newCustomer.setAddress("1234 Main St Anytown, KS 66400");
        newCustomer = customerDao.create(newCustomer);
        System.out.println("\n*** CREATE ***\n" + newCustomer);
        newCustomer.setEmail("rswanson@freedom.com");
        newCustomer = customerDao.update(newCustomer);
        System.out.println("\n*** UPDATE ***\n" + newCustomer);
        customerDao.delete(newCustomer.getCustomerId());
        System.out.println("\n*** DELETE ***\n");

        System.out.println("\n\n******* SIMPLE PRODUCT *******");
        SimpleProductDao spdao = new SimpleProductDao();
        UUID productId = spdao.createProduct("foobarbaz"+System.currentTimeMillis(), new BigDecimal(45.67), "Jaloo");
        System.out.println(productId);

        System.out.println("\n\n*** LIMIT ***");
        dao.getAllLimit(2).forEach(System.out::println);;

        System.out.println("\n\n*** PAGED ***");
        for(int i=1; i<11;i++){
            System.out.println("Page number: " + i);
            customerDao.getAllPaged(i, 10).forEach(System.out::println);
        }
    }
}
