package com.filter;

import com.beans.entity.AdminDO;
import com.util.Constants;
import com.util.ExcludeResouceUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "authorizationFilter",
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = Constants.ENCODING, value = "UTF-8")
        }
)
public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 过滤器排除静态资源
        String requestURI = httpServletRequest.getRequestURI();
        if (ExcludeResouceUtil.shouldExclude(requestURI)) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = httpServletRequest.getSession();
            AdminDO admin = (AdminDO) session.getAttribute("admin");
            String contextPath = httpServletRequest.getContextPath();
            String path = requestURI.substring(contextPath.length());

            if (admin == null) {
                if (path.equals("/loginServlet")) {
                    chain.doFilter(request, response);
                } else {
                    // 未登录且访问的不是公共路径
                    httpServletRequest.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }
            } else {
                chain.doFilter(request, response);
            }
        }


    }

    @Override
    public void destroy() {
    }
}
