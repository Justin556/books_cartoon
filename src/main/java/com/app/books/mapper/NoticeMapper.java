package com.app.books.mapper;

import com.app.books.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NoticeMapper {
    @Select("SELECT id, \n" +
            "create_time as createTime, \n" +
            "content, \n" +
            "title \n" +
            "FROM t_notice ORDER BY createTime DESC LIMIT 0,1")
    Notice getNewestNotice();
}
