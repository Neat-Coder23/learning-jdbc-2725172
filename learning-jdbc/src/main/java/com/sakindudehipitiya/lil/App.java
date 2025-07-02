package com.sakindudehipitiya.lil;

import java.util.List;

import com.sakindudehipitiya.lil.data.dao.ServiceDao;
import com.sakindudehipitiya.lil.data.entity.Service;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ServiceDao service = new ServiceDao();
        List<Service> services = service.getAll();
        System.out.println("**** Services ****");
        System.out.println(" ***  GET_ALL ***");
        services.forEach(System.out::println);
    }
}
