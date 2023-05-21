package com.service.impl;

import com.beans.entity.MemberDO;
import com.beans.req.MemberRequest;
import com.beans.res.TableResult;
import com.beans.vo.MemberVO;
import com.dao.MemberDao;
import com.dao.impl.MemberDaoImpl;
import com.service.MemberService;

public class MemberServiceImpl implements MemberService {
    private MemberDao memberDao = new MemberDaoImpl();

    @Override
    public boolean addMember(MemberDO memberDO) {
        return memberDao.addMember(memberDO) == 1;
    }

    @Override
    public TableResult<MemberVO> queryMemberByPage(MemberRequest memberRequest) {
        return memberDao.queryMemberByPage(memberRequest);
    }

    @Override
    public MemberVO getMemberById(long memberId) {
        return memberDao.getMemberById(memberId);
    }

    @Override
    public boolean updateMemberById(MemberDO memberDO) {
        return memberDao.updateMember(memberDO) == 1;
    }

    @Override
    public boolean deleteMemberById(long memberId) {
        return memberDao.deleteMemberById(memberId) == 1;
    }
}
