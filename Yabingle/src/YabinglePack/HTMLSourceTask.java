/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YabinglePack;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 *
 * @author Hoshi
 */
public class HTMLSourceTask extends TaskObject
{
    private final Consumer<StringBuilder> callback;
    private String url;
    
    public HTMLSourceTask(Consumer<StringBuilder> callback, String url) 
    {
        this.callback = callback;
        this.url = url;
    }
    
    @Override
    public void RunProcess() 
    {
        StringBuilder pageSource = HTMLReader.readPage(url);
        System.out.println(GetProcessedTime());
        callback.accept(pageSource);
    }
   
}
