package concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class Foo_1114 {

    private AtomicInteger firstJobDone = new AtomicInteger(0);
    private AtomicInteger secondJobDone = new AtomicInteger(0);

    public Foo_1114() {}


    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        // 1 完成，数量+1
        firstJobDone.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // 等待 1 完成
        while (firstJobDone.get() != 1) {}
        printSecond.run();
        // 2 完成，数量+1
        secondJobDone.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // 等待 2 完成
        while (secondJobDone.get() != 1) {}
        printThird.run();
    }
}

class Foo {
    private CountDownLatch first = new CountDownLatch(1);
    private CountDownLatch second = new CountDownLatch(1);
    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        // 1 完成
        first.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // 等待 1 完成
        first.await();
        printSecond.run();
        // 2 完成
        second.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // 等待 2 完成
        second.await();
        printThird.run();
    }
}

//信号量
class Foo2 {
    private Semaphore semaphore1 = new Semaphore(0);
    private Semaphore semaphore2 = new Semaphore(0);
    public Foo2() {}

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        semaphore1.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        semaphore1.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        semaphore2.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        semaphore2.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        semaphore2.release();
    }
}
