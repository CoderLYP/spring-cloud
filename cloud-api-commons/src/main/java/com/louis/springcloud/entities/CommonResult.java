package com.louis.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author louis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private String code;

    private String  message;

    private T date;

    public CommonResult(String code, String message) {
        this(code, message, null);
    }
}
