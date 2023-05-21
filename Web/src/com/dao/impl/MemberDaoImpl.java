package com.dao.impl;

import com.beans.entity.AdminDO;
import com.beans.entity.MemberDO;
import com.beans.req.MemberRequest;
import com.beans.res.TableResult;
import com.beans.vo.MemberVO;
import com.dao.MemberDao;
import com.util.DBUtil;
import com.util.DateUtil;
import com.util.StingUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {

    /**
     * 在数据库表中添加成员
     *
     * @param memberDO
     * @return
     */
    @Override
    public int addMember(MemberDO memberDO) {
        Connection connection = DBUtil.getConnection();
        if (connection == null) {
            return 0;
        }
        PreparedStatement ps = null;

        String sql = "insert into member(name,no,birthday)values (?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setObject(1, memberDO.getName());
            ps.setObject(2, memberDO.getNo());
            ps.setObject(3, memberDO.getBirthDay());
            // 打印一下最终执行的sql语句
            System.out.println("addMember执行的sql：" + ps.toString());
            // 返回结果处理行数
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, ps, null);
        }
        return 0;
    }


    @Override
    public TableResult<MemberVO> queryMemberByPage(MemberRequest memberRequest) {
        TableResult<MemberVO> tableResult = new TableResult<>();
        Connection connection = DBUtil.getConnection();
        if (connection == null) {
            return null;
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        // 查询学生列表
        StringBuilder sb = new StringBuilder();
        sb.append("select * from member ");
        appendWhere(memberRequest, sb);
        sb.append("order by id asc limit ").append(memberRequest.getStart()).append(",").append(memberRequest.getPageSize());

        try {
            ps = connection.prepareStatement(sb.toString());
            if (StingUtil.isNotBlank(memberRequest.getMemberName())) {
                ps.setObject(1, memberRequest.getMemberName());
            }
            // 打印一下最终执行的sql语句
            System.out.println("queryMemberByPage执行的sql：" + ps.toString());
            rs = ps.executeQuery();
            List<MemberVO> list = new ArrayList<>();
            // 处理结果集
            while (rs.next()) {
                MemberVO memberVO = buildMember(rs);
                list.add(memberVO);
            }
            tableResult.setData(list); // 设置数据
            /*
            查询总条数
             */
            sb.setLength(0);
            sb.append("select count(*) from member ");
            appendWhere(memberRequest, sb);
            ps = connection.prepareStatement(sb.toString());
            if (StingUtil.isNotBlank(memberRequest.getMemberName())) {
                ps.setObject(1, memberRequest.getMemberName());
            }
            // 打印一下最终执行的sql语句
            System.out.println("queryMemberByPage查询成员总条数的sql：" + ps.toString());
            if (rs.next()) {
                int totalCount = rs.getInt(1);
                tableResult.setTotalCount(totalCount); // 设置总条数

            }
            return tableResult;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MemberVO getMemberById(long memberId) {
        Connection connection = DBUtil.getConnection();
        if (connection == null) {
            return null;
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,name,no,birthday from member where id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setObject(1, memberId);
            // 打印一下最终执行的sql语句
            System.out.println("getMemberById执行的sql：" + ps.toString());
            rs = ps.executeQuery();
            // 处理结果集
            if (rs.next()) {
                return buildMember(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateMember(MemberDO memberDO) {
        Connection connection = DBUtil.getConnection();
        if (connection == null) {
            return 0;
        }
        PreparedStatement ps = null;

        String sql = "update member set name = ?,no = ?,birthday = ? where id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setObject(1, memberDO.getName());
            ps.setObject(2, memberDO.getNo());
            ps.setObject(3, memberDO.getBirthDay());
            ps.setObject(4, memberDO.getId());
            // 打印一下最终执行的sql语句
            System.out.println("updateMember执行的sql：" + ps.toString());
            // 返回结果处理行数
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, ps, null);
        }
        return 0;
    }

    @Override
    public int deleteMemberById(long memberId) {
        Connection connection = DBUtil.getConnection();
        if (connection == null) {
            return 0;
        }
        PreparedStatement ps = null;

        String sql = "delete from member where id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setObject(1, memberId);
            // 打印一下最终执行的sql语句
            System.out.println("deleteMemberById执行的sql：" + ps.toString());
            // 返回结果处理行数
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection, ps, null);
        }
        return 0;
    }

    private MemberVO buildMember(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        String no = rs.getString("no");
        Timestamp birthday = rs.getTimestamp("birthday");
        MemberVO memberVO = new MemberVO();
        memberVO.setId(id);
        memberVO.setName(name);
        memberVO.setNo(no);
        memberVO.setBirthDay(DateUtil.convertTimestamp2Str(birthday));
        return memberVO;
    }

    private void appendWhere(MemberRequest memberRequest, StringBuilder sb) {
        String memberName = memberRequest.getMemberName();
        if (StingUtil.isNotBlank(memberName)) {
            // todo 这里的问号为何可以不用加:要加
            sb.append("where name = ? ");
        }
    }
}
