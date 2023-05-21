package com.service.impl;

import com.beans.entity.AdminDO;
import com.dao.AdminDao;
import com.dao.impl.AdminDaoImpl;
import com.service.AdminService;

/**
 *
 */
public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public AdminDO validateLogin(String userName) {
        return adminDao.validateLogin(userName);

    }
}
