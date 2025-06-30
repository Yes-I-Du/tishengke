package com.tishengke.tishengkebackend.infrastructure.common;

import lombok.Getter;

/**
 * 删除请求通用类
 *
 * @author dmz xxx@163.com
 * @version 2025/6/22 18:58
 * @since JDK17
 */
@Getter
public class DeleteRequest {
    /* 主键ID */
    private Long id;

    private static final long serialVersionUID = 1L;
}