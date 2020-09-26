package com.loserico.bootmybatisplus.vo;

import lombok.Data;

/**
 * Created by smlz on 2019/12/9.
 */
@Data
public class ResultVO {
    private boolean success;
    private String msg;
    private Object data;
}
