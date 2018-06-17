package com.jin.jooq.demoTest;

import org.springframework.stereotype.Service;

/**
 * Created by 金梁 on 2018/6/17.
 */
@Service("Collection4Array")
public class Collection4Array implements TestInterface{
    @Override
    public String showMsg() {
        System.out.println("Collection4Array showMsg");
        return null;
    }
}
