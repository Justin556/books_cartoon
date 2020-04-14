package com.app.books.service;

import com.app.books.entity.UserRetailLevel;
import com.app.books.result.Result;

public interface PromoteService {

    Result promoteList();

    Result addPromote(UserRetailLevel userRetailLevel);
}
