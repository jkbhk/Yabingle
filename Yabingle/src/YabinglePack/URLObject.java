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
public class URLObject implements Serializable
{
    private String url;
    private StringBuilder pageSource;

    public URLObject(String url) {
        this.url = url;
        pageSource = null;
    }
    
    public String getUrl() {
        return url;
    }

    public StringBuilder getPageSource() {
        return pageSource;
    }

    public void setPageSource(StringBuilder pageSource) {
        this.pageSource = pageSource;
    }
    
    
}
