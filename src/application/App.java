package application;

import db.DB;
import model.entities.Department;

import java.sql.Connection;

public class App {

    public static void main(String[] args) {
        Department obj = new Department(1, "Livros");
        System.out.println(obj);
    }

}
