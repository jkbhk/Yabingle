/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YabinglePack;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Hoshi
 */
public class SearchPhraseTask extends TaskObject
{
    private static Pattern pattern;
    private static Matcher matcher;
    
    private Consumer<SearchPhraseTask> callback;
    private HTMLSourceTask sourceTask;
    private int searchPhraseOccurance;
    
    public SearchPhraseTask(HTMLSourceTask sourceTask, Consumer<SearchPhraseTask> callback)
    {
        this.sourceTask = sourceTask;
        this.callback = callback;
    }

    public HTMLSourceTask getSourceTask() {
        return sourceTask;
    }

    public int getSearchPhraseOccurance() {
        return searchPhraseOccurance;
    }
    
    
    
    @Override
    public void RunProcess()   
    {//  //       
        try 
        {      
            pattern = Pattern.compile("(" + YabingleManager.currentSearchWord + ")");
            matcher = pattern.matcher(sourceTask.getPageSource().toString());
        
            while(matcher.find())
            {
                searchPhraseOccurance++;
            }        
            callback.accept(this);
        } 
        catch (Exception e) 
        {
            System.out.println("error");
        }

    }
    
}
