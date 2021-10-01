package YabinglePack;

import java.util.function.Consumer;

/**
 *
 * @author Hoshi
 */
public abstract class TaskObject
{
    private boolean isProcessed = false;
    private int taskPriority;
    
    public TaskObject()
    {
        isProcessed = false;
    }
    
    public void Run() 
    {
        RunProcess(); 
        isProcessed = true;
    }
    

    public boolean IsProcessed() 
    {
        return isProcessed;
    }
    
    public abstract void RunProcess();
    
}
