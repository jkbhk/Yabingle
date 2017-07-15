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
    private long startTime;
    private long stopTime;
    
    
    @Override
    public void run() 
    {
        StartProcess();
        RunProcess();
        EndProcess();
    }
    
    private void StartProcess()
    {
        startTime = System.currentTimeMillis();
    }

    public abstract void RunProcess();
    
    private void EndProcess()
    {
        stopTime = System.currentTimeMillis();
    }
    
    public long GetProcessTime()
    {
        return (stopTime - startTime);
    }
}
