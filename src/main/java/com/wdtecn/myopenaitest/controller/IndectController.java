package com.wdtecn.myopenaitest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * openAi控制器
 */
@Controller
public class IndectController {

    /**
     * 首页
     * @return
     * @throws Exception
     */
    @GetMapping("/")
    public String toIndex() {
        return "index.html";
    }


}
