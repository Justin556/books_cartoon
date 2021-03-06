package com.app.books.exception;

import com.app.books.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@Slf4j
@RestControllerAdvice
public class CustomerExceptionHandler {
    /**
     * 自定义异常
     */
    @ExceptionHandler(Exception.class)
    public Result CustomerException(Exception e) {
        if (e instanceof CustomerException) {
            CustomerException ex = (CustomerException) e;
            return Result.error(ex.getCode(), ex.getMsg());
        }
        e.printStackTrace();
        return Result.error(-99, e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result DuplicateKeyException(DuplicateKeyException e) {
        e.printStackTrace();
        return Result.error("数据库中已存在该记录");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result IllegalArgumentException(IllegalArgumentException e) {
        e.printStackTrace();
        return Result.error("参数异常！");
    }

    @ExceptionHandler(NullPointerException.class)
    public Result NullPointerException(NullPointerException e) {
        e.printStackTrace();
        return Result.error("空指针！");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result DataIntegrityViolationException(DataIntegrityViolationException e) {
        Throwable mostSpecificCause = e.getMostSpecificCause();
        e.printStackTrace();
        return Result.error("编辑数据异常:");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return Result.error("参数类型不匹配！");
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, NumberFormatException.class})
    public Result MethodArgumentTypeMismatchException(Exception e) {
        e.printStackTrace();
        return Result.error("参数类型不匹配！");
    }
}
