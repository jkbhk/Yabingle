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
        private String linkExclusionPattern;
        private String searchPattern;
        
       
        public String getHrefPattern() {
            return hrefPattern;
        }

        public String getLinkExclusionPattern() {
            return linkExclusionPattern;
        }
       
        
        public EngineReference(String hrefPattern, String linkExclusionPattern, String searchPattern)
        {
            this.hrefPattern = hrefPattern;
            this.linkExclusionPattern = linkExclusionPattern;
            this.searchPattern = searchPattern;
        }
  
    }
    
    public static EngineReference Yahoo;
    public static EngineReference Bing;
    
    private static final String yahooHrefPattern = "(<a).+(</?a>)";
    private static final String yahooLinkPattern = "(.+=\")|(\".+)"; // use this with String.replaceAll
    private static final String bingHrefPattern = "<li class=\"b_algo\"><h2><a href=\"((http)|(https)):[/]{2}[\\w./-]+\"";
    private static final String bingLinkPattern = "(.+=\")|(\")";
    
    private static String bingSearchLinkPattern = "https://www.bing.com/search?q=";
    private static String yahooSearchLinkPattern = "https://sg.search.yahoo.com/search?q=";
    
    private static Homepage homepage;
    
    
    public static void Initialize(Homepage homepage)
    {
        Yahoo = new EngineReference(yahooHrefPattern, yahooLinkPattern,yahooSearchLinkPattern);
        Bing = new EngineReference(bingHrefPattern, bingLinkPattern,bingSearchLinkPattern);
        
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
        homepage.SetText(htmlSource.toString());
        LinkRetriverTask linkTask = new LinkRetriverTask(htmlSource,link -> AddLink(link));
        ThreadManager.AddRequest(linkTask);
    }
    
    public static void AddLink(String link)
    {
        System.out.println(link);
       
    }
}
