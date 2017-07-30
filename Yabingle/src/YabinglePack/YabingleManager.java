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
    
    public static EngineReference Yahoo;
    public static EngineReference Bing;
    
    private static Homepage homepage;
    private static long searchTime;
        
    private static final String yahooSearchPattern = "https://sg.search.yahoo.com/search?q=";
    private static final String yahooHrefPattern = "<div class=\"yst result\"><h3 class=\"title\"><a href=\"((http)|(https))[:][/]{2}\\S+\"";
            //"<a class=\" td-u\" href=\"((http)|(https))[:][/]{2}\\S+\""; 
    
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
        searchTime = System.currentTimeMillis();
        searchText = searchText.replaceAll(" ", "+");
        String bingSearchLink = Bing.searchPattern + searchText;
        String yahooSearchLink = Yahoo.searchPattern + searchText;
        HTMLSourceTask bingHTMLTask = new HTMLSourceTask(pageSource -> GetLinks(pageSource), bingSearchLink, Bing);
        
        HTMLSourceTask yahooHTMLTask = new HTMLSourceTask(pageSource -> GetLinks(pageSource), yahooSearchLink, Yahoo);
        ThreadManager.AddRequest(bingHTMLTask);  
        ThreadManager.AddRequest(yahooHTMLTask);
    }
    
    public static void GetLinks(HTMLSourceTask htmlSource)
    {
        //homepage.SetText(htmlSource.getPageSource().toString());
        LinkRetriverTask linkTask = new LinkRetriverTask(htmlSource,link -> AddLink(link));
        ThreadManager.AddRequest(linkTask);
    }
    
    public static synchronized void AddLink(String link)
    {
        if(!homepage.HaveLink(link))
        {       
            homepage.AddLink(link);
            if(homepage.HaveNoOfLinks(10))
            {
                searchTime = System.currentTimeMillis() - searchTime;
                homepage.SetText(String.valueOf(searchTime));
            }
            HTMLSourceTask linkHTMLTask = new HTMLSourceTask(linkTask-> DownloadLink(linkTask), link);
            ThreadManager.AddRequest(linkHTMLTask);
        }
    }
    
    public static void DownloadLink(HTMLSourceTask htmlSource)
    {
        System.out.println(htmlSource.getUrl());
        
        DownloadTask downloadTask = new DownloadTask(htmlSource.getUrl()
                , htmlSource.getPageSource().toString());
        ThreadManager.AddRequest(downloadTask);
        
        
    }
    
}
