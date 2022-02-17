public class Traffic {

    private String route;
    private int nextCar;
    private CollisionLock collision;

    public Traffic(String route, int nextCar, CollisionLock collision) {
        this.route = route;
        this.nextCar = nextCar;
        this.collision = collision;
    }

    public synchronized void start(int id) {
        while (id != nextCar) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (route == "SW") {
            runSW(id);
        }
        if (route == "NS") {
            runNS(id);
        }
        if (route == "WE") {
            runWE(id);
        }
        nextCar++;
        notifyAll();

    }

    public String getRoute() {
        return route;
    }

    public synchronized void runSW(int id) {//SN
        while (collision.getCollision(3) == 1) {

        }
        collision.setNS_SW();
        collision.freeNS_SW();
        collision.setSN_SW();
        collision.freeSN_SW();

    }

    public synchronized void runNS(int id) {
        while (collision.getCollision(4) == 1) {
        }
        collision.setNS_WE();
        collision.freeNS_WE();
        collision.setNS_SW();
        collision.freeNS_SW();
    }

    public synchronized void runWE(int id) {
        collision.setNS_WE();
        collision.freeNS_WE();
    }


}

