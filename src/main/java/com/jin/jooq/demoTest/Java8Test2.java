package com.jin.jooq.demoTest;

import com.jin.jooq.interfacedemo.PersonFactory;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by 金梁 on 2018/6/17.
 */
@Controller
public class Java8Test2 {

    @Resource(name = "NodelCollection")
    private TestInterface testInterface;


    static int outerStaticNum;
    int outerNum;

//    @RequestMapping(name = "interface84",method = RequestMethod.GET)
    @RequestMapping( value = "interface84",method =RequestMethod.GET )
    @ResponseBody
    public String test1(){
        System.out.println(testInterface);
        testInterface.addOneObj();
        testInterface.delOneObj();
        testInterface.showMsg();
        return "success";
    }

    /*函数式接口
    Lambda表达式是如何在java的类型系统中表示的呢？每一个lambda表达式都对应一个类型，通常是接口类型。
    而“函数式接口”是指仅仅只包含一个抽象方法的接口，每一个该类型的lambda表达式都会被匹配到这个抽象方法。
    因为 默认方法 不算抽象方法，所以你也可以给你的函数式接口添加默认方法
     */
    @FunctionalInterface
    interface Converter<H, Z> {//H,Z只是个形参，没有任何意义
        Z convert(H from);
    }
    @Test
    public void test2(){
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123
    }

    /*四、方法与构造函数引用*/
    @Test
    public void test3(){
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123

    }

    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    @Test
    public void test4(){
       /* Converter<String, String>  converter = something::startsWith;
        String converted = converter.convert("Java");
        System.out.println(converted);    // "J"*/
        final int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);
             // 3
        System.out.println(stringConverter.convert(2));

    }

    /*访问局部变量*/
    @Test
    public void test5(){
        /*这里的num必须不可被后面的代码修改（即隐性的具有final的语义）*/
        /*在lambda表达式中访问外层的局部变量*/
        final int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);
        // 3
        System.out.println(stringConverter.convert(2));
        /*和匿名对象不同的是，这里的变量num可以不用声明为final，该代码同样正确*/
        int num2 = 1;
        Converter<Integer, String> stringConverter2 =
                (from) -> String.valueOf(from + num2);
        System.out.println(stringConverter.convert(2));

        /*这里的num必须不可被后面的代码修改（即隐性的具有final的语义）*/
        int num3=1;
        Converter<Integer,String> stringConverter3=(from -> String.valueOf(from+num3));
        System.out.println(stringConverter.convert(2));


    }
    /*访问对象字段与静态变量*/

    void testScopes() {
        /*和本地变量不同的是，lambda内部对于实例的字段以及静态变量是即可读又可写。该行为和匿名对象是一致的：*/
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from+outerNum);
        };
        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from+outerStaticNum);
        };
        System.out.println(stringConverter1.convert(58));
        System.out.println(stringConverter2.convert(58));
    }
    @Test
    public void test6(){
        testScopes();
    }
    /*访问接口的默认方法*/
    @Test
    public void test7(){
        /*Predicate 接口只有一个参数，返回boolean类型。该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与，或，非）*/
        Predicate<String> predicate=(s)->s.length()>0;
        System.out.println(predicate.test("588"));
        System.out.println(predicate.negate().test("58"));
        Predicate<Boolean> nonNull = Objects::nonNull;
        System.out.println(nonNull.test(false));
        Predicate<Boolean> isNull = Objects::isNull;
        System.out.println(isNull.test(true));
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
        System.out.println(isNotEmpty.test("58"));
    }
}
