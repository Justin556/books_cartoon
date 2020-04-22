package com.app.books.service;

import com.app.books.vo.RegisterParams;

public interface UserService {

    void register(RegisterParams registerParams);

    String login(String userName, String password);
}
