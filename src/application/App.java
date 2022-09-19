package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class App {

    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDate = LocalDate.parse("11/05/1997", formatter);

        SellerDao sellerDao = DaoFactory.createSellerDao();

//        Seller seller = sellerDao.findById(3);

        Department department = new Department(2, "Electronics");

//        List<Seller> sellersByDepartment = sellerDao.findByDepartment(department);
//        sellersByDepartment.forEach(System.out::println);

//        List<Seller> allSellers = sellerDao.findAll();
//        allSellers.forEach(System.out::println);

//        Seller seller = new Seller("Greg", "greg@gmail.com", birthDate, 4000.0, department);
//        sellerDao.insert(seller);
//        System.out.println(seller);


        Seller seller = sellerDao.findById(11);

        seller.setName("Orejuela");
        seller.setEmail("carlpp1@gmail.com");
        sellerDao.update(seller);
        seller = sellerDao.findById(11);
        System.out.println(seller);
    }

}
