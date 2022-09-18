package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class App {

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

//        Seller seller = sellerDao.findById(3);

        Department department = new Department(2, "Electronics");

        List<Seller> sellersByDepartment = sellerDao.findByDepartment(department);
        sellersByDepartment.forEach(System.out::println);
    }

}
