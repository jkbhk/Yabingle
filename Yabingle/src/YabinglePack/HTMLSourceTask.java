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
    private SearchEngineType engineType;

    public StringBuilder getPageSource() {
        return pageSource;
    }
    
    public HTMLSourceTask(Consumer<HTMLSourceTask> callback, String url) 
    {
        this.callback = callback;
        this.url = url;
        engineType = SearchEngineType.NO_PREFERENCE;
        
    }

    public HTMLSourceTask(Consumer<HTMLSourceTask> callback, String url, SearchEngineType engineType) 
    {
        this.callback = callback;
        this.url = url;
        this.engineType = engineType;
    }
    
    public SearchEngineType getEngineType() {
        return engineType;
    }
    
    
    
    @Override
    public void RunProcess() 
    {
        pageSource = HTMLReader.readPage(url);
        callback.accept(this);
    }
   
}
