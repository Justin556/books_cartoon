package com.app.books.mapper;

import com.app.books.entity.Comic;
import com.app.books.vo.ChapterQuery;
import com.app.books.vo.ComicQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ChapterMapper {

    @Select("INSERT INTO chapter(user_id,chapter,chapter_id,out_id,type,create_time)\n" +
            "VALUES (#{userId},#{chapter},#{chapterId},#{outId},#{type},now())")
    Integer addChapter(ChapterQuery chapterQuery);


    @Select("SELECT *,chapter_id as chapterId from chapter\n" +
            " WHERE user_id=#{userId} and out_id=#{outId} and type=#{type}")
    ChapterQuery selectChapter(ChapterQuery chapterQuery);

}
