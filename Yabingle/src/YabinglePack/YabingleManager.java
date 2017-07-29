/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * open the template in the editor.
 */
package YabinglePack;

/**
 *
 * @author Hoshi
 */
public class YabingleManager 
{
    public static class EngineReference
    {

        private String hrefPattern;
        private String searchPattern;
        
       
        public String getHrefPattern() {
            return hrefPattern;
        }

        
        public EngineReference(String hrefPattern, String searchPattern)
        {
            this.hrefPattern = hrefPattern;
            this.searchPattern = searchPattern;
        }
  
    }
    
    private static Homepage homepage;
    
     
    public static EngineReference Yahoo;
    public static EngineReference Bing;
    
    private static final String yahooSearchPattern = "https://sg.search.yahoo.com/search?q=";
    private static final String yahooHrefPattern = "<a class=\" td-u\" href=\"((http)|(https))[:][/]{2}\\S+\""; 
    
    private static final String bingSearchPattern = "https://www.bing.com/search?q=";
    private static final String bingHrefPattern = "<li class=\"b_algo\"><h2><a href=\"((http)|(https)):[/]{2}\\S+\"";

    public static void Initialize(Homepage homepage)
    {
        Yahoo = new EngineReference(yahooHrefPattern,yahooSearchPattern);
        Bing = new EngineReference(bingHrefPattern,bingSearchPattern);
        
        YabingleManager.homepage = homepage;
    }
    
    public static void SearchText(String searchText)
    {
        searchText = searchText.replaceAll(" ", "+");
        String bingSearchLink = Bing.searchPattern + searchText;
        //String yahooSearchLink = yahooSearchLinkFormat + searchText;
        HTMLSourceTask bingHTMLTask = new HTMLSourceTask(pageSource -> GetLinks(pageSource), bingSearchLink,Bing);
        
        //HTMLSourceTask yahooHTMLTask = new HTMLSourceTask(pageSource -> GetLinks(pageSource), yahooSearchLink);
        ThreadManager.AddRequest(bingHTMLTask);   
    }
    
    public static void GetLinks(HTMLSourceTask htmlSource)
    {
        //homepage.SetText(htmlSource.getPageSource().toString());
        LinkRetriverTask linkTask = new LinkRetriverTask(htmlSource,link -> AddLink(link));
        ThreadManager.AddRequest(linkTask);
    }
    
    public static void AddLink(String link)
    {
        System.out.println(link);
    }
}
