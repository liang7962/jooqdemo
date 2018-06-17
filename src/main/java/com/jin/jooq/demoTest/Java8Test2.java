package com.jin.jooq.demoTest;

import com.jin.jooq.tables.tables.pojos.Register;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by 金梁 on 2018/6/17.
 */
@Component
public class Java8Test2 {
    @Resource(name = "NodelCollection")
    private TestInterface testInterface;

    @Test
    public void test1(){
        //testInterface.addOneObj();
        testInterface.delOneObj();
    }
}
