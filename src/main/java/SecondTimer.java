public class SecondTimer {
    public static void main(String[] args) {
        Thread timeThread = new Thread(new TimeDisplay());
        timeThread.start();
        Thread messageThread = new Thread(new MessageDisplay());
        messageThread.start();
    }
}

class TimeDisplay implements Runnable {
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (true) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Elapsed time: " + (elapsedTime / 1000) + " second" );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MessageDisplay implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("It's been 5 seconds");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}