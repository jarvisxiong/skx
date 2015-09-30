package com.skx.dao;

import com.skx.mybatis.model.GenericService;
import com.skx.mybatis.model.pojo.User;
import com.skx.mybatis.model.UserService;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by vj on 2015/09/20.
 */
public class TestUserMapper {
    public static UserService userServie;
    private static Integer count=0;
    Lock lock = new ReentrantLock();// Ëø
    @BeforeClass
    public static void setup() {
        userServie = new UserService();

    }

    @AfterClass
    public static void teardown() {
        userServie = null;
    }

    @Test
    public void testFindAllUsers() {
        List<User> users = userServie.findAll();
        Assert.assertNotNull(users);
        users.forEach((User u) -> System.out.println(u.getLogin()));
    }

    @Test
    public void testCreateUser() {
        User student = new User();
        int id = 3;
        String pwd = "aa";
        student.setLogin("student_" + id);
        student.setPassword(pwd);
        student.setCreatedDate(new Date());
        student.setUpdatedDate(new Date());
        userServie.createUser(student);
        List<User> newStudent = userServie.findAll();
        User u = newStudent.get(0);
        u.setUpdatedDate(new Date());
        userServie.update(u);
        Assert.assertNotNull(newStudent);
    }

    @Test
    public void testCreateUserSimultaneously() {
       // new UserService().test();
        Runnable task = () -> {

                int i = 5;
                UserService uss=new UserService();
                while (i > 0) {
                    count++;
                    System.out.println(String.format("%s--%s", count, Thread.currentThread().getName()));
                    i--;
                    User student = new User();
                    int id = 3;
                    String pwd = Thread.currentThread().getName();
                    student.setLogin("student_" + id);
                    student.setPassword(pwd);
                    student.setCreatedDate(new Date());
                    student.setUpdatedDate(new Date());

                    uss.createUser(student);
                }

        };

        //   task.run();

//        Thread thread = new Thread(task);
//        thread.start();
//
//
//        Thread thread2 = new Thread(task);
//        thread2.start();
//
//        Thread thread3 = new Thread(task);
//        thread3.start();
//        Thread thread4 = new Thread(task);
//        thread4.start();

        int ii=100;
        while(ii>0){
            ii--;
            Thread thread5 = new Thread(task);
            thread5.start();

        }
        //Thread thread1 = new Thread(task);
        // thread1.start();
        try {
            Thread.sleep(100000L);
        } catch (InterruptedException ex) {

        }
    }

    @Test
    public void testGenericService() {
        GenericService<User> gs = new GenericService<User>();
        try {
            gs.findAll();
        } catch (ClassNotFoundException ex) {
            Assert.fail(ex.getMessage());
        }
    }
}
