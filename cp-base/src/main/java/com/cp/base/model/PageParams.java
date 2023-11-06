package com.cp.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParams {
    //当前页码
    private Long pageNo = 1L;
    //每页记录数(default)
    private Long pageSize = 10L;
}
