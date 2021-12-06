public class Main {
    public static void main(String[] args) throws InterruptedException {
        CallCenter callCenter = new CallCenter();

        Thread station = new Thread(callCenter::call);

        station.start();

        Thread operator1 = new Thread(callCenter::operatorWork, "Оператор 1");
        Thread operator2 = new Thread(callCenter::operatorWork, "Оператор 2");
        Thread operator3 = new Thread(callCenter::operatorWork, "Оператор 3");

        operator1.start();
        operator2.start();
        operator3.start();
        station.join();
        operator1.join();
        operator2.join();
        operator3.join();
    }
}
