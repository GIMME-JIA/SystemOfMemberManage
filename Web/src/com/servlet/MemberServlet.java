package com.servlet;

import com.beans.entity.MemberDO;
import com.beans.req.MemberRequest;
import com.beans.res.TableResult;
import com.beans.vo.MemberVO;
import com.dao.MemberDao;
import com.service.MemberService;
import com.service.impl.MemberServiceImpl;
import com.util.DateUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "memberServlet", value = "/memberServlet")
public class MemberServlet extends HttpServlet {
    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type.equals("toMemberManage")) {
            // 查询出来的成员列表放到request中
            String memberName = request.getParameter("memberName");
            String pageNowStr = request.getParameter("pageNow"); // 当前第几页
            MemberRequest memberRequest = new MemberRequest();
            int pageNow = 1; // 默认查第一页
            if (pageNowStr != null && !"".equals(pageNowStr.trim())) {
                pageNow = Integer.parseInt(pageNowStr);
            }
            memberRequest.setPageNow(pageNow);
            memberRequest.setMemberName(memberName);
            // todo 此处点击尾页时显示空指针异常,tableResult为空,
            //  原因：
            TableResult<MemberVO> tableResult = memberService.queryMemberByPage(memberRequest);
            tableResult.setPageNow(pageNow);
            tableResult.setMemberName(memberName == null ? "" : memberName);
            // 放到request请求域中，并在member。jsp中使用
            request.setAttribute("tableResult", tableResult);

            request.getRequestDispatcher("/WEB-INF/member/memberManage.jsp").forward(request, response);
        } else if (type.equals("toAdd")) {
            // 跳转到添加界面，此时没有数据
            request.getRequestDispatcher("/WEB-INF/member/add.jsp").forward(request, response);
        } else if (type.equals("add")) {
            // 执行学生添加，此时已经输入了要添加的数据
            String memberName = request.getParameter("memberName");
            String no = request.getParameter("no");
            String birthDay = request.getParameter("birthDay");
            System.out.println(memberName);
            System.out.println(no);
            System.out.println(birthDay);
            MemberDO memberDO = new MemberDO();
            memberDO.setName(memberName);
            memberDO.setNo(no);
            memberDO.setBirthDay(DateUtil.convertStr2Date(birthDay));
            memberService.addMember(memberDO);
            // todo 以此路径/WEB-INF/member/memberManage.jsp跳转到memberManage.jsp报空指针异常，
            //  原因：因为没有request域里没有设置tableResult对象，目前未知如何解决
            //  试图：创建一个temp.jsp，跳到这个jsp再跳到toMemberManage重新遍历
            //  结果：重定向到toMemberManage直接遍历解决
            response.sendRedirect(request.getContextPath() + "/memberServlet?type=toMemberManage");
            // request.getRequestDispatcher("/WEB-INF/member/temp.jsp").forward(request, response);
        } else if (type.equals("toUpdate")) {
            String memberId = request.getParameter("id");
            String pageNow = request.getParameter("pageNow");
            MemberVO memberVO = memberService.getMemberById(Long.parseLong(memberId));
            request.setAttribute("memberVO", memberVO);
            request.setAttribute("pageNow", Integer.parseInt(pageNow));
            request.getRequestDispatcher("/WEB-INF/member/update.jsp").forward(request, response);
        } else if (type.equals("update")) {
            String memberId = request.getParameter("memberId");
            String memberName = request.getParameter("memberName");
            String no = request.getParameter("no");
            String birthDay = request.getParameter("birthDay");
            String pageNow = request.getParameter("pageNow");
            // 把参数封装为对象
            MemberDO memberDO = new MemberDO();
            memberDO.setId(Long.parseLong(memberId));
            memberDO.setName(memberName);
            memberDO.setNo(no);
            memberDO.setBirthDay(DateUtil.convertStr2Date(birthDay));

            memberService.updateMemberById(memberDO);
            response.sendRedirect(request.getContextPath() + "/memberServlet?type=toMemberManage&pageNow=" + pageNow);
        } else if (type.equals("delete")) {
            String memberId = request.getParameter("id");
            memberService.deleteMemberById(Long.parseLong(memberId));
            response.sendRedirect(request.getContextPath() + "/memberServlet?type=toMemberManage");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
























