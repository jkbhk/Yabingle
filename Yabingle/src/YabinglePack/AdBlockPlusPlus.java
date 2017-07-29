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

    public static final String hrefPattern = "(<a).+(</?a>)";
    public static final String linkPattern = "(.+=\")|(\".+)"; // use this with String.replaceAll
    public static final String bingHrefPattern = "<li class=\"b_algo\"><h2><a href=\"((http)|(https)):[/]{2}[\\w./-]+\"";
    public static final String bingLinkPattern = "(.+=\")|(\")";
   
    private static Pattern pattern;
    private static Matcher matcher;
     
    
    
    // This doesn't really remove ads, it just takes the non ad section
    public static ArrayList<String> getHrefList(String page, SearchEngineType type)
    {
        ArrayList<String> allMatches = new ArrayList<>();
        
        
        
        pattern = Pattern.compile(GetSpecificFilterPattern(type));
        matcher = pattern.matcher(page);
        
        while(matcher.find())
        {
            allMatches.add(matcher.group());
        }
        
        
        return allMatches;
        
    }
    
    public static String GetSpecificFilterPattern(SearchEngineType type)
    {
        switch(type)
        {
            case YAHOO:
                return null;
                
            case BING:
                return bingHrefPattern;
                
            default:
                return null;
                
        }
    }
    
    public static String GetLink(String s,String patternToExclude)
    {
        String link =  s.replaceAll(patternToExclude, "");
        
        return link;
    }
  
    
  
    
   
    
}
