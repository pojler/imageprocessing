# imageprocessing

It's a simple project to compare c++ and java solutions on two image processing algorithms.

To compile c++ solution you will need:

CImg library, that you can download from here:
https://cimg.eu/download.html

put content of the .zip into:
> \src\com\github\pojler\imageprocessing\lib>

and download ImageMagick to enable .jpg files from:
https://imagemagick.org/script/download.php#windows

and you will need c++ compiler, personally recommend g++.

command to compile:
>  g++ ImageProcessing.cpp -std=c++11  -lgdi32 -o ip.exe
