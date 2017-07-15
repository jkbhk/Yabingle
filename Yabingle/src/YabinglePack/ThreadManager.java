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
    private static Queue<RunnableObject> requestQueue = new PriorityQueue<>();
    
    private static ArrayList<YabingleThread> threadList = new ArrayList<>();
    
    public static void Initialize(int noOfThread)
    {
        if(threadList.size() == noOfThread)
        {
            return;
        }
        
        if(threadList.size() < noOfThread)
        {
            for (int i = 0; i < noOfThread - threadList.size(); i++)
            {
                threadList.add(new YabingleThread());
            }
        }
        threadList.clear();
        
    }
    
    public static void AddRequest(RunnableObject requestObject)
    {
        requestQueue.add(requestObject);
    }
   
    public static void TryProcess()
    {
        if(threadList.size() > 0)
        {
            
        }
    }
}
