package com.lwhtarena.jd.controller;

import com.lwhtarena.jd.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author liwh
 * @Title: ContentController
 * @Package com.lwhtarena.jd.controller
 * @Description:
 * @Version 1.0.0
 * @date 2020/8/23 13:40
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/parse/{keyword}")
    @ResponseBody
    public Boolean parse(@PathVariable("keyword") String keyword) throws IOException {
        return contentService.parseContent(keyword);
    }

    @GetMapping("/find/{keyword}/{pageNo}/{pageSize}")
    public String find(@PathVariable("keyword") String keyword,
                         @PathVariable("pageNo") int pageNo,
                         @PathVariable("pageSize") int pageSize, Model model) throws IOException {

        if (pageNo == 0) {
            pageNo = 1;
        }
        System.out.println(keyword + pageNo + pageSize);
        List<Map<String, Object>> list =  contentService.searchPage(keyword, pageNo, pageSize);
        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public String search(@PathVariable("keyword") String keyword,
                         @PathVariable("pageNo") int pageNo,
                         @PathVariable("pageSize") int pageSize, Model model) throws IOException {

        if (pageNo == 0) {
            pageNo = 1;
        }
        List<Map<String, Object>> list =  contentService.searchPageHighlighter(keyword, pageNo, pageSize);
        model.addAttribute("list", list);
        return "index";
    }
}
