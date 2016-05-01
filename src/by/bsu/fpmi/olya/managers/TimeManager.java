package by.bsu.fpmi.olya.managers;

/**
 * Created by Lenovo on 24.04.2016.
 */
public class TimeManager {

    private final long FACTOR = 1000000;

    private long delayNanoSec;
    private long passedTimeNanoSec;

    public TimeManager(long delayMilliSec) {
        this.delayNanoSec = convertMilliToNano(delayMilliSec);
        this.passedTimeNanoSec = 0;
    }

    public boolean hasTimePassed(long passedNanoSec){
        passedTimeNanoSec += passedNanoSec;
        if (passedTimeNanoSec > delayNanoSec){
            passedTimeNanoSec = 0;
            return true;
        }
        return false;
    }

    public long convertMilliToNano(long timeMilliSec){
        return timeMilliSec * FACTOR;
    }

}
