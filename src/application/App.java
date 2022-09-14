package application;

import db.DB;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.time.LocalDate;

public class App {

    public static void main(String[] args) {
        Department obj = new Department(1, "Livros");

        Seller seller = new Seller(21, "Bob", "bob@gmail.com", LocalDate.now(), 3000.0, obj);

        System.out.println(obj);
        System.out.println(seller);
    }

}
