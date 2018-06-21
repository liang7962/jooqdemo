package com.jin.jooq.controller;

import com.jin.jooq.dto.Prod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class ThymeleafController {
    private Logger logger = LoggerFactory.getLogger(ThymeleafController.class);
    /**
     * 测试hello
     * @return
     */
    @RequestMapping(value = "/thdemo1",method = RequestMethod.GET)
    public String thdemo1(Model model) {
        model.addAttribute("count", "4");
        model.addAttribute("execMode","div");
        model.addAttribute("role","manager");
        model.addAttribute("manager","liang");
        List<Prod> prods=new ArrayList<>();
        for (int i=0;i<5;i++){
            Prod p=new Prod("name"+i,"price"+i,i%2==0?false:true);
            prods.add(p);
        }
        model.addAttribute("prods",prods);
        return "framework/demo/thdemo1";
    }

}
