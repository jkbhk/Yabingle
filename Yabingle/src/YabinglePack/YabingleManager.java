/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * open the template in the editor.
 */
package YabinglePack;

import java.util.ArrayList;

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

    private static int noOfResults = 10;
    private static ArrayList<String> tempURLList = new ArrayList<>();
    private static boolean searchProcessing = false;
    
    public static void Initialize(Homepage homepage)
    {
        Yahoo = new EngineReference(yahooHrefPattern,yahooSearchPattern);
        Bing = new EngineReference(bingHrefPattern,bingSearchPattern);
        
        YabingleManager.homepage = homepage;
    }
    
    public static void SearchText(String searchText)
    {
        ResetSearch();
        
        searchText = searchText.replaceAll(" ", "+");
        String bingSearchLink = Bing.searchPattern + searchText;
        String yahooSearchLink = Yahoo.searchPattern + searchText;
        HTMLSourceTask bingHTMLTask = new HTMLSourceTask(pageSource -> GetLinks(pageSource), bingSearchLink, Bing);
        
        HTMLSourceTask yahooHTMLTask = new HTMLSourceTask(pageSource -> GetLinks(pageSource), yahooSearchLink, Yahoo);
        ThreadManager.AddRequest(bingHTMLTask);  
        ThreadManager.AddRequest(yahooHTMLTask);
    }
    
    public static void ResetSearch()
    {
        searchProcessing = true;
        searchTime = System.currentTimeMillis();
        homepage.ClearURLList();
        if(tempURLList.size() > 0)
        {
            tempURLList.clear();
        }
    }
    
    public static boolean CanSearch()
    {
        return !searchProcessing;
    }
    
    public static void GetLinks(HTMLSourceTask htmlSource)
    {
        //homepage.SetText(htmlSource.getPageSource().toString());
        LinkRetriverTask linkTask = new LinkRetriverTask(htmlSource,link -> AddLink(link));
        ThreadManager.AddRequest(linkTask);
    }
    
    public static synchronized void AddLink(String link)
    {
        if(!tempURLList.contains(link) && tempURLList.size() < noOfResults)
        {
            tempURLList.add(link);
//            if(homepage.HaveNoOfLinks(noOfResults))
//            {
//                searchTime = System.currentTimeMillis() - searchTime;
//                homepage.SetText(String.valueOf(searchTime));
//            }
            HTMLSourceTask linkHTMLTask = new HTMLSourceTask(linkTask-> DownloadLink(linkTask), link);
            ThreadManager.AddRequest(linkHTMLTask);
        }
    }
    
    public static void DownloadLink(HTMLSourceTask htmlSource)
    {
        if(htmlSource.getPageSource() != null && !homepage.HaveUrlObject(htmlSource.getUrl()))
        {
            homepage.AddURLObject(new URLObject(htmlSource.getUrl(), htmlSource.getPageSource()));

            System.out.println(htmlSource.getUrl());

            if(homepage.HaveNoOfLinks(noOfResults))
            {
                System.out.println("------------------------");
                searchTime = System.currentTimeMillis() - searchTime;
                System.out.println((searchTime/1000.0) + " seconds");
                homepage.SetText(String.valueOf(searchTime));
                searchProcessing = false;
                System.out.println("------------------------");
            }

            DownloadTask downloadTask = new DownloadTask(htmlSource.getUrl()
                , htmlSource.getPageSource().toString());
            ThreadManager.AddRequest(downloadTask);
        }
        
        
        
    }
    
}
