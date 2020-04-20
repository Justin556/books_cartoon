package com.app.books.mapper;

import com.app.books.vo.ComicQuery;
import com.app.books.entity.Comic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ComicMapper {

    @Select("<script> select *,cover_pic as coverPic,detail_pic as detailPic from t_comic where 1=1 " +
            "<if test=\"name !=null and name !=''\"> AND title like \"%\"#{name}\"%\" </if>" +
            "<if test=\"category !=null and category !=''\"> AND category  like \"%\"#{category}\"%\" </if>" +
            "<if test=\"status !=null and status !=''\"> AND status  like \"%\"#{status}\"%\" </if>" +
            "</script>")
    List<Comic> findAll(ComicQuery comicQuery);

    @Select("<script> select * from t_comic where id = #{id} </script>")
    Comic details(ComicQuery comicQuery);
}
