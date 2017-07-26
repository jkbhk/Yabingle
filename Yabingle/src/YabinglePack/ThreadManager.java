/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YabinglePack;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Hoshi
 */
public class ThreadManager 
{
    public static long totalSearchTime;
    
    private static Queue<TaskObject> requestQueue = new PriorityQueue<>();
    
    private static ArrayList<YabingleThread> threadList = new ArrayList<>();
    
    public static void Initialize(int noOfThread)
    {
        if(threadList.size() < noOfThread)
        {
            for (int i = 0; i < noOfThread - threadList.size(); i++)
            {
                YabingleThread thread = new YabingleThread();
                thread.start();
                threadList.add(thread);
            }
        }
        else if (threadList.size() > noOfThread)
        {
            threadList.clear();
            for (int i = 0; i < noOfThread - threadList.size(); i++)
            {
                YabingleThread thread = new YabingleThread();
                thread.start();
                threadList.add(thread);
            }
        }
    }
    
    public static void AddTime(long time)
    {
        totalSearchTime += time;
    }
    
    public static void AddRequest(TaskObject requestObject)
    {
        requestQueue.add(requestObject);
    }
   
    public static void TryProcess()
    {
        if(threadList.size() > 0)
        {
            for(YabingleThread thread : threadList)
            {
                if(!thread.GetAvailableStatus())
                {
                    TaskObject ro = requestQueue.poll();
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
