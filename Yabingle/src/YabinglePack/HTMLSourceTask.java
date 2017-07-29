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
    private final Consumer<HTMLSourceTask> callback;
    private String url;
    private StringBuilder pageSource;
    private YabingleManager.EngineReference engine;

    
    public HTMLSourceTask(Consumer<HTMLSourceTask> callback, String url) 
    {
        this.callback = callback;
        this.url = url;
        engine = null;
        
    }

    public HTMLSourceTask(Consumer<HTMLSourceTask> callback, String url, YabingleManager.EngineReference engine) 
    {
        this.callback = callback;
        this.url = url;
        this.engine = engine;
    }
    
    public YabingleManager.EngineReference getEngine()
    {
        return engine;
    }

    public String getUrl() {
        return url;
    }
    
    public StringBuilder getPageSource() 
    {
        return pageSource;
    }
    
    
    
    @Override
    public void RunProcess() 
    {
        pageSource = HTMLReader.readPage(url);
        callback.accept(this);
    }
   
}
