package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FlagsTest {

    private String filepath = "src/test/resources/testFlags";

    private User testUser;
    private Flags flags;

    @Before
    public void setUp() {
        flags = new FileFlags(filepath);
        testUser = new User("123456789");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void trueFlag() {
        updateFileContent("flag1:true");
        assertTrue(flags.getFlagValue("flag1", testUser));
    }

    @Test
    public void falseFlag() {
        updateFileContent("flag1:false");
        assertFalse(flags.getFlagValue("flag1", testUser));
    }

    @Test
    public void flagFlip() {
        // Set the flag to false
        updateFileContent("flag1:false");
        assertFalse(flags.getFlagValue("flag1", testUser));

        // Flip the falseFlag to true
        updateFileContent("flag1:true");

        // Flag should now be true
        assertTrue(flags.getFlagValue("flag1", testUser));
    }

    @Test
    public void flagNotInConfig() {
        // Flag file is empty
        updateFileContent("");

        // A missing flag should be interpreted as false.
        assertFalse(flags.getFlagValue("flag1", testUser));
    }

    /**
     * Test helper method to simulate updating the flag configuration file. Overwrites the file with {@param content}.
     */
    private void updateFileContent(String content) {
        try {
            Files.write(Paths.get(filepath), content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to file " + filepath);
        }
    }
}
