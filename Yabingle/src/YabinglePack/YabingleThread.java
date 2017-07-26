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
    private TaskObject runnableObject;
    private Boolean isProcessing;

    public Boolean GetAvailableStatus() 
    {
        boolean process = (runnableObject != null)? runnableObject.IsProcessed() : true;
        return (!isProcessing && process);
    }
    
    public void SetRunnableObject(TaskObject ro)
    {
        this.runnableObject = ro;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            if(runnableObject != null)
            {
                if(!runnableObject.IsProcessed() && !isProcessing)
                {
                    isProcessing = true;
                    runnableObject.Run();
                }
                else if(runnableObject.IsProcessed() && isProcessing)
                {
                    isProcessing = false;
                    ThreadManager.TryProcess();
                }
            }
        }
    }
}
