package jelan.httpclient;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class HttpResponseHandler {
	private static final Logger logger = Logger.getLogger(HttpResponseHandler.class);
	
	public void process(HttpResponse response) {
		// TODO Auto-generated method stub
		try {
			Header responseHeader = response.getFirstHeader("Response");
			if (responseHeader == null) {
				return;
			}
			String responString = responseHeader.getValue();
			if (responString == null) {
				return;
			}

			logger.debug("HTTP response data: [ " + responString + " ]");

			Gson gson = new Gson();
			HttpResponseDataObj[] responseDatas = gson.fromJson(responString, HttpResponseDataObj[].class);

			for (int i = 0; i < responseDatas.length; i++) {
				if (responseDatas[i] == null)
					continue;

				HttpResponseDataObj responseData = responseDatas[i];
				logger.debug("responseData.capturedTime: [ " + responseData.capturedTime + " ]");
				logger.debug("responseData.message: [ " + responseData.message + " ]");
			}
		} catch (Exception e) {
			logger.warn(e);
		} finally {

		}
	}
	
}
