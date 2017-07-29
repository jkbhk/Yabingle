package YabinglePack;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author looi
 */

public class AdBlockPlusPlus
{
    
    private static Pattern pattern;
    private static Matcher matcher;
     
    
    // This doesn't really remove ads, it just takes the non ad section
    public static ArrayList<String> getHrefList(StringBuilder page, YabingleManager.EngineReference ref)
    {
        ArrayList<String> allMatches = new ArrayList<>();
        
        
        
        pattern = Pattern.compile(ref.getHrefPattern());
        matcher = pattern.matcher(page);
        
        while(matcher.find())
        {
            allMatches.add(matcher.group());
            System.out.println(matcher.group());
        }

        return allMatches;
        
    }
    
    
    public static String GetLink(String unfilteredLink,YabingleManager.EngineReference ref)
    {
        String link =  unfilteredLink.replaceAll(ref.getLinkExclusionPattern(), "");
        
        return link;
    }
  
    
  
    
   
    
}
