package YabinglePack;

import java.util.function.Consumer;

/**
 *
 * @author Hoshi
 */
public abstract class TaskObject
{
    private boolean isProcessed;
    private long startTime;
    private long stopTime;
    
    public TaskObject()
    {
        isProcessed = false;
    }
    
    public void Run() 
    {
        startTime = System.currentTimeMillis();
        RunProcess();
        stopTime = System.currentTimeMillis();
        isProcessed = true;
    }

    public boolean IsProcessed() 
    {
        return isProcessed;
    }
    
    public abstract void RunProcess();
}
