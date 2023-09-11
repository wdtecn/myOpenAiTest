package com.wdtecn.myopenaitest.service.impl;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.wdtecn.myopenaitest.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenAiServiceImpl implements OpenAiService {

    @Value("${openai.api-key}")
    private String apiKey;

    @Override
    public String correctText(String inputText) throws Exception {

        // 拼接发送给openAi的参数
        String prompt = "纠正以下句子的语法错误：\n" + inputText;
        // 计算openAi返回的长度
        Integer length = inputText.length() + 10;

        // 组装参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", "text-davinci-003"); // 模块
        jsonObject.put("prompt", prompt);            // 句子
        jsonObject.put("max_tokens", length);        // 返回昌都

        // 调用openAi接口
        String postResult = HttpRequest
                .post("https://api.openai.com/v1/completions")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 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")
                .body(jsonObject.toJSONString())
                .execute()
                .body();

        // 解析返回值
        JSONObject jsonResponse = JSON.parseObject(postResult);
        String correctedText = jsonResponse.getJSONArray("choices")
                .getJSONObject(0)
                .getString("text");

        System.out.println("纠正后的句子：" + correctedText);

        // 返回正确句子并替换掉多余字符
        return correctedText.replace("\n", "").replace("\r", "");
    }
}
