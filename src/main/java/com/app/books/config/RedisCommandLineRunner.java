package com.app.books.config;

import com.app.books.mapper.BookMapper;
import com.app.books.mapper.ChapterMapper;
import com.app.books.mapper.UserMapper;
import com.app.books.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisCommandLineRunner implements CommandLineRunner {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public void run(String... args) throws Exception{
        System.out.println("This will be execute when the project was started!");
        for (Integer bookId: bookMapper.getAllIdFromBook()
             ) {
            redisUtil.set(bookId + ":episodeList", bookMapper.bookEpisodeList(bookId));
            //log.info("bookId为：" + bookId + "的章节缓存到redis");
        }
    }
}
