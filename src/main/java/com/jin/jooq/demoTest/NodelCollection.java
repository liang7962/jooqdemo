package com.jin.jooq.demoTest;

import org.springframework.stereotype.Service;

/**
 * Created by 金梁 on 2018/6/17.
 */
@Service("NodelCollection")
public class NodelCollection implements TestInterface{
    @Override
    public String showMsg() {
        System.out.println("NodelCollection showMsg");
        return null;
    }

    @Override
    public void delOneObj( ){
        System.out.println("none del");
    }
}
