package com.example.book_app.controller.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handle(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error-403";
            }

            if (statusCode == HttpStatus.CONFLICT.value()) {
                return "error-409";
            }

        }
        return "error";
    }
}
