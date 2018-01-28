# Radar

 The application is a radar that detects known aliens in given space image file.

## Getting Started
These instructions is to show how to build and run the Radar application on your local machine for development and testing purposes.

### Prerequisites
```
JDK 8
MAVEN
```

### Build
Go to project main directory then run below MAVEN command
```
mvn clean package
```

### Parameters:

The application takes 2 parameters as input.

* Space Image File Path
* Accuracy

**accuracy** is a double value which is greater than 0 or less than or equal to 1.
It is used for desired threshold accuracy value.

### Run
On the project main directory, run below command
``` java
java -jar target/radar-1.0.jar <<space_image_file path>> <<accuracy>>
```
#### Example
```
java -jar target/radar-1.0.jar /home/ali/Desktop/space.txt 0.85
```


## How it works:

The application will read the space image file from the given file path and also will read known Aliens from files.
The space image is extended in all directions(left, right, above, bottom) to detect edge cases.
The app will be taking partitions with the same width and height of each Alien from radar image and comparing both matrices.
It will count total matched characters and total matched alien characters('o') then calculate totalCharAccuracy and alienCharAccuracy.
Alien is detected when totalCharAccuracy and alienCharAccuracy greater than or equal to given accuracy value.
The application will print detected Alien info including coordinate from the extended space image, accuracy, alien type and partition of space image.
It will also print the detected Aliens and show them red colored on the extended space image to console.

## Output
Outputs of application for `0.85` the given threshold accuracy value

##### Detected Aliens
![Alt text](/screen_shots/detected_aliens.png)  

##### Detected Aliens are showed red colored on the extended image
![Alt text](/screen_shots/detected_aliens_on_space.png)   

