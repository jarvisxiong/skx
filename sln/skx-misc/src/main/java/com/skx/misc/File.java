package com.skx.misc;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Created by vj on 7/2/2015.
 */
public class File implements IFile {
    private static final Log LOGGER = LogFactory.getLog(File.class);

    public String getContent(String fileName){
        return fileName+"ssss";
    }
    public File() {
    }
}

