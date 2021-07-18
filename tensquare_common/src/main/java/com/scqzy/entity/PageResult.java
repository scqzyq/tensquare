package com.scqzy.entity;

import lombok.*;

import java.util.List;

/**
 * @Description: 分页对象
 * @Author 盛春强
 * @Date 2021/7/13 11:58
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageResult <T>{
    private long total;
    private List<T> rows;
    private long totalPages;

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
