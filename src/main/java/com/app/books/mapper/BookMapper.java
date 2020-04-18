package com.app.books.mapper;

import com.app.books.dto.BookQuery;
import com.app.books.entity.BookInfo;
import com.app.books.entity.Comic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface BookMapper {

    @Select("<script> select *,cover_pic as coverPic,detail_pic as detailPic from t_book where 1=1 " +
            "<if test=\"name !=null and name !=''\"> AND title like \"%\"#{name}\"%\" </if>" +
            "<if test=\"status !=null and status !=''\"> AND status =#{status} </if>" +
            "</script>")
    List<Comic> findAll(BookQuery bookQuery);

    @Select("<script> select * from t_book_info where bid = #{bookId} </script>")
    BookInfo details(int bookId);
}
