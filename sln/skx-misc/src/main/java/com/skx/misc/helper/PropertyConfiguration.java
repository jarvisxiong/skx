package com.skx.misc.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 从Properties 文件（列表）中获取属性配置并缓存起来
 *
 * @author koqiui
 * @date 2014-08-19
 */
public class PropertyConfiguration {
    private static final String fileSuffix = ".properties";
    private static final String defaultCharsetName = "UTF-8";
    //
    private final Log logger = LogFactory.getLog(PropertyConfiguration.class);

    private String[] parseFilenames(String fileNamesStr) {
        List<String> fileNameList = new ArrayList<String>();
        String[] fileNames = fileNamesStr != null && fileNamesStr.trim() != "" ? fileNamesStr.split(",") : null;
        if (fileNames != null) {
            for (int i = 0; i < fileNames.length; i++) {
                String fileName = fileNames[i].trim();
                if (!fileName.endsWith(fileSuffix)) {
                    fileName += fileSuffix;
                }
                if (!fileNameList.contains(fileName)) {
                    fileNameList.add(fileName);
                }
            }
        }
        return fileNameList.toArray(new String[fileNameList.size()]);
    }

    private Map<String, String> innerMap = new ConcurrentHashMap<String, String>();

    private ReentrantLock XLock = new ReentrantLock();

    @SuppressWarnings("rawtypes")
    private void config(String[] fileNames, String charsetName) {
        XLock.lock();
        //
        innerMap.clear();
        if (charsetName == null) {
            charsetName = defaultCharsetName;
        }
        for (int i = 0, j = fileNames.length; i < j; i++) {
            try {
                Class refClass = PropertyConfiguration.class;
                String fileName = fileNames[i];
                InputStream input = null;
                if ((input = refClass.getResourceAsStream(fileName)) == null) {
                    ClassLoader clsLoader = refClass.getClassLoader();
                    while (clsLoader != null && (input = clsLoader.getResourceAsStream(fileName)) == null) {
                        clsLoader = clsLoader.getParent();
                    }
                    if (clsLoader == null) {
                        continue;
                    }
                }
                if (input != null) {
                    Properties properties = new Properties();
                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(input, charsetName));
                    properties.load(inputReader);
                    Enumeration<Object> keys = properties.keys();
                    while (keys.hasMoreElements()) {
                        String key = (String) keys.nextElement();
                        String value = properties.getProperty(key);
                        innerMap.put(key, value);
                    }
                    logger.debug("finished reading from file \"" + fileName + "\"");
                }
            } catch (IOException e) {
                logger.error(e);
            }
        }
        XLock.unlock();
    }

    public PropertyConfiguration(String[] fileNames, String charsetName) {
        this.config(fileNames, charsetName);
    }

    public PropertyConfiguration(String[] fileNames) {
        this(fileNames, null);
    }

    public PropertyConfiguration(String fileNamesStr, String charsetName) {
        String[] fileNames = parseFilenames(fileNamesStr);
        this.config(fileNames, charsetName);
    }

    public PropertyConfiguration(String fileNamesStr) {
        this(fileNamesStr, null);
    }

    public int size() {
        return this.innerMap.size();
    }

    public Set<String> keySet() {
        return this.innerMap.keySet();
    }

    public String get(String key) {
        return this.innerMap.get(key);
    }

    public String put(String key, String value) {
        return this.innerMap.put(key, value);
    }

    public boolean containsKey(String key) {
        return this.innerMap.containsKey(key);
    }

    public boolean containsValue(String value) {
        return this.innerMap.containsValue(value);
    }

    public String toString() {
        return this.innerMap.toString();
    }
}
