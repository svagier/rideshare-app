package main.java.Tasks;

import main.java.Users.Driver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MirrorifyTask implements Runnable {

    private ArrayList<Driver> drivers;
    private String imageOutputPath;
    private String imageOutputFormat;

    public MirrorifyTask(ArrayList<Driver> driversList, String imageOutputPath, String imageOutputFormat) {
        this.drivers = driversList;
        this.imageOutputFormat = imageOutputFormat;
        this.imageOutputPath = imageOutputPath;
    }

    @Override
    public void run() {
        System.out.println("Starting to mirrorify " + this.getDrivers().size() + " driver's photos");
        for (int i=0; i < drivers.size(); i++)
            this.convertImageToMirror(drivers.get(i), this.getImageOutputPath(), this.getImageOutputFormat());
        System.out.println("Finished mirroryfing " + this.getDrivers().size() + " driver's photos");

    }

    public void convertImageToMirror(Driver driver, String outputPath, String outputFormat) {
        // set the RGB/BGR imageType variable accordingly to the OS
        int imageType = BufferedImage.TYPE_INT_ARGB;
        String osName = System.getProperty("os.name");
        try {
            if (osName == null) {
                throw new IOException("Operating System name not found.");
            }
            osName = osName.toLowerCase(Locale.ENGLISH);
            System.out.println("------------This is " + osName + "!------------");
            if (osName.contains("windows")) {
                imageType = BufferedImage.TYPE_INT_BGR;
            } else if (osName.contains("mac os")) {
                imageType = BufferedImage.TYPE_INT_BGR;     //<----CHANGE HERE
            } else
                imageType = BufferedImage.TYPE_INT_ARGB;
        } catch (Exception e) {
            System.out.println("Error while getting Operating System name: " + e);
        }


        BufferedImage mirroredImage = new BufferedImage(driver.getImageWidth(), driver.getImageHeight(), imageType);
        // TYPE_3BYTE_BGR or TYPE_INT_BGR for Windows, TYPE_INT_ARGB should work for Linux
        synchronized (driver.getProfileImage()){
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
//            System.out.println("Image conversion done, new mirror image saved in " + outputPathWithFile);
            driver.setProfileImage(mirroredImage);
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }

        }
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    public String getImageOutputPath() {
        return imageOutputPath;
    }

    public void setImageOutputPath(String imageOutputPath) {
        this.imageOutputPath = imageOutputPath;
    }

    public String getImageOutputFormat() {
        return imageOutputFormat;
    }

    public void setImageOutputFormat(String imageOutputFormat) {
        this.imageOutputFormat = imageOutputFormat;
    }
}
