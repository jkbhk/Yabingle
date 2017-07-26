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
public class HTMLSourceRetriver extends TaskObject
{
    private final Consumer<String> callback;
    private String url;
    
    public HTMLSourceRetriver(Consumer<String> callback, String url) 
    {
        this.callback = callback;
    }
    
    @Override
    public void RunProcess() 
    {
        StringBuilder pageSource = HTMLReader.readPage(url);
        callback.accept(url);
    }
   
}
