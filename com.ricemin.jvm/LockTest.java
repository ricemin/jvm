import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: jvm
 * @Package: PACKAGE_NAME
 * @ClassName: LockTest
 * @Description: java类作用描述
 * @Author: ricemin
 * @CreateDate: 2020-05-03 22:41
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-05-03 22:41
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LockTest {
    /**
     * @return
     * @method
     * @description 两个进程共享一个公共的固定大小的缓冲区（BoundedBuffer）。其中一个是生产者，用于把消息放入缓冲区；另外一个是消费者，用于从缓冲区中取出消息。
     * 问题出现在当缓冲区已经满了，而此时生产者还想向其中放入一个新的数据项的情形，其解决方法是让生产者此时进行休眠，等待消费者从缓冲区中取走了一个或者多个数据后再去唤醒它。
     * 同样地，当缓冲区已经空了，而消费者还想去取消息，此时也可以让消费者进行休眠，等待生产者放入一个或者多个数据时再唤醒它。
     * @date: 2020-05-03 22:43
     * @author: ricemin
     * No such property: code for class: Script1
     */
    public static void main(String[] args) {
        final BoundedBuffer boundedBuffer = new BoundedBuffer();
        Thread producer = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 100; i++) {
                            try {
                                boundedBuffer.put(i);
                                System.out.println("put:"+i);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

        );
        Thread consumer = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 100; i++) {
                            try {
                                boundedBuffer.take();
                                System.out.println("take:"+i);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }

        );
        producer.start();
        consumer.start();

    }

    static class BoundedBuffer {

        private int count = 0;

        private int size = 100;

        private ReentrantLock reentrantLock = new ReentrantLock();

        private Condition putCondition = reentrantLock.newCondition();

        private Condition takeCondition = reentrantLock.newCondition();

        private BlockingDeque<Integer> integerList = new LinkedBlockingDeque<>(size);

        public void put(int i) {
            reentrantLock.lock();
            try {
                if (count == size) {
                    try {
                        putCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ++count;
                integerList.add(i);
                takeCondition.signal();
            } finally {
                reentrantLock.unlock();
            }
        }

        public void take() {
            reentrantLock.lock();
            try {
                if(count==0){
                    takeCondition.await();
                }
                --count;
                integerList.take();
                putCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
        }
    }
}
