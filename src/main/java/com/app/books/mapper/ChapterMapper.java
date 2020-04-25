package com.app.books.mapper;

import com.app.books.vo.ChapterQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;



@Mapper
@Repository
public interface ChapterMapper {

    @Insert("INSERT INTO chapter(user_id,chapter,chapter_id,out_id,type,create_time)\n" +
            "VALUES (#{userId},#{chapter},#{chapterId},#{outId},#{type},now())")
    Integer addChapter(ChapterQuery chapterQuery);

    @Update("update chapter \n" +
            "set chapter=#{chapter},chapter_id=#{chapterId},chapter=#{chapter},create_time=now() where user_id=#{userId} and out_id=#{outId} and type= #{type}")
    Integer update(ChapterQuery chapterQuery);

    @Select("SELECT *,chapter_id as chapterId from chapter\n" +
            " WHERE user_id=#{userId} and out_id=#{outId} and type=#{type}")
    ChapterQuery selectChapter(ChapterQuery chapterQuery);

    /**
     * 获取最近观看的章节id
     * @param outId
     * @param type
     * @return
     */
    @Select("SELECT chapter_id as chapterId from chapter\n" +
            " WHERE out_id=#{outId} and type=#{type} order by create_time desc limit 0,1")
    Integer getNewestChapter(Integer outId, Integer type);

}
