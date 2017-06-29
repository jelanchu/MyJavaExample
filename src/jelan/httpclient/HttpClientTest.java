package jelan.httpclient;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HttpClientTest {
	private static final Logger logger = Logger.getLogger(HttpClientTest.class);
	private String testImageFilepath = "3plates-tw.jpg";
	
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
		//fail("Not yet implemented");
		testHttpClient();
	}
	public void testHttpClient() {
		
		try {
			String log4jConfPath = "log4j.properties";
			PropertyConfigurator.configure(log4jConfPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("-- Test tHttpClient --");
		try {
			logger.info(String.format("Init http request queue: max size [ %d ], trim mode [ %d ]", 10, 0));
			HttpRequestQueue<HttpRequestDataObj> hrq = new HttpRequestQueue<HttpRequestDataObj>(10, 0);
			HttpRequestWorker[] workers = new HttpRequestWorker[5];
			
			logger.info(String.format("Create http request worker: number [ %d ]", 5));			
			for (int i = 0; i < 5; i++) {
				HttpRequestWorker worker = new HttpRequestWorker(hrq);
				worker.start();
				workers[i] = worker;
			}
			
			logger.info(String.format("Create http request object: number [ %d ]", 20));
			int i = 0;
			while (true) {
				HttpRequestDataObj obj = new HttpRequestDataObj();
				obj.capturedTime = i;
				obj.imageFilename = testImageFilepath;
				hrq.add(obj);
				i++;
				Thread.sleep(100);
			}

//			for (int j = 0; j < 5; j++) {
//				try {
//					workers[j].join();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
}
