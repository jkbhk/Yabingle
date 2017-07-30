/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YabinglePack;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoshi
 */
public class YabingleThread extends Thread
{
    private TaskObject taskObject;
    private boolean isProcessing = false;

    public boolean GetAvailableStatus() 
    {
        boolean isAvailable = (taskObject != null)? taskObject.IsProcessed() : true;
        return (!isProcessing && isAvailable);
    }
    
    public void SetRunnableObject(TaskObject ro)
    {
        this.taskObject = ro;
        isProcessing = true;
    }
    
    public void ResetThread()
    {
        taskObject = null;
        isProcessing = false;
    }
        
    @Override
    public void run()
    {
        while(true)
        {
            try 
            {
                Thread.sleep(10);            
                
                if(taskObject != null)
                {
                    if(!taskObject.IsProcessed() && isProcessing)
                    {
                        taskObject.Run();
                    }
                    else if(taskObject.IsProcessed() && isProcessing)
                    {
                        isProcessing = false;
                        ThreadManager.TryProcess();
                    }
                }
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(YabingleThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }
    }
}
