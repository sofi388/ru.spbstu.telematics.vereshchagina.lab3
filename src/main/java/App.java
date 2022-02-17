import java.util.ArrayList;
import java.util.Random;

public class App {
    private static int numOfThread = 20;

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        int typeNextCar;

        CollisionLock collision = new CollisionLock();

        Traffic tLSW = new Traffic("Direction is is SW", 0, collision);
        int SW = 0;
        Traffic tLNS = new Traffic("Direction is is NS", 0, collision);
        int NS = 0;
        Traffic tLWE = new Traffic("Direction is is WE", 0, collision);
        int WE = 0;

        ArrayList<Thread> threads = new ArrayList<Thread>();
        Thread tempThread;

        while (threads.size() < numOfThread) {
            Thread.sleep(500);
            typeNextCar = random.nextInt(4) + 1;
            switch (typeNextCar) {
                case 1:
                    tempThread = new Thread(new Car(tLSW, SW++));
                    tempThread.start();
                    threads.add(tempThread);
                    break;
                case 2:
                    tempThread = new Thread(new Car(tLNS, NS++));
                    tempThread.start();
                    threads.add(tempThread);
                    break;
                case 3:
                    tempThread = new Thread(new Car(tLWE, WE++));
                    tempThread.start();
                    threads.add(tempThread);
                    break;
            }
        }
        for (Thread th : threads) {
            th.join();
        }
        System.out.println("Finish");
    }
}
