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
    private boolean stopThread;
    
    public void SetRunnableObject(TaskObject ro)
    {
        this.taskObject = ro;
        stopThread = false;
    }
    
    public void ResetThread()
    {
        taskObject = null;
        stopThread = true;
    }
        
    @Override
    public void run()
    {
        while(!stopThread)
        {
            try 
            {
                Thread.sleep(10);     
                
                if(taskObject == null || taskObject.IsProcessed())
                {
                    taskObject = ThreadManager.GetNextProcess();
                }
           
                if(taskObject != null && !taskObject.IsProcessed())
                {
                    taskObject.Run();
                }
            }
            catch(Exception e)
            {
                
            }
        }
    }
}
