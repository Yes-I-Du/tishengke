package com.tishengke.tishengkebackend.infrastructure.common;

import lombok.Data;

/**
 * 分页请求通用类
 *
 * @author dmz xxx@163.com
 * @version 2025/6/22 18:55
 * @since JDK17
 */
@Data
public class PageRequest {
    /* 当前页号 */
    private int current = 1;

    /* 页面大小 */
    private int pageSize = 10;

    /* 排序字段 */
    private String sortField;

    /* 排序顺序（默认降序） */
    private String sortOrder = "descend";
}