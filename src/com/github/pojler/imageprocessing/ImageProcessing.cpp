#include "lib\CImg.h"
#include <iostream>
#include <chrono>

using namespace cimg_library;

CImg<unsigned char> thresholding (int threshold, CImg<unsigned char> image){
     auto start = std::chrono::system_clock::now();
     int height = image.height();
     int width = image.width();
     //std::cout << height << width;

     CImg<unsigned char> thresholded_image (width, height,1, 3, 0);

     for (int i = 0; i < width; i++){
         for (int j = 0; j < height; j++){
             if( (image(i, j, 0,0) + image(i, j, 0, 1) + image(i,j,0,2))/3 > threshold){
                 thresholded_image(i,j,0) =255;
                 thresholded_image(i,j,1) =255;
                 thresholded_image(i,j,2) =255;
             }
             else{}
                 thresholded_image(i,j,0) =1;
                 thresholded_image(i,j,1) =1;
                 thresholded_image(i,j,2) =1;
             }
         }
    auto end = std::chrono::system_clock::now();
    std::chrono::duration<double> elapsed_seconds = end-start;
    std::time_t end_time = std::chrono::system_clock::to_time_t(end);

    std::cout << "finished computation at " << std::ctime(&end_time)
              << "elapsed time on thresholding: " << elapsed_seconds.count() << "s\n";
    return thresholded_image;

}

CImg<unsigned char> sobelFiltering (CImg<unsigned char> image){

    auto start = std::chrono::system_clock::now();
     int height = image.height();
     int width = image.width();
     int mask_x [3][3]= {{-1,0,1},
                        {-2,0,2},
                        {-1,0,1}};

     CImg<unsigned char> edge_image (width, height,1, 3, 0);

     for (int y = 0; y < height; y++){
         for (int x = 0; x < height; x++){
            int xr=0, xg=0,xb=0;

            for (int i = -1; i<2; i++){

                if(x+i < 0 || x+i >= width)
                    continue;
                for(int j = -1; j <2; j++){

                        if(y+j <0 || y+j >=height)
                            continue;

                        xr += mask_x[i+1][j+1] * image(x+i, y+j,0);
                        xg += mask_x[i+1][j+1] * image(x+i, y+j,1);
                        xb += mask_x[i+1][j+1] * image(x+i, y+j,2);
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
                edge_image(x,y, 0) =xr;
                edge_image(x,y, 1) =xg;
                edge_image(x,y, 2) =xb;

         }
     }
    
    auto end = std::chrono::system_clock::now();
    std::chrono::duration<double> elapsed_seconds = end-start;
    std::time_t end_time = std::chrono::system_clock::to_time_t(end);

    std::cout << "finished computation at " << std::ctime(&end_time)
              << "elapsed time on sobel: " << elapsed_seconds.count() << "s\n";
     return edge_image;

}


int main() {

    CImg<unsigned char> image("data.jpg");
    CImg <unsigned char> thresholded = thresholding(125, image);
    CImg <unsigned char> edged = sobelFiltering(image);
    CImgDisplay main_disp(image,"Original");
    CImgDisplay thresh_disp(edged,"edged");
    while (!main_disp.is_closed()) {
        main_disp.wait();
        thresh_disp.wait();
    }

    return 0;

}