package com.beans.entity;

import java.util.Objects;

/**
 * 获取数据库admin表信息，封装成javabean对象
 */
public class AdminDO {
    private Long id;
    private String userName;
    private String pwd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminDO adminDO = (AdminDO) o;
        return Objects.equals(id, adminDO.id) && Objects.equals(userName, adminDO.userName) && Objects.equals(pwd, adminDO.pwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, pwd);
    }

    @Override
    public String toString() {
        return "AdminDO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }*/
}
