package com.lee.video.entity.xs;;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 用户打赏
 */
@Data
@Entity
@Table(name = "t_user_send_log")
public class UserSendLog extends BaseEntity {

    //用户id
    private Integer userId;

    //打赏书币（金额）类型（0书币，1金额）
    private Integer sendType;

    //打赏小说或漫画id
    private Integer outId;

    //打赏金额
    private Integer amount;

    //打赏小说或漫画的某个章节id
    private Integer zjId;
}
