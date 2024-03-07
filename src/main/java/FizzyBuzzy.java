import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class FizzyBuzzy {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        Semaphore semaphore = new Semaphore(3);

        Thread threadA = new Thread(new Fizz(queue, semaphore));
        Thread threadB = new Thread(new Buzz(queue, semaphore));
        Thread threadC = new Thread(new FizzBuzz(queue, semaphore));
        Thread threadD = new Thread(new Number(queue, semaphore));

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}

class Fizz implements Runnable {
    private final BlockingQueue<String> queue;
    private final Semaphore semaphore;

    public Fizz(BlockingQueue<String> queue, Semaphore semaphore) {
        this.queue = queue;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            for (int i = 1; i <= 100; i++) {
                if (i % 3 == 0 && i % 5 != 0) {
                    queue.put("fizz");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }
}

class Buzz implements Runnable {
    private final BlockingQueue<String> queue;
    private final Semaphore semaphore;

    public Buzz(BlockingQueue<String> queue, Semaphore semaphore) {
        this.queue = queue;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            for (int i = 1; i <= 100; i++) {
                if (i % 5 == 0 && i % 3 != 0) {
                    queue.put("buzz");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }
}

class FizzBuzz implements Runnable {
    private final BlockingQueue<String> queue;
    private final Semaphore semaphore;

    public FizzBuzz(BlockingQueue<String> queue, Semaphore semaphore) {
        this.queue = queue;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            for (int i = 1; i <= 100; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    queue.put("fizzbuzz");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }
}

class Number implements Runnable {
    private final BlockingQueue<String> queue;
    private final Semaphore semaphore;

    public Number(BlockingQueue<String> queue, Semaphore semaphore) {
        this.queue = queue;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire(3);
            for (int i = 1; i <= 100; i++) {
                String result = queue.take();
                if (result != null) {
                    System.out.println(result);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release(3);
        }
    }
}