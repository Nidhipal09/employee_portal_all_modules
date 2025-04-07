package com.employeeportal.model.dto;

import com.employeeportal.model.Users;

import java.util.List;

public class SearchResponseDTO {
    private List<Users> list;
    private Integer pageSize;
    private Integer pageNumber;
    private Integer totalPageSize;

    public SearchResponseDTO() {
    }

    public SearchResponseDTO(List<Users> list, Integer pageSize, Integer pageNumber, Integer totalPageSize) {
        this.list = list;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalPageSize = totalPageSize;
    }

    public List<Users> getList() {
        return list;
    }

    public void setList(List<Users> list) {
        this.list = list;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(Integer totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

}
