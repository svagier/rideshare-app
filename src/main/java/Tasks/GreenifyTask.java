package main.java.Tasks;

import main.java.Users.Driver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Greenify task, which makes green versions of all images in the list of Drivers.
 */
public class GreenifyTask implements Runnable {
    private List<Driver> drivers;
    private String imageOutputPath;
    private String imageOutputFormat;


    /**
     * Instantiates a new Greenify task.
     *
     * @param driversList       the drivers list
     * @param imageOutputPath   the image output path
     * @param imageOutputFormat the image output format
     */
    public GreenifyTask(ArrayList<Driver> driversList, String imageOutputPath, String imageOutputFormat) {
        this.drivers = driversList;
        this.imageOutputFormat = imageOutputFormat;
        this.imageOutputPath = imageOutputPath;
    }

    /**
     * for each Driver in the drivers list, run greenyfying for his/her proile image.
     */
    @Override
    public void run() {
        long start = System.currentTimeMillis();


        System.out.println("Starting to greenify " + this.getDrivers().size() + " driver's photos");
        for (int i=0; i < drivers.size(); i++)
            this.convertImageToGreen(drivers.get(i), this.getImageOutputPath(), this.getImageOutputFormat());
        long end = System.currentTimeMillis();
        long duration = end-start;
        System.out.println("Finished greenyfying " + this.getDrivers().size() + " driver's photos. Duration: " + duration + "ms.");
    }

    /**
     * Convert image to green.
     *
     * @param driver       the driver
     * @param outputPath   the output path
     * @param outputFormat the output format
     */
    public void convertImageToGreen(Driver driver, String outputPath, String outputFormat) {
        synchronized (driver.getProfileImage()){
        try {
            for (int i = 0; i < driver.getImageHeight(); i++) {
                for (int j = 0; j < driver.getImageWidth(); j++) {
                    Color c = new Color(driver.getProfileImage().getRGB(j, i));
                    int green = (int) (c.getGreen() * 0.8);
                    int onlyGreen = new Color(0, green, 0).getRGB();
                    driver.getProfileImage().setRGB(j, i, onlyGreen);
                }
            }
            String outputPathWithFile = outputPath + driver.getUsername() + "." + outputFormat;
            File output = new File(outputPathWithFile);
            ImageIO.write(driver.getProfileImage(), outputFormat, output);
//            System.out.println("Image conversion done, new green image saved in " + outputPathWithFile);
        } catch (Exception e) {
            System.out.println("Exception:  " + e);
        }

        }
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

    /**
     * Gets drivers.
     *
     * @return the drivers
     */
    public List<Driver> getDrivers() {
        return drivers;
    }

    /**
     * Sets drivers.
     *
     * @param drivers the drivers
     */
    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }
}
