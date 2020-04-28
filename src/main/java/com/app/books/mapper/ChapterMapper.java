package com.app.books.mapper;

import com.app.books.vo.ChapterQuery;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;



@Mapper
@Repository
public interface ChapterMapper {

    @Insert("INSERT INTO t_chapter(user_id,chapter,chapter_id,out_id,type,create_time)\n" +
            "VALUES (#{userId},#{chapter},#{chapterId},#{outId},#{type},now())")
    Integer addChapter(ChapterQuery chapterQuery);

    @Update("update t_chapter \n" +
            "set chapter_id=#{chapterId},chapter=#{chapter},create_time=now() where user_id=#{userId} and out_id=#{outId} and type= #{type}")
    Integer update(ChapterQuery chapterQuery);

    @Select("SELECT *,chapter_id as chapterId from t_chapter\n" +
            " WHERE user_id=#{userId} and out_id=#{outId} and type=#{type}")
    ChapterQuery selectChapter(ChapterQuery chapterQuery);

    /**
     * 获取最近观看的章节id
     * @param outId
     * @param type
     * @return
     */
    @Select("SELECT chapter_id as chapterId from t_chapter\n" +
            " WHERE out_id=#{outId} and type=#{type} order by create_time desc limit 0,1")
    Integer getNewestChapter(Integer outId, Integer type);


    /**
     * 删除历史记录
     * @param
     */
    @Delete("delete from t_chapter where out_id = #{outId} and type = #{type} and user_id = #{userId}")
    void deleteHistorical(Integer outId, Integer type,Integer userId);

    /**
     * 删除所有历史记录
     * @param
     */
    @Delete("delete from t_chapter where user_id = #{userId}")
    void deleteAllHistorical(Integer userId);
}
