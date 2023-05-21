package com.service;

import com.beans.entity.AdminDO;

@FunctionalInterface
public interface AdminService {
    AdminDO validateLogin(String userName);
}
