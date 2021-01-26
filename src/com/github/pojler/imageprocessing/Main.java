package com.github.pojler.imageprocessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        BufferedImage image = null;
        File file = null;

        try {

            file = new File(System.getProperty("user.dir") + "//data//data.jpg");
            image = ImageIO.read(file);

        } catch (IOException e) {

            System.out.println(e);

        }

        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                int pixel = image.getRGB(x, y);
                int a = (pixel >> 24) & 0xff;
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = pixel & 0xff;
                int avg = (r + g + b) / 3;
                pixel = (a << 24) | (avg << 16) | (avg << 8) | avg;
                image.setRGB(x, y, pixel);

            }

        }
        try {
            file = new File(System.getProperty("user.dir") + "//data//grayscaledData.jpg");
            ImageIO.write(image, "png", file);
            System.out.println("Successfully converted a colored image into a grayscale image");
        } catch (java.io.IOException e) {
            System.out.println(e);
        }

    }

}
