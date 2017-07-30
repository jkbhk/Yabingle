
package YabinglePack;

/**
 *
 * @author looi
 */
public class DownloadTask extends TaskObject 
{
    private String fileName;
    private String page;
    
    public DownloadTask(String fileName, String page)       
    {
        this.fileName = fileName;
        this.page = page;
    }
    
    @Override
    public void RunProcess() 
    {
        DownloadManager.downloadFile(fileName, page);
    }
    
    
    
    
}
