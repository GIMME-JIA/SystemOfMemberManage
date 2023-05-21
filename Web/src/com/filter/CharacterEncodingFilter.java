package com.filter;

import com.util.Constants;
import com.util.ExcludeResouceUtil;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "characterEncodingFilter",
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = Constants.ENCODING, value = "UTF-8")
        }
)
public class CharacterEncodingFilter implements Filter {
    private String ENCODING = null;

    public void init(FilterConfig config) throws ServletException {
        this.ENCODING = config.getInitParameter(Constants.ENCODING);
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        System.out.println("当前request编码：" + httpServletRequest.getCharacterEncoding());
        System.out.println("当前response编码：" + httpServletResponse.getCharacterEncoding());
        // 过滤器排除静态资源
        String requestURI = httpServletRequest.getRequestURI();
        System.out.println(requestURI);
        if (ExcludeResouceUtil.shouldExclude(requestURI)) {
            // 设置字符编码
            httpServletRequest.setCharacterEncoding(ENCODING);
            httpServletResponse.setCharacterEncoding(ENCODING);
        }
        
        chain.doFilter(request, response);

    }
}























