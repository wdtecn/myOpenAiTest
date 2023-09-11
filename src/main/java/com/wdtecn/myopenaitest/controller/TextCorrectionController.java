package com.wdtecn.myopenaitest.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.wdtecn.myopenaitest.service.OpenAiService;
import com.wdtecn.myopenaitest.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import okhttp3.*;
import javax.annotation.PostConstruct;

/**
 * openAi控制器
 */
@RestController
@RequestMapping("/api")
public class TextCorrectionController {
    @Autowired
    private OpenAiService openAiService;

    /**
     * 专门提供纠错的接口
     * @param inputText 需要纠错的句子
     * @return
     * @throws Exception
     */
    @PostMapping("/correct")
    public R<?> correctText(@RequestBody String inputText) throws Exception {

        if (StrUtil.isEmpty(inputText)) {
            return R.fail("参数不能为空！");
        }

        String correctedText = "";
        try {
            correctedText = openAiService.correctText(inputText);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("服务器繁忙，请稍后重试！");
        }

        return R.ok(correctedText);
    }


}
