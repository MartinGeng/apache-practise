import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Checksum;


public class Utility {

    @Test
    public void testIO() throws IOException {
        InputStream in = new URL("http://commons.apache.org").openStream();
        try {
            InputStreamReader inR = new InputStreamReader(in);
            BufferedReader buf = new BufferedReader(inR);
            String line;
            while ((line = buf.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            in.close();
        }
    }

    /**
     * IOUtils contains utility methods dealing with reading, writing and copying.
     * The methods work on InputStream, OutputStream, Reader and Writer.
         toXxx/read - these methods read data from a stream
         write - these methods write data to a stream
         copy - these methods copy all the data from one stream to another
         contentEquals - these methods compare the content of two streams
     */
    @Test
    public void testIOUtils() throws IOException {
        InputStream in = new URL("http://commons.apache.org").openStream();
        try {
            System.out.println(IOUtils.toString(in));
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * The FileUtils class contains utility methods for working with File objects.
     * These include reading, writing, copying and comparing files.
         writing to a file
         reading from a file
         make a directory including parent directories
         copying files and directories
         deleting files and directories
         converting to and from a URL
         listing files and directories by filter and extension
         comparing file content
         file last changed date
         calculating a checksum
     */
    @Test
    public void testFileUtils() throws IOException {
        File file = new File("/commons/io/project.properties");
        List lines = FileUtils.readLines(file, "UTF-8");

    }

    /**
     * The FilenameUtils class contains utility methods for working with filenames without using File objects.
     * The class aims to be consistent between Unix and Windows, to aid transitions between these environments
     * (such as moving from development to production).
     *  (example C:\dev\project\file.txt):

         the prefix - C:\
         the path - dev\project\
         the full path - C:\dev\project\
         the name - file.txt
         the base name - file
         the extension - txt
     */
    @Test
    public void testFilenameUtils() throws IOException {
        String filename = "C:/commons/io/../lang/project.xml";
        String normalized = FilenameUtils.normalize(filename);
        // result is "C:/commons/lang/project.xml"

        String baseName = FilenameUtils.getBaseName(normalized);
        boolean isContains = FilenameUtils.directoryContains("C:/", baseName);
        String extension = FilenameUtils.getExtension(filename);
        String fullPath = FilenameUtils.getFullPath(normalized);

        String name = FilenameUtils.getName(filename);
        String path = FilenameUtils.getPath(filename);

        boolean isExtension = FilenameUtils.isExtension(filename, "xml");
        isExtension = FilenameUtils.isExtension(filename, new String[]{"xml", ""});
        isExtension = FilenameUtils.isExtension(filename, new ArrayList<String>());

        String noExtension = FilenameUtils.removeExtension(filename);
    }

    /**
     * The FileSystemUtils class contains utility methods for working with the file system to access functionality not supported by the JDK.
     * Currently,
     * the only method is to get the free space on a drive. Note that this uses the command line, not native code.
     * @throws IOException
     */
    @Test
    public void testFileSystemUtils() throws IOException {
        long freeSpace = FileSystemUtils.freeSpace("C:/");
    }
}
