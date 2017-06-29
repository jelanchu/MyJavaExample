package jelan.httpclient;

import java.io.File;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import com.google.gson.Gson;

public class HttpRequestDataObj {
	public String imageFilename;
	public long capturedTime;
	
	public StringBody getStrBody() {
		Gson gson = new Gson();
		StringBody headerStrBody = new StringBody(gson.toJson(this), ContentType.TEXT_PLAIN);
		return headerStrBody;
	}
	
	public FileBody getFileBody() {
		FileBody body = new FileBody(new File(imageFilename));
		return body;
	}
}
