package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.dao.SuperDAO;

public interface QueryDAO extends SuperDAO {
    void customerOrderDetail();
}
