/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YabinglePack;

import java.util.ArrayList;

/**
 *
 * @author Hoshi
 */
public class SearchFilter extends RunnableObject
{
    private String url;

    private ArrayList<String> urlList;

    public ArrayList<String> getURLList() 
    {
        return urlList;
    }
    
    public SearchFilter(IProcessListener processListener, String url) 
    {
        super(processListener);
    }
    
    @Override
    public void RunProcess() 
    {
        StringBuilder pageSource = HTMLReader.readPage(url);
    }
   
}
