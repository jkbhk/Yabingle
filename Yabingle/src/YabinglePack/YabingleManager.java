/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * open the template in the editor.
 */
package YabinglePack;

import com.sun.corba.se.spi.orbutil.fsm.Guard;
import java.text.DecimalFormat;
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
        private String pagePattern;
       
        public String getHrefPattern() {
            return hrefPattern;
        }

        
        public EngineReference(String hrefPattern, String searchPattern, String pagePattern)
        {
            this.hrefPattern = hrefPattern;
            this.searchPattern = searchPattern;
            this.pagePattern = pagePattern;
        }
  
    }
    
    public static String currentSearchWord;
    public static EngineReference Yahoo;
    public static EngineReference Bing;
    public static int noOfResults = 50;
    
    private static Homepage homepage;
    private static long searchTime;
    
    private static final String yahooSearchPattern = "https://sg.search.yahoo.com/search?q=";
    private static final String yahooHrefPattern = "<div class=\"yst result\"><h3 class=\"title\"><a href=\"((http)|(https))[:][/]{2}\\S+\"";
            //"<a class=\" td-u\" href=\"((http)|(https))[:][/]{2}\\S+\""; 
    private static final String yahooPagePattern = "b=";
    
    private static final String bingSearchPattern = "https://www.bing.com/search?q=";
    private static final String bingHrefPattern = "<li class=\"b_algo\"><h2><a href=\"((http)|(https)):[/]{2}\\S+\"";
    private static final String bingPagePattern = "first=";
    
    private static ArrayList<String> tempURLList = new ArrayList<>();
    
    public static void Initialize(Homepage homepage)
    {
        Yahoo = new EngineReference(yahooHrefPattern, yahooSearchPattern, yahooPagePattern);
        Bing = new EngineReference(bingHrefPattern, bingSearchPattern, bingPagePattern);
        
        YabingleManager.homepage = homepage;
    }
    
    public static void SearchText(String searchText)
    {
        ResetSearch();
        currentSearchWord = searchText;
        SearchQuery(searchText, 1);
    }
    
    private static void SearchQuery(String searchText, int page)
    {
        searchText = searchText.replaceAll(" ", "+");
        String bingSearchLink = Bing.searchPattern + searchText + "&" + Bing.pagePattern + page;
        String yahooSearchLink = Yahoo.searchPattern + searchText + "&" + Yahoo.pagePattern + (((page - 1) * 10) + 1);
        
        HTMLSourceTask bingHTMLTask = new HTMLSourceTask(pageSource -> GetLinks(pageSource), bingSearchLink, Bing);
        HTMLSourceTask yahooHTMLTask = new HTMLSourceTask(pageSource -> GetLinks(pageSource), yahooSearchLink, Yahoo) ;
        
        ThreadManager.AddRequest(bingHTMLTask);
        ThreadManager.AddRequest(yahooHTMLTask);
    }
    
    public static void SearchNextPage(HTMLSourceTask sourceTask)
    {
        String searchLink = sourceTask.getUrl().replaceAll(".+(" + sourceTask.getEngine().pagePattern +")", "");
        
        int pageNum = Integer.parseInt(searchLink);
        
        if(sourceTask.getEngine() == Yahoo)
        {
            pageNum += 10;
        }
        else if (sourceTask.getEngine() == Bing)
        {
            pageNum++;
        }
        
        searchLink = sourceTask.getUrl().replaceAll("(" + sourceTask.getEngine().pagePattern +").+",sourceTask.getEngine().pagePattern + pageNum);
        
        HTMLSourceTask HTMLTask = new HTMLSourceTask(pageSource -> GetLinks(pageSource), searchLink, sourceTask.getEngine());
        System.out.println(HTMLTask.getUrl());
        ThreadManager.AddRequest(HTMLTask);
    }
    
    public static void ResetSearch()
    {
        homepage.heartLabel.setVisible(false);
        homepage.SetText("");
        searchTime = System.currentTimeMillis();
        homepage.ClearURLList();
        if(tempURLList.size() > 0)
        {
            tempURLList.clear();
        }
    }
    
    public static void GetLinks(HTMLSourceTask htmlSource)
    {
        //homepage.SetText(htmlSource.getPageSource().toString());
        LinkRetriverTask linkTask = new LinkRetriverTask(htmlSource,link -> AddLink(link), homepage.urlResults);
        ThreadManager.AddRequest(linkTask);
    }
    
    public static synchronized void AddLink(String link)
    {
        if(!tempURLList.contains(link))
        {
            tempURLList.add(link);
//            if(homepage.HaveNoOfLinks(noOfResults))
//            {
//                searchTime = System.currentTimeMillis() - searchTime;
//                homepage.SetText(String.valueOf(searchTime));
//            }
            HTMLSourceTask linkHTMLTask = new HTMLSourceTask(linkTask-> FindSearchPhrase(linkTask), link);
            ThreadManager.AddRequest(linkHTMLTask);
        }
    }
    
    public static void FindSearchPhrase(HTMLSourceTask htmlSource)
    {
        if(htmlSource.getPageSource() != null)
        {
            SearchPhraseTask searchPhraseTask = new SearchPhraseTask(htmlSource, phraseTask -> DownloadLink(phraseTask));
            
            ThreadManager.AddRequest(searchPhraseTask);
        }
    }
    
    public static synchronized void DownloadLink(SearchPhraseTask searchTask)
    {
        if(!homepage.HaveUrlObject(searchTask.getSourceTask().getUrl()) && homepage.urlResults.Count() < noOfResults)
        {
            homepage.AddURLObject(new URLObject(searchTask.getSourceTask().getUrl(), searchTask.getSourceTask().getPageSource(), searchTask.getSearchPhraseOccurance()));

            System.out.println(searchTask.getSourceTask().getUrl());

            DownloadTask downloadTask = new DownloadTask(searchTask.getSourceTask().getUrl()
                , searchTask.getSourceTask().getPageSource().toString(), ()-> Complete(),homepage.HaveNoOfLinks(noOfResults));
            
            ThreadManager.AddRequest(downloadTask);
        }    
    }
    
    public static void Complete()
    {
        ThreadManager.StopProcesses();
        System.out.println("------------------------");
        long stopSearchTime = System.currentTimeMillis() - searchTime;

        DecimalFormat df = new DecimalFormat("#.00");
        String timeTaken = df.format(stopSearchTime/1000.0);

        System.out.println(timeTaken + " seconds");
        homepage.SetText(timeTaken + " seconds");
        System.out.println("------------------------");
        homepage.heartLabel.setVisible(true);
        homepage.UpdateDisplay();
    }
}
