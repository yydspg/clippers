package com.cp.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    //data list
    private List<T> items;
    //total records
    private long counts;
    //current page
    private long page;
    //records per page
    private long pageSize;

}
