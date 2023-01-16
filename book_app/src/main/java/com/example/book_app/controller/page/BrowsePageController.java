package com.example.book_app.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrowsePageController {

    @GetMapping("/browse")
    public String browsePage() {
        return "browsePage";
    }
}
