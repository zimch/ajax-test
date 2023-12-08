package ru.zimch.ajaxtest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер для отрисовки клиента
 */
@Controller
@RequestMapping
public class WebController {

    @GetMapping
    public String getIndexPage() {
        return "index";
    }

}
