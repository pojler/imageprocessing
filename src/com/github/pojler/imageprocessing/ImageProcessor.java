package com.github.pojler.imageprocessing;

import java.awt.image.BufferedImage;

public class ImageProcessor {

    ImageInOut imgio;

    ImageProcessor() {
        imgio = new ImageInOut();
    }

    void grayScale(BufferedImage image, String outfilename) {
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

        imgio.saveImage(outfilename, image);
    }

    void Thresholding(int threshold, BufferedImage image, String outfilename) {
        long start = System.currentTimeMillis();
        int width = image.getWidth();
        int height = image.getHeight();
        int localthreshold = threshold;

        if (threshold > 255) {
            localthreshold = 255;
        } else if (threshold < 0) {
            localthreshold = 0;
        }

        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                int pixel = image.getRGB(x, y);
                int a = (pixel >> 24) & 0xff;
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = pixel & 0xff;
                int avg = (r + g + b) / 3;
                if (avg > localthreshold) {
                    pixel = (a << 24) | (255 << 16) | (255 << 8) | 255;
                } else {
                    pixel = (a << 24) | (0 << 16) | (0 << 8) | 0;
                }
                image.setRGB(x, y, pixel);
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println("Time elapsed on thresholding " + ((stop - start) / 1000.0) + " seconds");
        imgio.saveImage(outfilename, image);

    }

    void SobelFiltering(BufferedImage image, String outfilename) {
        long start = System.currentTimeMillis();
        int[][] mask_x = {{-1, 0, 1},
                {-2, 0, 2},
                {-1, 0, 1}};

        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                int pixel = image.getRGB(x, y);
                int newpixel = 0;
                int xr = 0;
                int xg = 0;
                int xb = 0;
                int a = (pixel >> 24) & 0xff;
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = pixel & 0xff;
                int avg = 0;

                for (int i = -1; i < 2; i++) {

                    if (x + i < 0 || x + i >= width)
                        continue;
                    for (int j = -1; j < 2; j++) {
                        if (y + j < 0 || y + j >= height)
                            continue;
                        xr += mask_x[i + 1][j + 1] * (image.getRGB(x + i, y + j) >> 16 & 0xff);
                        xg += mask_x[i + 1][j + 1] * (image.getRGB(x + i, y + j) >> 8 & 0xff);
                        xb += mask_x[i + 1][j + 1] * (image.getRGB(x + i, y + j) & 0xff);

                        // System.out.println(" r = " +r+ " g = "+ g+ " b = " + b + "\n");


                    }
                    if (xr > 255) {
                        xr = 255;
                    } else if (xr < 0) {
                        xr = 0;
                    }

                    if (xg > 255) {
                        xg = 255;
                    } else if (xg < 0) {
                        xg = 0;
                    }

                    if (xb > 255) {
                        xb = 255;
                    } else if (xb < 0) {
                        xb = 0;
                    }


                }
                newpixel = a << 24 | xr << 16 | xg << 8 | xb;
                bufferedImage.setRGB(x, y, newpixel);
                xr = xg = xb = 0;
            }

        }
        long stop = System.currentTimeMillis();
        System.out.println("Time elapsed on Sobel " + ((stop - start) / 1000.0) + " seconds");
        imgio.saveImage(outfilename, bufferedImage);
    }


}


