package com.beans.res;

import com.util.Constants;

import java.util.List;

public class TableResult<T> {
    private List<T> data;
    private int totalCount; // 总共多少条
    private int pageNow; // 透传,当前页数
    private String memberName; // 透传


    /**
     * 总页数
     *
     * @return
     */
    public int getPageCount() {
        int pageCount;
        if (totalCount % Constants.PAGE_SIZE == 0) {
            pageCount = totalCount / Constants.PAGE_SIZE;
        } else {
            pageCount = totalCount / Constants.PAGE_SIZE + 1;
        }
        return pageCount;
    }


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}





























