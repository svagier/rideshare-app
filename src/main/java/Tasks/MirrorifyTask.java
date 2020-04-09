package main.java.Tasks;

import main.java.Users.Driver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MirrorifyTask implements Runnable {
    private ArrayList<Driver> drivers;

    public MirrorifyTask(ArrayList<Driver> driversList) {
        this.drivers = driversList;
    }

    @Override
    public void run() {

    }

    public void convertImageToMirror(Driver driver, String inputPath, String inputFormat, String outputPath, String outputFormat) {
        BufferedImage mirroredImage = new BufferedImage(driver.getImageWidth(), driver.getImageHeight(), BufferedImage.TYPE_INT_BGR);
        // TYPE_3BYTE_BGR or TYPE_INT_BGR for Windows, TYPE_INT_ARGB should work for Linux

        // Create mirror image pixel by pixel, row by row
        for (int y = 0; y < driver.getImageHeight(); y++)
        {
            // leftX starts from the left side of the profileImage and rightX starts from the right side of the profileImage
            for (int leftX = 0, rightX = driver.getImageWidth() - 1; leftX < driver.getImageWidth(); leftX++, rightX--)
            {
                // get pixel value from the source (profileImage)
                int pixelValue = driver.getProfileImage().getRGB(leftX, y);
                // set the pixel value from the source to a pixel with mirrored position
                mirroredImage.setRGB(rightX, y, pixelValue);
            }
        }
        try
        {
            String outputPathWithFile = outputPath + driver.getUsername() + "." + outputFormat;
            File output = new File(outputPathWithFile);
            ImageIO.write(mirroredImage, outputFormat, output);
            System.out.println("Image conversion done, new mirror image saved in " + outputPathWithFile);
            driver.setProfileImage(mirroredImage);
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
    }
}
