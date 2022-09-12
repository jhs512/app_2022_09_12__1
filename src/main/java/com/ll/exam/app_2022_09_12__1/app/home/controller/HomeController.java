package com.ll.exam.app_2022_09_12__1.app.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @RequestMapping("/")
    @ResponseBody
    public String showMain() {
        return "메인";
    }
}
