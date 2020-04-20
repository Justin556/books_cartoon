package com.app.books.mapper;

import com.app.books.entity.*;
import com.app.books.pojo.BookDetailsPojo;
import com.app.books.vo.BookQuery;
import org.apache.ibatis.annotations.Insert;
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
            "t_book_info.charge_money as chargeMoney, \n" +
            "t_book_info.charge_num as chargeNum, \n" +
            "t_book_info.episodes, \n" +
            "t_book_info.is_new as isNew, \n" +
            "t_book_info.is_recommend as isRecommend, \n" +
            "t_book_info.read_num as readNum, \n" +
            "t_book_info.reader,\n" +
            "(SELECT sum(amount) FROM t_user_send_log WHERE t_user_send_log.out_id = t_book.id) as sendSum,\n" +
            "(SELECT COUNT(1) FROM t_book_likes WHERE t_book_likes.bid = t_book.id) as likeSum,\n" +
            "(SELECT COUNT(1) FROM t_book_collect WHERE t_book_collect.bid = t_book.id) as collectSum,\n" +
            "(SELECT COUNT(1) FROM t_comment WHERE t_comment.out_id = t_book.id) as commentSum,\n" +
            "(SELECT COUNT(1) FROM t_book_episodes WHERE t_book_episodes.bid = t_book.id) as chapterSum\n" +
            "FROM t_book INNER JOIN t_book_info ON t_book.id = t_book_info.bid WHERE t_book.id = 1")
    BookDetailsPojo details(Integer bookId);

    @Insert("insert into t_user_send_log(create_time, out_id, user_id, amount) values(#{createTime}, #{outId}, #{userId}, #{amount})")
    void userSend(UserSendLog userSendLog);

    /**
     * 打赏列表
     * @param bookId
     * @return
     */
    @Select("select sum(u.amount) as userAmount, \n" +
            "u.user_id as userId,\n" +
            "(select t_user.user_name from t_user where t_user.id = u.user_id) as userName \n" +
            "from t_user_send_log u where out_id = #{bookId}\n" +
            "GROUP BY userId\n" +
            "ORDER BY userAmount desc")
    List<UserSendLog> userSendList(Integer bookId);

    @Insert("insert into t_comment(create_time, out_id, user_id, comment_info) values(#{createTime}, #{outId}, #{userId}, #{commentInfo})")
    void insertComment(Comment comment);

    /**
     * 评论列表
     * @param bookId
     * @return
     */
    @Select("select c.comment_info as commentInfo, \n" +
            "(select t_user.user_name from t_user where t_user.id = c.user_id) as userName \n" +
            "from t_comment c where c.out_id = #{bookId}\n" +
            "ORDER BY c.create_time desc")
    List<Comment> commentList(Integer bookId);

    /**
     * 章节列表
     * @param bookId
     * @return
     */
    @Select("SELECT b.title, \n" +
            "b.ji_no as jiNo,\n" +
            "b.money, \n" +
            "b.info  \n" +
            "FROM t_book_episodes b WHERE b.bid = #{bookId}\n" +
            "ORDER BY jiNo")
    List<BookEpisodes> bookEpisodeList(Integer bookId);



    /**
     * 查询该分类下的小说 前5项
     * @param category
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
     * 猜你喜欢
     * @return
     */
    @Select("SELECT id, title, category, summary, cover_pic as coverPic, author, (SELECT sum(amount) FROM t_user_send_log WHERE out_id = t_book.id) as countSend\n" +
            "FROM t_book\n" +
            "ORDER BY countSend DESC\n" +
            "LIMIT 0,6")
    List<Book> maybeLike();

    /**
     * 大家一起看
     * @return
     */
    @Select("SELECT id, title, category, summary, cover_pic as coverPic, author, (SELECT count(1) FROM t_book_likes WHERE bid = t_book.id) as countLike\n" +
            "FROM t_book\n" +
            "ORDER BY countLike DESC\n" +
            "LIMIT 0,6")
    List<Book> watchTogether();

    @Select("SELECT id, title, category, summary, cover_pic as coverPic, author\n" +
            "FROM t_book\n" +
            "WHERE category = 2\n" +
            "LIMIT 0,6")
    List<Book> girlLike();

    @Select("SELECT id, title, category, summary, cover_pic as coverPic, author\n" +
            "FROM t_book\n" +
            "WHERE category = 1\n" +
            "LIMIT 0,6")
    List<Book> boyLike();
}
