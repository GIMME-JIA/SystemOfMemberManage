package com.dao;

import com.beans.entity.MemberDO;
import com.beans.req.MemberRequest;
import com.beans.res.TableResult;
import com.beans.vo.MemberVO;

import javax.rmi.CORBA.Stub;


public interface MemberDao {
    int addMember(MemberDO memberDO);

    /**
     * 分页查询成员
     *
     * @param memberRequest
     * @return
     */
    TableResult<MemberVO> queryMemberByPage(MemberRequest memberRequest);

    MemberVO getMemberById(long memberId);

    /**
     * 返回影响的条数
     *
     * @param memberDO
     * @return
     */
    int updateMember(MemberDO memberDO);

    int deleteMemberById(long memberId);
}
