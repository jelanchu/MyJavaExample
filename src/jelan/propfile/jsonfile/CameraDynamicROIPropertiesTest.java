package jelan.propfile.jsonfile;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CameraDynamicROIPropertiesTest {
	private static final Logger logger = Logger.getLogger(CameraDynamicROIPropertiesTest.class); 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			String log4jConfPath = "log4j.properties";
			PropertyConfigurator.configure(log4jConfPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CameraDynamicROIProperties propHandler = new CameraDynamicROIProperties();
		String dyRoiFilename = null;
		try {
			dyRoiFilename = propHandler.readFile(1);
		} catch (Exception e) {
			logger.warn(e);
			if (dyRoiFilename != null) logger.warn("read dynamic roi property file failed! [ " + dyRoiFilename + " ]");
			logger.warn("Dynamic ROI properties are the default value in template file.");
		}
		CameraDynamicROI dynamicROIprop = propHandler.getROIProp(1, 0);
		logger.debug(String.format("Camera dynamic ROI props:: cameraId: [ %d ], dynamc roi props: [ %s ]", 1, dynamicROIprop.toJsonString()));
	}

}
