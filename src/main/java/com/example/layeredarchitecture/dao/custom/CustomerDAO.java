package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.*;

public interface CustomerDAO extends CrudDAO<Customer> {
    void customerNameAddress() throws SQLException, ClassNotFoundException;
}
