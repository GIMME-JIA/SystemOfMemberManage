package com.service;

import com.beans.entity.MemberDO;
import com.beans.req.MemberRequest;
import com.beans.res.TableResult;
import com.beans.vo.MemberVO;
import com.dao.MemberDao;


public interface MemberService {
    boolean addMember(MemberDO memberDO);

    TableResult<MemberVO> queryMemberByPage(MemberRequest memberRequest);

    MemberVO getMemberById(long memberId);

    boolean updateMemberById(MemberDO memberDO);

    boolean deleteMemberById(long memberId);
}
