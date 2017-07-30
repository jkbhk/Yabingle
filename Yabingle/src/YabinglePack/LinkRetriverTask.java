
package YabinglePack;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 *
 * @author looi
 */
public class LinkRetriverTask extends TaskObject
{
    private HTMLSourceTask htmlSourceObj;
    private ArrayList<String> hrefList = new ArrayList<>();
    private Consumer<String> callback;
    
    private ArrayList<URLObject> urlResults;
    
    public LinkRetriverTask(HTMLSourceTask htmlTaskObj,Consumer<String> callback, ArrayList<URLObject> urlResults)
    {
        this.htmlSourceObj = htmlTaskObj;
        this.callback = callback;
        this.urlResults = urlResults;
    }
    
    @Override
    public void RunProcess() 
    {
        hrefList = AdBlockPlusPlus.getHrefList(htmlSourceObj.getPageSource(),htmlSourceObj.getEngine());
        
        for(String unfilteredLink : hrefList)
        {
            callback.accept(AdBlockPlusPlus.GetLink(unfilteredLink));
        }
        
        if (urlResults.size() < 10)
        {
            YabingleManager.SearchNextPage(htmlSourceObj);
        }
    }

    
    
    
    
}
