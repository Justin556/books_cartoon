package com.app.books.service;

import com.app.books.dto.UserQuery;
import com.app.books.entity.UserRetailLevel;
import com.app.books.result.Result;

public interface PromoteService {

    Result promoteList(UserQuery userQuery);

    Result addPromote(UserRetailLevel userRetailLevel);
}
