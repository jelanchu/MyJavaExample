package jelan.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClient {
	
	public HttpResponse post(String url, HttpEntity reqEntity) throws Exception {
		HttpResponse response = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(2000).build();
		httppost.setConfig(requestConfig);
		
		httppost.setEntity(reqEntity);
		try {
			response = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			response = null;
		} catch (IOException e) {
			e.printStackTrace();
			response = null;
		} finally {
			httpclient.close();
		}
		return response;
	}
}
