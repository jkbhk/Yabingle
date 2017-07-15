/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YabinglePack;

/**
 *
 * @author Hoshi
 */
public abstract class RunnableObject implements Runnable
{
    private IProcessListener processListener;
    
    private long startTime;
    private long stopTime;
    
    public RunnableObject(IProcessListener processListener)
    {
        this.processListener = processListener;
    }
    
    @Override
    public void run() 
    {
        startTime = System.currentTimeMillis();
        RunProcess();
        stopTime = System.currentTimeMillis();
        processListener.Complete(this);
    }
    
    public abstract void RunProcess();
    
    public long GetProcessTime()
    {
        return (stopTime - startTime);
    }
}
