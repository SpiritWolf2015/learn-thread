package chapter04;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 6-18
 */
public class ConnectionPoolTest {
    private static final ConnectionPool pool  = new ConnectionPool(10);
    // ��֤����ConnectionRunner�ܹ�ͬʱ��ʼ
    private static final CountDownLatch start = new CountDownLatch(1);
    // main�߳̽���ȴ�����ConnectionRunner��������ܼ���ִ��
    private static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        // �߳������������߳��������й۲�
        final int threadCount = 50;
        end = new CountDownLatch(threadCount);

        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();

        final int count = 20;
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnetionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();

        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection:  " + got);
        System.out.println("not got connection " + notGot);
    }

    private static class ConnetionRunner implements Runnable {
        private int count;
        private final AtomicInteger got;
        private final AtomicInteger notGot;

        public ConnetionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (Exception ex) {
                // NONE OP
            }
            while (count > 0) {
                try {
                    // ���̳߳��л�ȡ���ӣ����1000ms���޷���ȡ�������᷵��null
                    // �ֱ�ͳ�����ӻ�ȡ������got��δ��ȡ��������notGot
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception ex) {
                    // NONE OP
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
