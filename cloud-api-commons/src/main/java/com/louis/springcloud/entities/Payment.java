package com.louis.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author louis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment  implements Serializable{

    private Long id;

    private String serial;
}
