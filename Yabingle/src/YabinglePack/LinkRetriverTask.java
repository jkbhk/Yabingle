
package YabinglePack;

import java.util.ArrayList;

/**
 *
 * @author looi
 */
public class LinkRetriverTask extends TaskObject
{

    private HTMLSourceTask htmlSourceObj;
    ArrayList<String> hrefList = new ArrayList<>();
    
    public LinkRetriverTask(HTMLSourceTask htmlTaskObj)
    {
        this.htmlSourceObj = htmlTaskObj;
    }
    
    @Override
    public void RunProcess() 
    {
        //hrefList = AdBlockPlusPlus.getHrefList(htmlSourceObj.getPageSource(),)
    }

    
    
    
    
}
