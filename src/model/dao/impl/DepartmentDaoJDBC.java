package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO department (Name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, obj.getName());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    obj.setId(id);
                }

                DB.closeResultSet(resultSet);
            } else {
                throw  new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE department SET Name = ? WHERE Id = ?"
            );

            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM department WHERE Id = ?"
            );

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }

    }

    @Override
    public Department findById(Integer id) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Department obj = null;

        try {

            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM department WHERE Id = ?"
            );

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                obj = new Department();
                obj.setId(resultSet.getInt("Id"));
                obj.setName(resultSet.getString("Name"));
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }

        return obj;
    }

    @Override
    public List<Department> findAll() {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Department> departmentList = new ArrayList<>();

        try {

            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM department"
            );

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Department obj = new Department();

                obj.setId(resultSet.getInt("Id"));
                obj.setName(resultSet.getString("Name"));

                departmentList.add(obj);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }

        return departmentList;
    }
}
