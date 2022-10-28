package com.uta.quiz.controller;

import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author Jianxiang Wang
 * @create 2022-10-28-9:07
 */
@Controller
public class QuizController {
    @RequestMapping("/q10")
    public String page1() {
        return "Q10";
    }

    @RequestMapping("/q11")
    public String page2() {
        return "Q11";
    }

    @RequestMapping("/q12")
    public String page3() {
        return "Q12";
    }

    @GetMapping("/question10")
    @ResponseBody
    public String question10(@RequestParam("words") String words) {
        String a = words.replaceAll("\\pP|\\pS", "").replace(" ","").toLowerCase();
        HashMap<String,Integer> map = new HashMap<>();
        for (int i = 0; i < a.length();i++) {
            String str = String.valueOf(a.charAt(i));
            map.put(str,map.getOrDefault(str,0)+1);
        }
        return JSONUtils.toJSONString(map);
    }

    @GetMapping("/question12")
    @ResponseBody
    public String question12(@RequestParam("words") String words) {
        long startTime = System.currentTimeMillis();
        String a = words.replaceAll("\\pP|\\pS", "").toLowerCase();
        HashMap<String,Object> map = new HashMap<>();
        String[] strArr = a.split(" ");
        for (String s : strArr) {
            if ("".equals(s)) {
                continue;
            }
            map.put(s,(Integer)map.getOrDefault(s,0)+1);
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        map.put("consume time",time + " ms");
        return JSONUtils.toJSONString(map);
    }

    @GetMapping("/question11")
    @ResponseBody
    public String question11(
            @RequestParam("text2") String text2,
            @RequestParam("text3") String text3
    ) {
        // 对text3进行处理，去除空格
        String newText2 = text2.toLowerCase();
        String newText3 = text3.replace(" ", "");
        Map<String, List<Integer>> map = new HashMap<>();
        // 遍历text3中的每一个字符，并在text1中寻找
        for (int i = 0; i < newText3.length(); i++) {
            char temp = newText3.charAt(i);
            List<Integer> list = new ArrayList<>(); // 用于存储每一个字符出现的位置
            // 遍历text2
            for (int j = 0; j < newText2.length(); j++) {
                if (newText2.charAt(j) == temp) {
                    list.add(j);
                }
            }
            map.put(String.valueOf(temp),list);
        }
        return JSONUtils.toJSONString(map);
    }
}
