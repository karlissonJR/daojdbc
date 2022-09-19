package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;

public class App2 {

    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();


//        Department department = new Department("Music");
//        departmentDao.insert(department);
//        System.out.println(department);

//        Department department = new Department(1, "Food");
//        departmentDao.update(department);

//        departmentDao.deleteById(6);

//        Department department = departmentDao.findById(4);
//        System.out.println(department);

        List<Department> departments = departmentDao.findAll();
        departments.forEach(System.out::println);
    }

}
