package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

   @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> getAllCustomer=new ArrayList<>();
        while (rst.next()){
            Customer entity=new Customer(rst.getString("id"),
                    rst.getString("name"), rst.getString("address"));
            getAllCustomer.add(entity);
        }
        return getAllCustomer;
    }
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",
                    entity.getId(), entity.getName(), entity.getAddress());
    }
    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?"
               ,entity.getName(),entity.getAddress(),entity.getId());

   }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Customer WHERE id=?",id);
    }
    @Override
    public  String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT id FROM Customer WHERE id=?",id);
        return resultSet.next();
    }
    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?",id);
        rst.next();
        return new Customer(id + "", rst.getString("name"), rst.getString("address"));
    }


    @Override
    public void customerNameAddress() throws SQLException, ClassNotFoundException {
        //logic
    }
}
