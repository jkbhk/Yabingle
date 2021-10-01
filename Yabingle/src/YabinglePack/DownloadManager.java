
package YabinglePack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author looi
 */
public class DownloadManager 
{
    
    private static final String directory = "downloaded files/";
    private static final String htmlExtention = ".html";
    private static FileWriter fileWriter = null;
    private static BufferedWriter bufferedWriter = null;
    
    public static void Initialize()
    {
        checkForDirectory();
    }
    
    
    public static void downloadFile(String url, String page)
    {
        try
        {
            fileWriter = new FileWriter(directory + createFileName(url) + htmlExtention);    
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
    
    public static String getFileName(String url)
    {
        return directory + createFileName(url) + htmlExtention;
    }
    
    private static String createFileName(String unfiltered)
    {
        return unfiltered.replaceAll("[\\/:*?\"<>|]", "_");
    }
    
    
    
    private static void checkForDirectory()
    {
        new File("downloaded files").mkdir();
    }
    
}
