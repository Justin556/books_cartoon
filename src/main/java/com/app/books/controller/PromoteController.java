package com.app.books.controller;


import com.app.books.config.AuthenticationInterceptor;
import com.app.books.vo.UserQuery;
import com.app.books.entity.UserRetailLevel;
import com.app.books.result.Result;
import com.app.books.service.PromoteService;
import com.app.books.utils.QrCodeUtils2;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/promote/")
@Controller
public class PromoteController {

    @Autowired
    private PromoteService promoteService;

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    //本人推广用户列表
    @GetMapping("page")
    public Result promoteList(UserQuery userQuery) {
        return promoteService.promoteList(userQuery);
    }

    //本人推广二维码生成
    @GetMapping("yards")
    public Result yards(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String text = "http://47.56.19.236/books/#/pages/register/register?type=2&id="+authenticationInterceptor.userId;
        //不含Logo
       String fileName = QrCodeUtils2.encode(text, null, "/home/project/picture/qrCode", true);
       Map<String,String> er=new HashMap<>();
        er.put("text",text);
        er.put("yards","/books/qrCode/"+fileName);
       return Result.success(er);
    }

    //增加本人推广用户
    @PostMapping("addPromote")
    public Result addPromote(UserRetailLevel userRetailLevel) {
        return promoteService.addPromote(userRetailLevel);
    }

    @GetMapping("download")
    @ApiOperation(value = "漫画图片下载")
    public void download() {


    }
}
