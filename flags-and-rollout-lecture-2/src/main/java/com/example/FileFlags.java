package com.example;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A file based implementation of the flags interface. Flag values are stored in a configuration file.
 */
public class FileFlags implements Flags {

    private final String flagsFilePath;
    private Map<String, Boolean> flagStore;

    public FileFlags(String flagsFilePath) {
        this.flagsFilePath = flagsFilePath;
    }

    /**
     * Retrieves the value of a flag by the {@param flagName}.
     */
    public boolean getFlagValue(String flagName, User user) {
        readFlagsFromFile();
        return flagStore.get(flagName);
    }

    /**
     * Helper method to load the flag values from the flag configuration file.
     */
    private void readFlagsFromFile(){
        flagStore = new HashMap<>();
        List<String> lines;
        try {
            lines = Files.readLines(new File(flagsFilePath), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException("Unable to open file at " + flagsFilePath);
        }
        for (String line : lines) {
            // Each line is of the form:
            //  <flagName>:<flagValue>
            // e.g.
            //  MyFlag:true
            String[] split = line.split(":");
            flagStore.put(split[0], Boolean.valueOf(split[1]));
        }
    }
}
