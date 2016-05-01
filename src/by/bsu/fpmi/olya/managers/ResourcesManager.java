package by.bsu.fpmi.olya.managers;

/**
 * Created by Lenovo on 23.04.2016.
 */
public abstract class ResourcesManager {
    private int refCount = 0;

    public void addReference(){
        refCount++;
    }

    public boolean removeReference(){
        refCount--;
        return refCount == 0;
    }
}
