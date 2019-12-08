/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luonglv.uitls;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

/**
 *
 * @author LeVaLu
 */
public class ImageUtils implements Serializable {

     public static void writeImageToDisk(ServletContext context, Part partFile) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = partFile.getInputStream();

            // Get the current runtime absolute path
            String fullPath = context.getRealPath("/");

            // Move the path back to the project directory
            String pathArr[] = fullPath.split("\\\\build\\\\web");
            String projectPath = pathArr[0];

            // Get the photo name and the extension
            String photoName = Paths.get(partFile.getSubmittedFileName()).getFileName().toString();

            // Create file to store image
            File fileToStore = new File(projectPath + "/web/assets/" + photoName);
            outputStream = new FileOutputStream(fileToStore);

            // Read Part file content to the the buffer
            int len;
            while ((len = inputStream.read()) > -1) {
                // Write content in buffer to the disk
                outputStream.write(len);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }

    }
}
