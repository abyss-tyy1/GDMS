package com.GDMS.dto;

import java.io.Serializable;

public class BaseRequestDto implements Serializable {


    private Integer pageSize;

    private Integer currentPage;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "BaseRequestDto{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                '}';
    }
}
