package com.example.book_app.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditReviewPageController {

    @GetMapping("/editReview")
    public String editReviewPage() {
        return "editReviewPage";
    }
}
