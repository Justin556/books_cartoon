package com.app.books.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;


@Data
public class RegisterParams {
    @NotBlank
    @Length(max = 16,message = "请输入不超过16位长度的用户名")
    private String userName;

    @NotBlank
    @Pattern(regexp= "^(?=.*([a-zA-Z].*))(?=.*[0-9].*)[a-zA-Z0-9-*/+.~!@#$%^&*()]{6,20}$", message= "请输入6-20位密码，至少包含数字和字母的组合")
    private String password;

    //注册来源（0：平台  1：代理  2：分销）
    private Integer type;
    //代理或分销的id
    private Integer id;
}
