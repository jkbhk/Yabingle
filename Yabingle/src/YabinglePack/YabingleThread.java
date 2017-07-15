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
public class YabingleThread extends Thread
{
    private RunnableObject runnableObject;
    private Boolean isProcessing;

    public Boolean GetIsProcessing() 
    {
        return isProcessing;
    }
    
    public void SetRunnableObject(RunnableObject ro)
    {
        this.runnableObject = ro;
    }
    
    @Override
    public void run()
    {
        isProcessing = true;
        runnableObject.run();
        isProcessing = false;
        ThreadManager.TryProcess();
    }
}
