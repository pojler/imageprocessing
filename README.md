# Imageprocessing

## Description

Simple project comparing performance of pure **C++**, pure **Java** and their mix using **Java Native Interface** during
various tasks.

## Prerequisites

To make the C++ work you will need **MinGW** compiler as well as some tools and utilities.

### CImg library

You can download it from here: https://cimg.eu/download.html

Unzip contents of the library in the `/lib` directory of the project.

`\src\com\github\pojler\imageprocessing\lib`

### ImageMagick utility

Available at https://imagemagick.org/script/download.php. Processes .jpg files for CImg.

## Installation

Download the project or clone it with git client:

```
> git clone https://github.com/pojler/imageprocessing
```

## Compilation

### Java

Compile the ImageProcessor.java and Main.java classes in prefered IDE or inline method.

### C++

To compile the project navigate to project root directory and type following command.

```
g++ -std=c++11 -lgdi32 -o ip.exe ImageProcessing.cpp
```
