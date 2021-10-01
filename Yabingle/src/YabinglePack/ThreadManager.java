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
        StopProcesses();

        
        for (int i = 0; i < noOfThread; i++)
        {
            YabingleThread thread = new YabingleThread();
            thread.start();
            threadList.add(thread);
        }
    }
        
    public static void AddRequest(TaskObject requestObject)
    {
        taskQueue.add(requestObject);
    }
   
    public static void StopProcesses()
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
            threadList.clear();
        }
        
    }
    
    public static TaskObject GetNextProcess()
    {
        TaskObject ro = taskQueue.poll();
                
        return ro;
    }
}
