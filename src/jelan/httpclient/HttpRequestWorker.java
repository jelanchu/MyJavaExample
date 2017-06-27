package jelan.httpclient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Stopwatch;

public class HttpRequestWorker extends Thread {
	private static final Logger logger = Logger.getLogger(HttpRequestWorker.class);
	private HttpRequestQueue queue = null;
	private boolean isActive = true;
	private int numObjsPerSecond = 2;
	
	public HttpRequestWorker() {
		this.queue = new HttpRequestQueue();
	}

	public void run() {
		while (isActive) {
			ArrayList<Object> requests = new ArrayList<Object>();
			try {
				long remainingTime = 1000;
				while (remainingTime > 0 && requests.size() < numObjsPerSecond) {
					Stopwatch watch = Stopwatch.createStarted();
					Object request = queue.poll(remainingTime, TimeUnit.MILLISECONDS);
					watch.stop();
					remainingTime = remainingTime - watch.elapsed(TimeUnit.MILLISECONDS);
					if (request != null) {
						requests.add(request);
					}
				}
				
				if (requests.size() > 0) {
					postMulitpleImages(requests);
				}

			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				// listener.clearHttpRequestResource(requests);
				requests = null;
			}
		}

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String postMulitpleImages(ArrayList<Object> requests) throws ClientProtocolException, IOException {
		HttpResponse response = null;

		String results = null;

		long beginMS = 0;
		long endMS = 0;
		try {
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();

			for (int i = 0; i < requests.size(); i++) {
				builder.addPart("BodyInfo", ((HttpRequestDataObj)requests.get(i)).getStrBody());

				String filename = ((HttpRequestDataObj)requests.get(i)).imageFilename;
				FileBody body = ((HttpRequestDataObj)requests.get(i)).getFileBody();
				builder.addPart(filename, body);
			}

			HttpEntity reqEntity = builder.build();

			response = new HttpClient().post(Config.HTTP_SERVER_URL, reqEntity);

			if (response != null) {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					//listener.handleHttpResponse(response);
				} else {
					logger.warn("Response HTTP code: [ " + response.getStatusLine().getStatusCode() + " ]");

				}
				if (response.getEntity() != null) {
					EntityUtils.consume(response.getEntity());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e.toString());
		}

		return results;
	}
}