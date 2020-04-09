package main.java.Tasks;

import main.java.Users.Driver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GreenifyTask implements Runnable {
    private List<Driver> drivers;
    private String imageOutputPath;
    private String imageOutputFormat;


    public GreenifyTask(ArrayList<Driver> driversList, String imageOutputPath, String imageOutputFormat) {
        this.drivers = driversList;
        this.imageOutputFormat = imageOutputFormat;
        this.imageOutputPath = imageOutputPath;
    }

    @Override
    public void run() {
        System.out.println("Starting to greenify " + this.getDrivers().size() + " driver's photos");
        for (int i=0; i < drivers.size(); i++)
            this.convertImageToGreen(drivers.get(i), this.getImageOutputPath(), this.getImageOutputFormat());
    }

    public void convertImageToGreen(Driver driver, String outputPath, String outputFormat) {
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
            System.out.println("Image conversion done, new green image saved in " + outputPathWithFile);
        } catch (Exception e) {
            System.out.println("Exception:  " + e);
        }
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

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }
}
