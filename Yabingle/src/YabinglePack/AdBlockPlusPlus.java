package YabinglePack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AdBlockPlusPlus {
    
   
    private static Pattern pattern;
    private static Matcher matcher;
     
    // This doesn't really remove ads, it just takes the non ad section
    public static String removeAds(String page,String filterPattern)
    {

        pattern = Pattern.compile(filterPattern);
        matcher = pattern.matcher(page);
        
        if(matcher.find())
        {
            System.out.println("found");
            return matcher.group();
  
        }
        
        
        System.out.println("Could not filter, regex error?");
        return page;
        
    }
    
    
  
    
  
    
   
    
}
