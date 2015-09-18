
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
}
