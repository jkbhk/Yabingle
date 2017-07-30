/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YabinglePack;

import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hoshi
 */
public class ThreadManager 
{
    private static final Queue<TaskObject> taskQueue = new ArrayDeque<>();
    
    private static final ArrayList<YabingleThread> threadList = new ArrayList<>();
    
    public static void InitializeSearch(int noOfThread)
    {
        if(taskQueue.size() > 0)
        {
            taskQueue.clear();
        }
        
        if(threadList.size() > 0)
        {
            for (YabingleThread eachThread : threadList)
            {
                eachThread.ResetThread();
            }
        }
        
        if(threadList.size() < noOfThread)
        {
            int originalSize = threadList.size();
            for (int i = 0; i < noOfThread - originalSize; i++)
            {
                YabingleThread thread = new YabingleThread();
                thread.start();
                threadList.add(thread);
            }
        }
        else if (threadList.size() > noOfThread)
        {
            threadList.clear();
            for (int i = 0; i < noOfThread; i++)
            {
                YabingleThread thread = new YabingleThread();
                thread.start();
                threadList.add(thread);
            }
        }
    }
        
    public static void AddRequest(TaskObject requestObject)
    {
        taskQueue.add(requestObject);
        TryProcess();
    }
   
    public static void TryProcess()
    {
        if(threadList.size() > 0)
        {
            for(YabingleThread thread : threadList)
            {
                if(thread != null && thread.GetAvailableStatus())
                {
                    TaskObject ro = taskQueue.poll();
                    if(ro != null)
                    {                    
                        thread.SetRunnableObject(ro);
                        break;
                    }
                }
            }
        }
    }
}
