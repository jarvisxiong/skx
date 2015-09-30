package com;

import com.skx.exercise.interception.Client;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by vj on 7/2/2015.
 */
public class TestFile {
    private static final Log logger = LogFactory.getLog(TestFile.class);

    @Test
    public void pass() {
        logger.info("test.............");
        Assert.assertTrue(true);
    }

    @Test
    public void fail() {
        logger.info("test.............");
        Assert.assertTrue(false);
    }

    @Test
    public void testLog4j() {
        logger.debug("test debug");
        logger.info("test info");
        logger.warn("test warn");
        logger.error("test error");
       // Assert.assertTrue(false);
    }

    @Test
    public void testClient() {
        Client.main(null);
    }
}
