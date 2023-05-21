package com.dao;

import com.beans.entity.AdminDO;

@FunctionalInterface
public interface AdminDao {
    AdminDO validateLogin(String userName);
}
