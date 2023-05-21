package com.dao.impl;

import com.beans.entity.AdminDO;
import com.dao.AdminDao;
import com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {
    @Override
    public AdminDO validateLogin(String userName) {
        Connection connection = DBUtil.getConnection();
        if (connection == null) {
            return null;
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
//        StringBuilder sb = new StringBuilder();
//        sb.append("select id,user_name,pwd from admin where user_name = ?");
        String sql = "select id,user_name,pwd from admin where user_name = ?";
        try {
//            ps = connection.prepareStatement(sb.toString());
            ps = connection.prepareStatement(sql);
            ps.setObject(1, userName);
            // 打印一下最终执行的sql语句
            System.out.println("validateLogin执行的sql：" + ps.toString());
            rs = ps.executeQuery();
            // 处理结果集
            if (rs.next()) {
                long id = rs.getLong("id");
                String pwd = rs.getString("pwd");
                System.out.println(id + userName + pwd);
                AdminDO adminDO = new AdminDO();
                adminDO.setId(id);
                adminDO.setUserName(userName);
                adminDO.setPwd(pwd);
                return adminDO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
