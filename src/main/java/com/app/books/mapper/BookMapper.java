package com.app.books.mapper;

import com.app.books.entity.*;
import com.app.books.pojo.BookDetailsPojo;
import com.app.books.vo.BookParams;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface BookMapper {

    @Select("<script> select *,cover_pic as coverPic,detail_pic as detailPic from t_book where 1=1 " +
            "<if test=\"name !=null and name !=''\"> AND title like \"%\"#{name}\"%\" OR author like \"%\"#{name}\"%\" </if>" +
            "<if test=\"category !=null and category !=''\"> AND category = #{category} </if>" +
            "<if test=\"status !=null and status !=''\"> AND status =#{status} </if>" +
            "</script>")
    List<Book> findAll(BookParams bookParams);

    @Select("SELECT t_book.id, \n" +
            "t_book.create_time as createTime, \n" +
            "t_book.title, \n" +
            "t_book.author, \n" +
            "t_book.category, \n" +
            "t_book.status, \n" +
            "t_book.number, \n" +
            "t_book.summary, \n" +
            "t_book.cover_pic as coverPic, \n" +
            "t_book.detail_pic as detailPic, \n" +
            "t_book.sort, \n" +
            "(SELECT sum(amount) FROM t_user_send_log WHERE t_user_send_log.out_id = t_book.id) as sendSum,\n" +
            "(SELECT COUNT(1) FROM t_book_likes WHERE t_book_likes.bid = t_book.id) as likeSum,\n" +
            "(SELECT COUNT(1) FROM t_book_collect WHERE t_book_collect.bid = t_book.id) as collectSum,\n" +
            "(SELECT COUNT(1) FROM t_comment WHERE t_comment.out_id = t_book.id) as commentSum,\n" +
            "(SELECT COUNT(1) FROM t_book_episodes WHERE t_book_episodes.bid = t_book.id) as chapterSum\n" +
            "FROM t_book WHERE t_book.id = #{bookId}")
    BookDetailsPojo details(Integer bookId);

    /**
     * 新增打赏
     * @param userSendLog
     */
    @Insert("insert into t_user_send_log(create_time, out_id, user_id, amount,type) values(#{createTime}, #{outId}, #{userId}, #{amount}, #{type})")
    void userSend(UserSendLog userSendLog);

    /**
     * 通过bookId和userId获取点赞记录的id
     * @param bookId
     * @return
     */
    @Select("SELECT id FROM t_book_likes WHERE bid = #{bookId} and user_id = #{userId}")
    Integer getLikeIdByBookIdAndUserId(Integer bookId, Integer userId);

    /**
     * 新增点赞
     * @param bookLikes
     */
    @Insert("insert into t_book_likes(create_time, user_id, bid) values(#{createTime}, #{userId}, #{bid})")
    void insertBookLike(BookLikes bookLikes);

    /**
     * 取消点赞
     * @param
     */
    @Delete("delete from t_book_likes where user_id = user_id and bid = #{bookId}")
    void deleteBookLike(Integer bookId, Integer userId);

    /**
     * 通过bookId和userId获取收藏记录的id
     * @param bookId
     * @return
     */
    @Select("SELECT id FROM t_book_collect WHERE bid = #{bookId} and user_id = #{userId}")
    Integer getCollectIdByBookIdAndUserId(Integer bookId, Integer userId);

    /**
     * 新增收藏
     * @param bookCollect
     */
    @Insert("insert into t_book_collect(create_time, user_id, bid, title, cover_pic) values(#{createTime}, #{userId}, #{bid}, #{title}, #{coverPic})")
    void insertBookCollect(BookCollect bookCollect);

    /**
     * 取消收藏
     * @param
     */
    @Insert("delete from t_book_collect where user_id = #{userId} and bid = #{bookId}")
    void deleteBookCollect(Integer bookId, Integer userId);

    /**
     * 获取用户的小说收藏列表
     * @param userId
     * @return
     */
    @Select("SELECT create_time as createTime,\n" +
            "bid,\n" +
            "title,\n" +
            "cover_pic as coverPic\n" +
            "FROM t_book_collect\n" +
            "where user_id = #{userId}")
    List<BookCollect> getBookCollectList(Integer userId);

    /**
     * 打赏列表
     * @param bookId
     * @return
     */
    @Select("select sum(u.amount) as userAmount, \n" +
            "u.user_id as userId,\n" +
            "(select t_user.user_name from t_user where t_user.id = u.user_id) as userName \n" +
            "from t_user_send_log u where out_id = #{bookId} and type=1\n" +
            "GROUP BY userId ")
    List<UserSendLog> userSendList(Integer bookId);

    @Insert("insert into t_comment(create_time, out_id, user_id, comment_info) values(#{createTime}, #{outId}, #{userId}, #{commentInfo})")
    void insertComment(Comment comment);

    /**
     * 评论列表
     * @param bookId
     * @return
     */
    @Select("select c.comment_info as commentInfo, \n" +
            "c.create_time as createTime, \n" +
            "(select t_user.user_name from t_user where t_user.id = c.user_id) as userName \n" +
            "from t_comment c where c.out_id = #{bookId}\n" +
            "ORDER BY c.create_time desc")
    List<Comment> commentList(Integer bookId);

    /**
     * 章节列表
     * @param bookId
     * @return
     */
    @Select("SELECT b.title, b.id, b.bid,\n" +
            "b.ji_no as jiNo,\n" +
            "b.money, \n" +
            "b.info  \n" +
            "FROM t_book_episodes b WHERE b.bid = #{bookId}\n" +
            "ORDER BY jiNo")
    List<BookEpisodes> bookEpisodeList(Integer bookId);

    /**
     * 单个章节的内容
     * @param jiNo 章节内容id
     * @return
     */
    @Select("SELECT content FROM t_book_episodes_content WHERE id = #{jiNo}")
    String episodesContent(Integer jiNo);

    /**
     * 查询该分类下的小说 前5项
     * @param category 分类标识
     * @return
     */
    @Select("SELECT id, title, category, summary, cover_pic as coverPic, author\n" +
            "FROM t_book WHERE category = #{category}\n" +
            "LIMIT 0,5")
    List<Book> getBookByCategory(Integer category);

    /**
     * 查询该分类下的小说
     * @param category
     * @return
     */
    @Select("SELECT id, title, category, summary, cover_pic as coverPic, author\n" +
            "FROM t_book WHERE category = #{category}")
    List<Book> categoryPageList(Integer category);

    /**
     * 猜你喜欢 前6项
     * @return
     */
    @Select("SELECT id, title, category, summary, cover_pic as coverPic, author, (SELECT sum(amount) FROM t_user_send_log WHERE out_id = t_book.id) as countSend\n" +
            "FROM t_book\n" +
            "ORDER BY countSend DESC\n" +
            "LIMIT 0,6")
    List<Book> maybeLike();

    /**
     * 猜你喜欢 全部
     * @return
     */
    @Select("SELECT id, title, category, summary, cover_pic as coverPic, author, (SELECT sum(amount) FROM t_user_send_log WHERE out_id = t_book.id) as countSend\n" +
            "FROM t_book\n" +
            "ORDER BY countSend DESC")
    List<Book> maybeLikeAll();

    /**
     * 大家一起看 前6项
     * @return
     */
    @Select("SELECT id, title, category, summary, cover_pic as coverPic, author, (SELECT count(1) FROM t_book_likes WHERE bid = t_book.id) as countLike\n" +
            "FROM t_book\n" +
            "ORDER BY countLike DESC\n" +
            "LIMIT 0,6")
    List<Book> watchTogether();

    /**
     * 大家一起看 全部
     * @return
     */
    @Select("SELECT id, title, category, summary, cover_pic as coverPic, author, (SELECT count(1) FROM t_book_likes WHERE bid = t_book.id) as countLike\n" +
            "FROM t_book\n" +
            "ORDER BY countLike DESC")
    List<Book> watchTogetherAll();

    /**
     * 女生喜欢 前6项
     * @return
     */
    @Select("SELECT id, title, category, summary, cover_pic as coverPic, author\n" +
            "FROM t_book\n" +
            "WHERE category = 2\n" +
            "LIMIT 0,6")
    List<Book> girlLike();

    /**
     * 女生喜欢 全部
     * @return
     */
    @Select("SELECT id, title, category, summary, cover_pic as coverPic, author\n" +
            "FROM t_book\n" +
            "WHERE category = 2")
    List<Book> girlLikeAll();

    /**
     * 男生喜欢 前6项
     * @return
     */
    @Select("SELECT id, title, category, summary, cover_pic as coverPic, author\n" +
            "FROM t_book\n" +
            "WHERE category = 1\n" +
            "LIMIT 0,6")
    List<Book> boyLike();

    /**
     * 男生喜欢 全部
     * @return
     */
    @Select("SELECT id, title, category, summary, cover_pic as coverPic, author\n" +
            "FROM t_book\n" +
            "WHERE category = 1")
    List<Book> boyLikeAll();

    @Select("SELECT t_book.id, \n" +
            "t_book.create_time as createTime, \n" +
            "t_book.title, \n" +
            "t_book.author, \n" +
            "t_book.category, \n" +
            "t_book.status, \n" +
            "t_book.number, \n" +
            "t_book.summary, \n" +
            "t_book.cover_pic as coverPic, \n" +
            "t_book.detail_pic as detailPic, \n" +
            "t_book.sort \n" +
            "FROM t_book WHERE t_book.id = #{bookId}")
    Book getBookById(Integer bookId);
}
