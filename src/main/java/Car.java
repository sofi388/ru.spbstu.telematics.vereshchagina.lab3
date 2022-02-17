public class Car implements Runnable{
    private Traffic tL;
    private int id;

    Car(Traffic tL, int id) {
        this.tL = tL;
        this.id = id;
    }

    public void run() {
        long sleepTime = 1000;
        System.out.println(tL.getRoute() + " Car number " + (id+1) + " waiting");
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tL.start(id);
        System.out.println(tL.getRoute() + " Car number " + (id+1)
                + " crossing");

    }
}
