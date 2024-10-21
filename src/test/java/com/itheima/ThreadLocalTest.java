package com.itheima;

import org.junit.jupiter.api.Test;

/**
 * @Title: ThreadLocalTest
 * @Author yuier
 * @Package com.itheima
 * @Date 2024/10/21 23:30
 * @description: ThreadLocal 测试类
 */
public class ThreadLocalTest {

    @Test
    public void testThreadLocalSetAndGet() {
        // 获取一个 ThreadLocal 对象
        ThreadLocal tl = new ThreadLocal<>();
        // 开启两个线程
        new Thread(() -> {
            tl.set("萧炎");
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
        }, "线程一").start();

        new Thread(() -> {
            tl.set("药老");
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
        }, "线程二").start();
    }
}
