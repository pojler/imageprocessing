package com.github.pojler.imageprocessing;

import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {

        ImageInOut imageinout = new ImageInOut();
        BufferedImage image = imageinout.loadImage(System.getProperty("user.dir") + "//data//data.jpg");
        ImageProcessor ip = new ImageProcessor();
        ip.Thresholding(123, image, System.getProperty("user.dir") + "//data//datathresholded.jpg");
        ip.SobelFiltering(image, System.getProperty("user.dir") + "//data//dataedges.jpg");
    }

}
