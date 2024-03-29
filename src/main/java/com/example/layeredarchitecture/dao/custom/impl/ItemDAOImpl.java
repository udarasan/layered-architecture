package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.entity.Item;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public  ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");
        ArrayList<Item> getAllItem=new ArrayList<>();
        while (rst.next()) {
            Item entity=new Item(rst.getString(1),rst.getString(2),rst.getBigDecimal(3),rst.getInt(4));
           getAllItem.add(entity);
        }
        return getAllItem;
    }
    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",
                entity.getCode(),entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand());

    }
    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("DELETE FROM Item WHERE code=?", code);
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",
                entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getCode());
    }
    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT code FROM Item WHERE code=?", code);
        return rst.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?", code);
        rst.next();
        return new Item(code + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
    }


}
