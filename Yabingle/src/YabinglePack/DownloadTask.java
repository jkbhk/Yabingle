
package YabinglePack;

import java.util.function.Consumer;

/**
 *
 * @author looi
 */
public class DownloadTask extends TaskObject 
{
    private String fileName;
    private String page;
    private Runnable callback;
    private boolean canCallback;
    
    public DownloadTask(String fileName, String page, Runnable callback, boolean canCallback)       
    {
        this.fileName = fileName;
        this.page = page;
        this.callback = callback;
        this.canCallback = canCallback;
    }
    
    @Override
    public void RunProcess() 
    {
        try
        {
            DownloadManager.downloadFile(fileName, page);
            if(canCallback){
                callback.run();
            }
        }
        catch(Exception e)
        {
            
        }
    }
    
    
    
    
}
