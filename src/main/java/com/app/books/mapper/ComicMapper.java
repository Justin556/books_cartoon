package com.app.books.mapper;

import com.app.books.entity.*;
import com.app.books.pojo.ComicDetailsPojo;
import com.app.books.vo.ComicQuery;
import org.apache.ibatis.annotations.Insert;
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

    @Select("<script> select *,cover_pic as coverPic from t_comic where id = #{comicId} </script>")
    ComicDetailsPojo details(String comicId);

    /**
     * 打赏列表
     * @param comicId
     * @return
     */
    @Select("select u.amount as userAmount,u.create_time as createTime,\n" +
            "            (select t_user.user_name from t_user where t_user.id = u.user_id) as userName \n" +
            "            from t_user_send_log u \n" +
            "where out_id= #{comicId}")
    List<UserSendLog> userSendList(String comicId);
    /**
     * 打赏总金额
     * @param comicId
     * @return
     */
    @Select("select sum(u.amount) from t_user_send_log u where out_id = #{comicId}")
    Integer userSendMoneyList(String comicId);

    /**
     * 评论列表
     * @param comicId
     * @return
     */
    @Select("select c.comment_info as commentInfo ,\n" +
            "(select t_user.user_name from t_user where t_user.id = c.user_id) as userName \n" +
            "from t_comment c where c.out_id = #{comicId}\n" +
            "ORDER BY c.create_time desc")
    List<Comment> commentList(String comicId);

    /**
     * 章节列表
     * @param comicId
     * @return
     */
    @Select("SELECT *  \n" +
            "FROM t_comic_episodes b WHERE b.comic_id = #{comicId}\n" +
            "ORDER BY ji_no")
    List<ComicEpisodes> comicEpisodeList(String comicId);


    /**
     * 章节详情
     * @param comicId
     * @return
     */
    @Select("SELECT *  \n" +
            "FROM t_comic_banner b WHERE b.comic_id = #{comicId}")
    List<ComicBanner> bannerDetails(String comicId);

    /**
     * 漫画点赞
     * @param comicLikes
     * @return
     */
    @Insert("insert t_comic_likes(create_time,comic_id,user_id)\n" +
            "VALUES(now(),#{comicId},#{userId})")
    List<ComicLikes> addComicLikes(ComicLikes comicLikes);

}
