package com.jin.jooq.controller;

import com.jin.jooq.application.AsyncTask;
import com.jin.jooq.exception.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 *  * <br>
 * 标题: 调用异步方法的controller<br>
 * 描述: 异步任务<br>
 * @author: jinliang
 * @create: 2018/7/2 13:54
 * @desc: 
 * @param
 **/
@RestController
@RequestMapping("/tasks")
public class DoTaskController {

    @Autowired
    private AsyncTask asyncTask;

    @RequestMapping("/asyncTask")
    public JsonResult asyncTask() throws Exception {
        long start = System.currentTimeMillis();
        Future<Boolean> a = asyncTask.doTask11();
        Future<Boolean> b = asyncTask.doTask22();
        Future<Boolean> c = asyncTask.doTask33();
        while (!a.isDone() || !b.isDone() || !c.isDone()) {
            if (a.isDone() && b.isDone() && c.isDone()) {
                break;
            }
        }
        long end = System.currentTimeMillis();
        String times = "任务全部完成，总耗时：" + (end - start) + "毫秒";
        System.out.println(times);
        return JsonResult.ok(times);
    }
}
