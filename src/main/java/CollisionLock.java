import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CollisionLock {
    //crossing ways, with probability of collision
    private int NS_WE;
    private int NS_SW;
    private int SN_SW;

    private Lock lockNS_WE = new ReentrantLock();
    private Condition condNS_WE = lockNS_WE.newCondition();
    private Lock lockNS_SW = new ReentrantLock();
    private Condition condNS_SW = lockNS_SW.newCondition();
    private Lock lockSN_SW = new ReentrantLock();
    private Condition condSN_SW = lockSN_SW.newCondition();

    public CollisionLock() {
        NS_WE = 0;
        NS_SW = 0;
        SN_SW = 0;

    }

    public void setNS_WE() {
        lockNS_WE.lock();
        int f = 0;
        if (NS_WE == 1) {
            try {
                condNS_WE.await();
            } catch (InterruptedException e) {

                e.printStackTrace();
            } finally {
                NS_WE = 1;
                lockNS_WE.unlock();
                f = 1;
            }
        }
        if (f != 1) {
            NS_WE = 1;
            lockNS_WE.unlock();
        }
    }

    public void freeNS_WE() {
        lockNS_WE.lock();
        NS_WE = 0;
        condNS_WE.signal();
        lockNS_WE.unlock();
    }

    public void setNS_SW() {
        int f = 0;
        lockNS_SW.lock();
        if (NS_SW == 1) {
            try {
                condNS_SW.await();
            } catch (InterruptedException e) {

                e.printStackTrace();
            } finally {
                NS_SW = 1;
                lockNS_SW.unlock();
                f = 1;
            }
        }
        if (f != 1) {
            NS_SW = 1;
            lockNS_SW.unlock();
        }
    }

    public void freeNS_SW() {
        lockNS_SW.lock();
        NS_SW = 0;
        condNS_SW.signal();
        lockNS_SW.unlock();
    }

    public void setSN_SW() {
        lockSN_SW.lock();
        int f = 0;
        if (SN_SW == 1) {
            try {
                condSN_SW.await();
            } catch (InterruptedException e) {

                e.printStackTrace();
            } finally {
                SN_SW = 1;
                lockSN_SW.unlock();
                f = 1;
            }
        }
        if (f != 1) {
            SN_SW = 1;
            lockSN_SW.unlock();
        }
    }

    public void freeSN_SW() {
        lockSN_SW.lock();
        SN_SW = 0;
        condSN_SW.signal();
        lockSN_SW.unlock();
    }

    public synchronized int getCollision(int num) {
        switch (num) {
            case 0:
                return NS_WE;
            case 1:
                return NS_SW;
            case 2:
                return SN_SW;
        }
        return -1;
    }
}
