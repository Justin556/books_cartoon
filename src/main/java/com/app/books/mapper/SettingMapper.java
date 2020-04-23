package com.app.books.mapper;

import com.app.books.entity.WebSite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SettingMapper {
    @Select("SELECT create_time as createTime,\n" +
            "customer_qq as customerQq,\n" +
            "customer_wx as customerWx,\n" +
            "day_share_gifts as dayShareGifts,\n" +
            "mh_deduct as mhDeduct,\n" +
            "platform_qr_code as platformQrCode,\n" +
            "proportion,\n" +
            "sign_to_give as signToGive,\n" +
            "site_name as siteName,\n" +
            "vip_year as vipYear,\n" +
            "xs_deduct as xsDeduct\n" +
            "FROM t_web_site LIMIT 0,1")
    WebSite getWebSite();
}
