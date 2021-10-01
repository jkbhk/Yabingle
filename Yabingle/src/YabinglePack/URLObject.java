/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YabinglePack;

import java.io.Serializable;

/**
 *
 * @author Hoshi
 */
public class URLObject implements Serializable, Comparable
{
    private String url;
    private StringBuilder pageSource;
    private int noOfOccuranceOfSearchPhrase;

    public int getNoOfOccuranceOfSearchPhrase() {
        return noOfOccuranceOfSearchPhrase;
    }

    public URLObject(String url, StringBuilder pageSource, int noOfOccuranceOfSearchPhrase) {
        this.url = url;
        this.pageSource = pageSource;
        this.noOfOccuranceOfSearchPhrase = noOfOccuranceOfSearchPhrase;
    }
    
    public String getUrl() {
        return url;
    }

    public StringBuilder getPageSource() {
        return pageSource;
    }

    @Override
    public int compareTo(Object o) {
        URLObject otherObj = (URLObject)o;
        if(noOfOccuranceOfSearchPhrase > otherObj.noOfOccuranceOfSearchPhrase)
        {
            return 1;
        }
        return -1;
    }

    
    

    
    
}
