package com.beans.req;

import com.util.Constants;

import java.sql.Connection;

public class MemberRequest {
    private int pageNow; // 当前要查询第几页
    private int pageSize = Constants.PAGE_SIZE; // 每页展示多少条
    private String memberName;

    /**
     * 获取limit start，len中的start
     *
     * @return
     */
    public int getStart() {
        return (pageNow - 1) * pageSize;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
