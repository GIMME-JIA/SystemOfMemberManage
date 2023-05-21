package com.servlet;

import com.beans.entity.AdminDO;
import com.service.AdminService;
import com.service.impl.AdminServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "");
        String type = request.getParameter("type");
        // request获取的type值是说明用户点击了登录
        // 此时判断登录的成功与否
        if ("trueLogin".equals(type)) {
            // 获取域对象的账号密码信息
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");

            // 校验账号密码是否为空
            if (userName == null || "".equals(userName.trim()) ||
                    password == null || "".equals(password.trim())) {
                request.setAttribute("message", "用户名或密码不能为空");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }

            AdminService adminService = new AdminServiceImpl();
            // 创建一个AdminDO对象，用来接受adminService返回的javabean
            AdminDO adminDO = adminService.validateLogin(userName);

            // 校验账号密码是否有误
            // 前提是登录对象不为空
            if (adminDO != null && password.equals(adminDO.getPwd())) {
                // 密码信息脱敏
                adminDO.setPwd(null);
                request.getSession().setAttribute("admin", adminDO);
                /*
                当我们做了提交表单的动作后再通过getRequestDispatcher进行内部转发，
                刷新页面会导致表单的重复提交
                因此可以改用重定向,
                故不可再用/WEB-INF/member/memberManage.jsp该路径，
                因为WEB-INF是禁止外部访问的，而重定向又不是内部的转发操作
                对于外部跳转，必须使用重定向，转发则不可对外跳转。
                所以再建一个memberServlet，
                 */
                System.out.println("跳转到主页面");
//                response.sendRedirect("/WEB-INF/member/memberManage.jsp");
                response.sendRedirect(request.getContextPath() + "/memberServlet?type=toMemberManage");
            } else {
                // 校验的账号密码有误
                request.setAttribute("message", "用户名或密码有误");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }

        } else {
            // 账号密码，登录失败，再跳回登录页面
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
