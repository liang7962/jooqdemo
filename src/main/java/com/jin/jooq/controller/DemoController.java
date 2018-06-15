package com.jin.jooq.controller;

import com.jin.jooq.service.DemoService;
import com.jin.jooq.tables.tables.pojos.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("demo")
    public @ResponseBody
    String demoController() {
        List<Register> all = demoService.findAll();
        return all.toString();
    }

    @RequestMapping("demo2")
    public @ResponseBody
    String demoController2() {
        Register register = new Register();
        register.setRegistername("liang");
        register.setRole(0);
        register.setAge(25);
        register.setAddress("上海");
        register.setStatus(0);
        Boolean flag = demoService.save(register);
        if (flag) {
            return "success";
        } else {
            return "eroor";
        }
    }

    @RequestMapping("demo3")
    public @ResponseBody
    String demoController3() {
        Register register = new Register();
        register.setRegisterno(4);
        register.setRegistername("jin");
        register.setRole(0);
        register.setAge(25);
        register.setAddress("上海23");
        register.setStatus(1);
        Boolean flag = demoService.update(register);
        if (flag) {
            return "success";
        } else {
            return "eroor";
        }
    }

    @RequestMapping("selectone")
    public @ResponseBody
    String demoController4() {
        Register register = new Register();
        register.setRegisterno(4);
        Register register1 = demoService.findOne(register);
        return register1.toString();
    }

    @RequestMapping("delete")
    public @ResponseBody
    String demoController5() {
        Register register = new Register();
        register.setRegisterno(4);
        Boolean flag = demoService.delectRegister(register);
        if (flag) {
            return "success";
        } else {
            return "eroor";
        }
    }

    @RequestMapping("groud")
    public @ResponseBody
    String demoController6() {
        Register register = new Register();
        register.setStatus(0);
        List<Register> list = demoService.findAllGroudByStatus(register);
        return list.toString();
    }


}