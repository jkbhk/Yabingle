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
    private static String bingSearchLinkFormat = "https://www.bing.com/search?q=";
    private static String yahooSearchLinkFormat = "https://sg.search.yahoo.com/search?q=";
    
    private static Homepage homepage;
    
    public static void Initialize(Homepage homepage)
    {
        YabingleManager.homepage = homepage;
    }
    
    public static void SearchText(String searchText)
    {
        searchText = searchText.replaceAll(" ", "+");
        String bingSearchLink = bingSearchLinkFormat + searchText;
        //String yahooSearchLink = yahooSearchLinkFormat + searchText;
        
        HTMLSourceTask bingHTMLTask = new HTMLSourceTask(pageSource -> GetLinks(pageSource), bingSearchLink);
        //HTMLSourceTask yahooHTMLTask = new HTMLSourceTask(pageSource -> GetLinks(pageSource), yahooSearchLink);
        ThreadManager.AddRequest(bingHTMLTask);
    }
    
    public static void GetLinks(HTMLSourceTask htmlSource)
    {
        homepage.SetText(htmlSource.toString());
        LinkRetriverTask linkTask = new LinkRetriverTask(htmlSource);
    }
}
