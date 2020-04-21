package com.app.books.service;

import com.app.books.vo.RegisterParams;

public interface UserService {
    /**
     * 注册
     * @param registerParams
     */
    void register(RegisterParams registerParams);
}
