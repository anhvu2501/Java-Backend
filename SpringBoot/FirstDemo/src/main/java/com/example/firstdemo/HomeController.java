package com.example.firstdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //Chỉ định HomeController là Controller
public class HomeController {
    //Khi user truy cập vào endpoint / thì homepage() được gọi
    @GetMapping("/")
    public String homepage() {
        return "index"; //Trả về trang index.html
    }
    //... Có thể mapping thêm nhiều endpoint khác nữa

    //Thymeleaf helps to pass data into View of MVC model, returns HTML page holds data for the client
}
