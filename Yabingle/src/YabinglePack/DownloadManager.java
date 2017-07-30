
package YabinglePack;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author looi
 */
public class DownloadManager 
{
    private static FileWriter fileWriter = null;
    private static BufferedWriter bufferedWriter = null;
    
    public static void downloadFile(String url, String page)
    {
        try
        {
            fileWriter = new FileWriter("downloaded files/" + createFileName(url) + ".html");    
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(page);
            bufferedWriter.newLine();
            bufferedWriter.close();
            
        }
        catch(Exception e)
        {
            System.out.println("error downloading file");    
        }

    }
    
    private static String createFileName(String unfiltered)
    {
        return unfiltered.replaceAll("[\\/:*?\"<>|]", "_");
    }
    
}
