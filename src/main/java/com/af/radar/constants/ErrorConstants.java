package com.af.radar.constants;

/**
 * Created by AKilickaya
 */
public final class ErrorConstants {
    // 2 parameters(1. space image file path, 2.accuracy value) should be passed to run application
    public static final String ERR101 = "ERR101";

    // accuracy parameter should be greater than 0 or less than or equal to 1
    public static final String ERR102 = "ERR102";

    // accuracy parameter should be double
    public static final String ERR103 = "ERR103";

    // There is no valid align char and should be at least 1 'o' character
    public static final String ERR104 = "ERR104";

    // Error while getting partition Of Image
    public static final String ERR105 = "ERR105";

    // FileNotFoundException while reading file
    public static final String ERR106 = "ERR106";

    // Could not found Space image file with given path
    public static final String ERR107 = "ERR107";

    //Could not found Alien image file
    public static final String ERR108 = "ERR108";
}
