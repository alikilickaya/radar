package com.af.radar.utils;

import com.af.radar.constants.ErrorConstants;
import com.af.radar.exceptions.RadarException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by AKilickaya
 */
public final class ReaderUtils {
    private static final Logger LOGGER = Logger.getLogger(ReaderUtils.class.getName());

    private ReaderUtils() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Reads file from given path and converting it to a String list
     *
     * @param path file path
     * @return String list
     * @throws RadarException {@link ErrorConstants#ERR106}
     */
    public static final List<String> convertFileToList(String path) throws RadarException {
        File file = new File(path);
        return readFile(file);
    }

    /**
     * Reads the given File
     *
     * @param file
     * @return
     * @throws RadarException {@link ErrorConstants#ERR106}
     */
    private static final List<String> readFile(File file) throws RadarException {
        List<String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(line);
            }

            return list;
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new RadarException(ErrorConstants.ERR106, "FileNotFoundException while reading file");
        }
    }

    /**
     * Reads file from resources and converting it to a String list
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static final List<String> convertResourceFileToList(String fileName) throws RadarException {

        try (InputStream is = ReaderUtils.class.getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            List<String> list = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }

            return list;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new RadarException(ErrorConstants.ERR106, "FileNotFoundException while reading file");
        }
    }
}
