package main.java.Tasks;

import main.java.Users.Driver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * The type Mirrorify task, which makes mirror versions of all images in the list of Drivers.
 */
public class MirrorifyTask implements Runnable {

    private ArrayList<Driver> drivers;
    private String imageOutputPath;
    private String imageOutputFormat;

    /**
     * Instantiates a new Mirrorify task.
     *
     * @param driversList       the drivers list
     * @param imageOutputPath   the image output path
     * @param imageOutputFormat the image output format
     */
    public MirrorifyTask(ArrayList<Driver> driversList, String imageOutputPath, String imageOutputFormat) {
        this.drivers = driversList;
        this.imageOutputFormat = imageOutputFormat;
        this.imageOutputPath = imageOutputPath;
    }

    /**
     * for each Driver in the drivers list, run mirroryfing for his/her proile image.
     */
    @Override
    public void run() {
        long start = System.currentTimeMillis();

        System.out.println("Starting to mirrorify " + this.getDrivers().size() + " driver's photos");
        for (int i=0; i < drivers.size(); i++)
            this.convertImageToMirror(drivers.get(i), this.getImageOutputPath(), this.getImageOutputFormat());
        long end = System.currentTimeMillis();
        long duration = end-start;
        System.out.println("Finished mirroryfing " + this.getDrivers().size() + " driver's photos. Duration: " + duration + "ms.");
    }

    /**
     * Convert image to mirror.
     *
     * @param driver       the Driver
     * @param outputPath   the output path where converted image is saved
     * @param outputFormat the output format
     */
    public void convertImageToMirror(Driver driver, String outputPath, String outputFormat) {
        int imageType = this.getImageType();        // set the RGB/BGR imageType variable accordingly to the OS
        BufferedImage mirroredImage = new BufferedImage(driver.getImageWidth(), driver.getImageHeight(), imageType);
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

    /**
     * Below check is just in case, because various operating systems may use different values of imageType.
     * Tested to work properly on Windows and MacOS.
     * TYPE_3BYTE_BGR or TYPE_INT_BGR for Windows and MacOS, TYPE_INT_ARGB should work for Linux
     * https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html
     *
     * @return imageType, which is a static int defined in java.awt.image.BufferedImage;
     */
    private int getImageType() {
        int imageType = BufferedImage.TYPE_INT_ARGB;
        String osName = System.getProperty("os.name");
        try {
            if (osName == null) {
                throw new IOException("Operating System name not found.");
            }
            osName = osName.toLowerCase(Locale.ENGLISH);
            if (osName.contains("windows")) {
                imageType = BufferedImage.TYPE_INT_BGR;
            } else if (osName.contains("mac os")) {
                imageType = BufferedImage.TYPE_INT_BGR;
            } else
                imageType = BufferedImage.TYPE_INT_ARGB;
        } catch (Exception e) {
            System.out.println("Error while getting Operating System name: " + e);
        }
        return imageType;
    }

    /**
     * Gets drivers.
     *
     * @return the drivers
     */
    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    /**
     * Sets drivers.
     *
     * @param drivers the drivers
     */
    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    /**
     * Gets image output path.
     *
     * @return the image output path
     */
    public String getImageOutputPath() {
        return imageOutputPath;
    }

    /**
     * Sets image output path.
     *
     * @param imageOutputPath the image output path
     */
    public void setImageOutputPath(String imageOutputPath) {
        this.imageOutputPath = imageOutputPath;
    }

    /**
     * Gets image output format.
     *
     * @return the image output format
     */
    public String getImageOutputFormat() {
        return imageOutputFormat;
    }

    /**
     * Sets image output format.
     *
     * @param imageOutputFormat the image output format
     */
    public void setImageOutputFormat(String imageOutputFormat) {
        this.imageOutputFormat = imageOutputFormat;
    }
}
