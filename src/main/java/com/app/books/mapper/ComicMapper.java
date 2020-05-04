package com.app.books.mapper;

import com.app.books.entity.*;
import com.app.books.pojo.ComicDetailsPojo;
import com.app.books.vo.ChapterQuery;
import com.app.books.vo.ComicQuery;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ComicMapper {

    @Select("<script> select *,cover_pic as coverPic,detail_pic as detailPic from t_comic where 1=1 " +
            "<if test=\"name !=null and name !=''\"> AND title like \"%\"#{name}\"%\" </if>" +
            "<if test=\"category !=null and category !=''\"> AND category  like \"%\"#{category}\"%\" </if>" +
            "<if test=\"status !=null and status !=''\"> AND status  like \"%\"#{status}\"%\" </if>" +
            "<if test=\"comments !=null and comments !=''\"> AND comments  like \"%\"#{comments}\"%\" </if>" +
             "</script>")
    List<Comic> findAll(ComicQuery comicQuery);

    @Select("select *,cover_pic as coverPic,detail_pic as detailPic from t_comic where 1=1 " +
            "             ORDER BY send desc ")
    List<Comic> sendRanking();

    @Select("select *,cover_pic as coverPic,detail_pic as detailPic from t_comic where 1=1 " +
            "             ORDER BY likes desc ")
    List<Comic> likesRanking();

    @Select("select *,cover_pic as coverPic,detail_pic as detailPic from t_comic where 1=1 " +
            "             ORDER BY collect desc ")
    List<Comic> collectRanking( );

    @Select("select *,cover_pic as coverPic,detail_pic as detailPic from t_comic where 1=1 " +
            "             ORDER BY comments desc ")
    List<Comic> commentsRanking();


    @Update("<script> update t_comic  " +
            "<if test=\"likes !=null and likes !=''\"> set likes = #{likes}+likes  </if>" +
            "<if test=\"send !=null and send !=''\"> set send = #{send}+send  </if>" +
            "<if test=\"collect !=null and collect !=''\"> set collect = #{collect}+collect </if>" +
            "<if test=\"comments !=null and comments !=''\"> set comments = #{comments}+comments </if>" +
            "where id=#{comicId}</script>")
    void update(ComicQuery comicQuery);

    @Select("<script> select *,cover_pic as coverPic,(SELECT COUNT(1) FROM t_comic_likes WHERE t_comic_likes.comic_id = t_comic.id) as likeSum," +
            "(SELECT COUNT(1) FROM t_comic_collect WHERE t_comic_collect.comic_id = t_comic.id) as collectSum" +
            " from t_comic where id = #{comicId} </script>")
    ComicDetailsPojo details(String comicId);

    /**
     * 打赏列表
     * @param comicId
     * @return
     */
    @Select("select u.amount as userAmount,u.create_time as createTime,\n" +
            "            (select t_user.user_name from t_user where t_user.id = u.user_id) as userName, \n" +
            "(select t_user.portrait from t_user where t_user.id = u.user_id) as portrait \n" +
            "            from t_user_send_log u \n" +
            "where out_id= #{comicId} and type=2")
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
            "(select t_user.user_name from t_user where t_user.id = c.user_id) as userName, \n" +
            "(select t_user.portrait from t_user where t_user.id = c.user_id) as portrait \n" +
            "from t_comment c where c.out_id = #{comicId} and c.type=2\n" +
            "ORDER BY c.create_time desc")
    List<Comment> commentList(String comicId);

    @Insert("insert into t_comment(create_time, out_id, user_id, comment_info,type) values(now(), #{outId}, #{userId}, #{commentInfo},2)")
    void insertComment(Integer outId,Integer userId,String commentInfo);

    /**
     * 章节列表
     * @param comicId
     * @return
     */
    @Select("SELECT * \n" +
            "FROM t_comic_episodes b WHERE b.comic_id = #{comicId}\n"
            )
    List<ComicEpisodes> comicEpisodeList(String comicId);
    /**
     * 章节详情
     * @param id
     * @return
     */
    @Select("SELECT * \n" +
            "FROM t_comic_episodes b WHERE b.id = #{id}\n"
    )
    ComicEpisodes getEpisodeBy(String id);

    @Insert("INSERT INTO t_comic_ispay(create_time, user_id, chapter_id, is_pay)\n" +
            "VALUE(NOW(), #{userId}, #{chapterId}, #{isPay})")
    void addComicIsPay(ComicIsPay ComicIsPay);

    @Select("SELECT is_pay FROM t_comic_ispay\n" +
            "WHERE user_id = #{userId} AND chapter_id = #{chapterId}")
    String getIsPay(Integer userId, String chapterId);
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
    @Delete("delete from t_comic_likes where user_id = #{userId} and comic_id = #{comicId}")
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
    @Delete("delete from t_comic_collect where user_id = #{userId} and comic_id = #{comicId}")
    void deleteComicCollect(Integer comicId, Integer userId);

    /**
     * 收藏历史
     * @param comicCollect
     * @return
     */
    @Select("SELECT tc.cover_pic as coverPic,tcc.comic_id as comicId,tc.summary as summary,tc.title FROM t_comic_collect tcc\n" +
            "LEFT JOIN t_comic tc on tcc.comic_id=tc.id\n" +
            "WHERE user_id = #{userId}")
    List<ComicCollect> closedHistory(ComicCollect comicCollect);


    /**
     * 历史记录
     * @param userId
     * @return
     */
    @Select("SELECT * from(\n" +
            "SELECT tc.id as outId,tc.cover_pic as coverPic,tc.title,2 as type,tce.title as chapter,c.create_time as createTime FROM t_chapter c\n" +
            "LEFT JOIN t_comic tc on c.out_id=tc.id\n" +
            "LEFT JOIN t_comic_episodes tce on tce.id=c.chapter_id\n" +
            "WHERE user_id =  #{userId} and c.type=2\n" +
            "UNION all\n" +
            "SELECT tc.id as outId,tc.cover_pic as coverPic,tc.title,1 as type,tce.title as chapter,c.create_time as createTime FROM t_chapter c\n" +
            "LEFT JOIN t_book tc on c.out_id=tc.id \n" +
            "LEFT JOIN t_book_episodes tce on tce.id=c.chapter_id\n" +
            "WHERE user_id =  #{userId} and c.type=1\n" +
            ") a\n" +
            "ORDER BY createTime DESC ")
    List<ChapterQuery> historicalRecord(String userId);

    /**
     * 历史记录
     * @return
     */
    @Select("select * from t_comic_type")
    List<ComicType> comictype();

}
