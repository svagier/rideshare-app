package main.java.Tasks;

import main.java.Users.Driver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GreenifyTask implements Runnable {
    private List<Driver> drivers;

    public GreenifyTask(ArrayList<Driver> driversList) {
        this.drivers = driversList;
    }

    @Override
    public void run() {

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
}
