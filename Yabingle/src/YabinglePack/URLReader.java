/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YabinglePack;

/**
 *
 * @author Hoshi
 */
public class URLReader extends RunnableObject
{
    private String url;
    
    private StringBuilder pageSource;

    public StringBuilder GetPageSource() 
    {
        return pageSource;
    }
    
    public URLReader(IProcessListener processListener, String url) 
    {
        super(processListener);
    }
    @Override
    public void RunProcess() 
    {
        pageSource = HTMLReader.readPage(url);
    }
   
}
