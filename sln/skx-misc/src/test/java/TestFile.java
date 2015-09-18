/*
 * Copyright (c) 2008-2014 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import com.skx.misc.File;
import com.skx.misc.IFile;


/**
* Created by vj on 7/2/2015.
*/
public class TestFile {
    private static final Log LOGGER = LogFactory.getLog(TestFile.class);
    @Test
    public void test() {
        LOGGER.info("test.............");
        IFile f=new File();
        Assert.assertTrue(f.getContent("fdy").endsWith("ssss"));

    }
}
