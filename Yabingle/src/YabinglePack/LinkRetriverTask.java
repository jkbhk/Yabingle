
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
    
    
    public LinkRetriverTask(HTMLSourceTask htmlTaskObj,Consumer<String> callback)
    {
        this.htmlSourceObj = htmlTaskObj;
        this.callback = callback;
    }
    
    @Override
    public void RunProcess() 
    {
        hrefList = AdBlockPlusPlus.getHrefList(htmlSourceObj.getPageSource(),htmlSourceObj.getEngine());
        
        for(String unfilteredLink : hrefList)
        {
            callback.accept(AdBlockPlusPlus.GetLink(unfilteredLink));
        }
        
        
    }

    
    
    
    
}
