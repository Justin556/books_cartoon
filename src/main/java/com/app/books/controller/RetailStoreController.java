package com.app.books.controller;

import com.app.books.mapper.RetailStoreMapper;
import com.app.books.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "分佣规则")
@RequestMapping("/retailStore/")
public class RetailStoreController {
    @Autowired
    private RetailStoreMapper retailStoreMapper;

    @GetMapping("show")
    @ApiOperation(value = "分佣规则")
    public Result show() {
        return Result.success(retailStoreMapper.getRetailStore());
    }
}
