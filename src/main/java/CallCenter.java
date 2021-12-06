import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenter {
    final int LIMIT_CALL = 60;
    final int HANDLE_CALL = 1000;
    final int CALL_TIME = 5000;
    final int DELAY_OPERATOR = 5000;

    static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
    
    public void call() {
        for (int i = 1; i < LIMIT_CALL + 1; i++) {
            System.out.println("Поступил звонок " + i);
            queue.add("Звонок " + i);
            try {
                Thread.sleep(HANDLE_CALL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void operatorWork() {
        String queuePoll;
        while (true) {
            queuePoll = queue.poll();
            if (queuePoll != null) {
                System.out.println(Thread.currentThread().getName() + " обработал: " + queuePoll);
                try {
                    Thread.sleep(CALL_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (queue.size() == 0) {
                try {
                    Thread.sleep(DELAY_OPERATOR);
                    if (queue.size() == 0) {
                        System.out.println(Thread.currentThread().getName() + " ушел домой");
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
