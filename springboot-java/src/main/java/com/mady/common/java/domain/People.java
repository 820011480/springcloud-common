package com.mady.common.java.domain;

import com.mady.common.java.utils.ValidatorUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/10/15 14:56
 * @description people
 */

@Slf4j
@Data
public class People {
    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    private String name;
    /**
     * 证件号
     */
    @NotNull(message = "证件号不能为空")
    private String certificate;
    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    private int gender;
    /**
     * 出生日期
     */
    @NotNull(message = "出生日期不能为空")
    private Date birthday;
    /**
     * 地址
     */
    @NotNull(message = "地址不能为空")
    private String address;


    public static void main(String[] args) {
        People people = new People();
        List<String> valid = ValidatorUtils.valid(people, null);
        System.out.println(valid);
    }
}
