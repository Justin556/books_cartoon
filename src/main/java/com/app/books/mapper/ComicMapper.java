package com.app.books.mapper;

import com.app.books.entity.*;
import com.app.books.pojo.ComicDetailsPojo;
import com.app.books.vo.ComicQuery;
import org.apache.ibatis.annotations.Delete;
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
     * 打赏
     * @param userSendLog
     * @return
     */
    @Select("select sum(u.amount) from t_user_send_log u where out_id = #{comicId}")
    UserSendLog userSend(UserSendLog userSendLog);

    /**
     * 评论列表
     * @param comicId
     * @return
     */
    @Select("select c.comment_info as commentInfo,c.create_time as createTime ,\n" +
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
     * 根据id查章节
     * @param id
     * @return
     */
    @Select("SELECT id,comic_id as comicId,ji_no as jiNo,title  \n" +
            "FROM t_comic_episodes b WHERE id=#{id}")
    ComicEpisodes getEpisodeById(String id);

    /**
     * 章节详情
     * @param comicId
     * @return
     */
    @Select("SELECT *  \n" +
            "FROM t_comic_banner b WHERE b.comic_id = #{comicId}")
    List<ComicBanner> bannerDetails(String comicId);


    /**
     * 漫画是否点赞
     * @param userId
     * @param comicId
     * @return
     */
    @Select("select COUNT(1) from t_comic_likes\n" +
            "where comic_id=#{comicId} and user_id=#{userId}")
    Integer likeStatus(String userId,String comicId);

    /**
     * 漫画是否收藏
     * @param userId
     * @param comicId
     * @return
     */
    @Select("select COUNT(1) from t_comic_collect\n" +
            "where user_id=#{userId} and comic_id=#{comicId}")
    Integer collectStatus(String userId,String comicId);

    /**
     * 通过comicId和userId获取点赞记录的id
     * @param comicId
     * @return
     */
    @Select("SELECT id FROM t_comic_likes WHERE comic_id = #{comicId} and user_id = #{userId}")
    Integer getLikeIdByComicIdIdAndUserId(Integer comicId, Integer userId);

    /**
     * 新增点赞
     * @param comicLikes
     */
    @Insert("insert into t_comic_likes(create_time, user_id, comic_id) values(now(), #{userId}, #{comicId})")
    void insertComicLike(ComicLikes comicLikes);

    /**
     * 取消点赞
     * @param
     */
    @Delete("delete from t_comic_likes where user_id = user_id and comic_id = #{comicId}")
    void deleteComicLike(Integer comicId, Integer userId);

    /**
     * 通过comicId和userId获取收藏记录的id
     * @param comicId
     * @return
     */
    @Select("SELECT id FROM t_comic_collect WHERE comic_id = #{comicId} and user_id = #{userId}")
    Integer getCollectIdByComicIdIdAndUserId(Integer comicId, Integer userId);

    /**
     * 新增收藏
     * @param comicCollect
     */
    @Insert("insert into t_comic_collect(create_time, user_id, comic_id) values(now(), #{userId}, #{comicId})")
    void insertComicCollect(ComicCollect comicCollect);

    /**
     * 取消收藏
     * @param
     */
    @Delete("delete from t_comic_collect where user_id = user_id and comic_id = #{comicId}")
    void deleteComicCollect(Integer comicId, Integer userId);
}
