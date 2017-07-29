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
    
    private static final String linkExclusionPattern = "(.+=\")|(\")";
    
    // This doesn't really remove ads, it just retrieves the non ad section
    public static ArrayList<String> getHrefList(StringBuilder page, YabingleManager.EngineReference ref)
    {
        ArrayList<String> allMatches = new ArrayList<>();
        
        
        pattern = Pattern.compile(ref.getHrefPattern());
        matcher = pattern.matcher(page);
        
        while(matcher.find())
        {
            allMatches.add(matcher.group());
        }

        return allMatches;
        
    }

    // Extracts only the link portion of a line
    public static String GetLink(String unfilteredLink)
    {
        String link =  unfilteredLink.replaceAll(linkExclusionPattern, ""); 
        return link;
    }
  
    
  
    
   
    
}
