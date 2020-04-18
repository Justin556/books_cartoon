package com.app.books.entity;;

import lombok.Data;

import java.util.Date;


/**
 * 站点设置表
 */
@Data
public class WebSite {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    //站点名称
    private String siteName;

    //日分享赠送
    private Integer dayShareGifts = 0;

    //平台分享二维码
    private String platformQrCode;

    //客服QQ
    private String customerQq;

    //客服微信
    private String customerWx;

    //年费会员金额
    private Integer vipYear;

    //充值比例
    private Integer proportion;

    //漫画默认扣除扣费章节
    private Integer mhDeduct;

    //小说默认扣除扣费章节
    private Integer xsDeduct = 0;

    //签到赠送
    private Integer signToGive = 0;


}
