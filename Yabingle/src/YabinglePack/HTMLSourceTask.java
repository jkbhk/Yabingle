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
    private StringBuilder pageSource;
    private SeachEngineType engineType;
    
    public HTMLSourceTask(Consumer<StringBuilder> callback, String url) 
    {
        this.callback = callback;
        this.url = url;
        engineType = SeachEngineType.NO_PREFERENCE;
        
    }

    public SeachEngineType getEngineType() {
        return engineType;
    }
    
    public HTMLSourceTask(Consumer<StringBuilder> callback, String url, SeachEngineType engineType) 
    {
        this.callback = callback;
        this.url = url;
        this.engineType = engineType;
    }
    
    @Override
    public void RunProcess() 
    {
        pageSource = HTMLReader.readPage(url);
        callback.accept(pageSource);
    }
   
}
