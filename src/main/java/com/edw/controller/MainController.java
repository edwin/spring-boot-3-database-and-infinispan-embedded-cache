package com.edw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <pre>
 *  com.edw.controller.MainController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 10 Nov 2025 9:15
 */
@RestController
public class MainController {

    @GetMapping(path = "/")
    public HashMap index() {
        return new HashMap(){{
            put("hello", "world");
        }};
    }

}
