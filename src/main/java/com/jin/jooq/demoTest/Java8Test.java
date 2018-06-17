package com.jin.jooq.demoTest;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

public class Java8Test {

    /**
     * @author: jinliang
     * @create: 2018/6/15 11:14
     * @desc: lambda和jdk1.8基础遍历方式
     * @param
     **/
    @Test
    public void test1(){
        IntBinaryOperator intBinaryOperator = (int x, int y) -> x + y;
        final int i = intBinaryOperator.applyAsInt(5, 6);
        System.out.print(i);

        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);

        // 以前的循环方式
        for (String player : players) {
            System.out.print(player + "; ");
        }

        // 使用 lambda 表达式以及函数操作(functional operation)
        players.forEach((player) -> System.out.print(player + "; "));

        // 在 Java 8 中使用双冒号操作符(double colon operator)
        players.forEach(System.out::println);
    }

    /**
     * @author: jinliang
     * @create: 2018/6/15 11:13
     * @desc: 线程测试
     * @param
     **/
    @Test
    public void test2(){
        DemoRunnable demoRunnable=new DemoRunnable();
        demoRunnable.run();
    }

    /**
     * @author: jinliang
     * @create: 2018/6/15 11:14
     * @desc: 匿名类排序
     * @param
     **/
    @Test
    public void test3(){
        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};

        // 1.1 使用匿名内部类根据 name 排序 players
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });
        List<String> list =  Arrays.asList(players);
        list.forEach((player) -> System.out.print(player + "; "));
    }

    /**
     * @author: jinliang
     * @create: 2018/6/15 11:15
     * @desc: 使用 lambda expression 排序 players
     * @param
     **/
    @Test
    public void test4(){
        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};

        // 1.2 使用 lambda expression 排序 players
        Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
        Arrays.sort(players, sortByName);

        List<String> list =  Arrays.asList(players);
        list.forEach((player) -> System.out.print(player + "; "));
    }


    /**
     * @author: jinliang
     * @create: 2018/6/15 11:15
     * @desc: 也可以采用如下形式:
     * @param
     **/
    @Test
    public void test5(){
        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};

        // 1.3 也可以采用如下形式:
        Arrays.sort(players, (String s1, String s2) -> (s1.compareTo(s2)));

        List<String> list =  Arrays.asList(players);
        list.forEach((player) -> System.out.print(player + "; "));


    }

    @Test
    public void test6(){
        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};

        List<String> list =  Arrays.asList(players);
        Collections.sort(list);
        list.forEach((player) -> System.out.print(player + "; "));


    }

    @Test
    public void test7(){
        List<Person> javaProgrammers =this.personListFactory();
        List<Person> phpProgrammers = this.personListFactory2();

        System.out.println("所有程序员的姓名:");
        javaProgrammers.forEach((p)->System.out.printf("%s %s;",p.getFirstName(),p.getLastName()));
        phpProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        System.out.println("给程序员加薪前 :");
        javaProgrammers.forEach((p)->System.out.printf("%s;",p.getSalary()));
        phpProgrammers.forEach((p) -> System.out.printf("%s;",p.getSalary()));


        System.out.println("给程序员加薪 5% :");
        Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary());
        javaProgrammers.forEach(giveRaise);
        phpProgrammers.forEach(giveRaise);

        javaProgrammers.forEach((p)->System.out.printf("%s;",p.getSalary()));
        phpProgrammers.forEach((p) -> System.out.printf("%s;",p.getSalary()));

    }


    /*定义过滤器,然后重用它们来执行其他操作:*/
    @Test
    public void test8(){
        List<Person> javaProgrammers =this.personListFactory();
        List<Person> phpProgrammers = this.personListFactory2();
        System.out.println("下面是月薪超过 $1,400 的PHP程序员:");
        phpProgrammers.stream().filter((p)->(p.getSalary()>1400)).forEach((p)->System.out.printf("%s %s;",p.getFirstName(),p.getSalary()));
        Predicate<Person> ageFilter=(p)->(p.getAge()>25);
        Predicate<Person> salaryFilter = (p) -> (p.getSalary() > 1400);
        Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));
        System.out.println("下面是年龄大于 24岁且月薪在$1,400以上的女PHP程序员:");
        phpProgrammers.stream()
                .filter(ageFilter)
                .filter(salaryFilter)
                .filter(genderFilter)
                .forEach((p)->System.out.printf("%s %s;",p.getFirstName(),p.getSalary()));
        //重用filters
        System.out.println("年龄大于 24岁的女性 Java programmers:");
        javaProgrammers.stream()
                .filter(ageFilter)
                .filter(genderFilter)
                .forEach((p)->System.out.printf("%s %s;",p.getFirstName(),p.getSalary()));
    }

    /*使用limit方法,可以限制结果集的个数:*/
    @Test
    public void test9(){
        List<Person> javaProgrammers =this.personListFactory();
        Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));

        System.out.println("最前面的3个 Java programmers:");
        javaProgrammers.stream().limit(3).forEach((p)->System.out.printf("%s %s;",p.getFirstName(),p.getSalary()));

        System.out.println("最前面的3个女性 Java programmers:");
        javaProgrammers.stream().limit(3).filter(genderFilter).forEach((p)->System.out.printf("%s %s;",p.getFirstName(),p.getSalary()));
    }


    /*排序呢? 我们在stream中能处理吗? 答案是肯定的。 在下面的例子中,我们将根据名字和薪水排序Java程序员,放到一个list中,然后显示列表*/
    @Test
    public void test10(){
        List<Person> javaProgrammers =this.personListFactory();
        System.out.println("根据 name 排序,并显示前5个 Java programmers:");
        List<Person> collect = javaProgrammers
                .stream()
                .sorted((p, p2) -> (p.getFirstName().compareTo((p2.getFirstName()))))
                .limit(5)
                .collect(toList());
        collect.forEach((p)->System.out.printf("%s %s;",p.getFirstName(),p.getSalary()));
    }

    /*如果我们只对最低和最高的薪水感兴趣,比排序后选择第一个/最后一个 更快的是min和max方法:*/
    @Test
    public void test11(){
        List<Person> javaProgrammers =this.personListFactory();
        List<Person> phpProgrammers = this.personListFactory2();
        System.out.println("工资最低的 Java programmer:");
        Person person  = javaProgrammers
                .stream()
                .min((p1,p2)->(p1.getSalary()-p2.getSalary()))
                .get();
        System.out.printf("Name: %s %s; Salary: %,d",person.getFirstName(),person.getLastName(),person.getSalary());

        System.out.println("工资最高的 Java programmer:");
        Person person1 = javaProgrammers
                .stream()
                .max((p1, p2) -> (p1.getSalary() - p2.getSalary()))
                .get();
        System.out.printf("Name: %s %s; Salary: %,d",person1.getFirstName(),person1.getLastName(),person1.getSalary());
    }

    /*上面的例子中我们已经看到 collect 方法是如何工作的。 结合 map 方法,我们可以使用 collect 方法来将我们的结果集放到一个字符串,一个 Set 或一个TreeSet中*/
    @Test
    public void test12(){
        List<Person> javaProgrammers =this.personListFactory();
        List<Person> phpProgrammers = this.personListFactory2();
        System.out.println("将 PHP programmers 的 first name 拼接成字符串:");
        String phpDevelopers = phpProgrammers
                .stream()
                .map(Person::getFirstName)
                .collect(joining(" ; ")); // 在进一步的操作中可以作为标记(token)
        System.out.println("将 Java programmers 的 first name 存放到 Set:");

        Set<String> javaDevFirstName2 = javaProgrammers
                .stream()
                .map(Person::getFirstName)
                .collect(toSet());
        System.out.println("将 Java programmers 的 first name 存放到 TreeSet:");

        TreeSet<String> javaDevLastName = javaProgrammers
                .stream()
                .map(Person::getLastName)
                .collect(toCollection(TreeSet::new));
    }

    /*Streams 还可以是并行的(parallel)*/
    @Test
    public void test13(){
        List<Person> javaProgrammers =this.personListFactory();
        System.out.println("计算付给 Java programmers 的所有money:");
        int totalSalary = javaProgrammers
                .parallelStream()
                .mapToInt(p -> p.getSalary())
                .sum();
        System.out.print("totalSalary>>>>>>>>>>>>>>>>>>"+totalSalary);
    }

    /*我们可以使用summaryStatistics方法获得stream 中元素的各种汇总数据。 接下来,我们可以访问这些方法,比如getMax, getMin, getSum或getAverage*/
    @Test
    public void test14(){
        //计算 count, min, max, sum, and average for numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntSummaryStatistics stats = numbers
                .stream()
                .mapToInt((x) -> x)
                .summaryStatistics();

        System.out.println("List中最大的数字 : " + stats.getMax());
        System.out.println("List中最小的数字 : " + stats.getMin());
        System.out.println("所有数字的总和   : " + stats.getSum());
        System.out.println("所有数字的平均值 : " + stats.getAverage());
    }

    private List<Person> personListFactory(){
        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };
        return javaProgrammers;
    }


    private List<Person> personListFactory2(){
        List<Person> phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };
        return phpProgrammers;
    }
}