package jelan.httpclient;

public class Constant {
	public static String HTTP_SERVER_IP = "10.211.55.8";
	public static int HTTP_SERVER_PORT = 1234;
	public static String HTTP_SERVER_URL = "http://@ip:@port/test/".replace("@ip", HTTP_SERVER_IP).replace("@port", String.valueOf(HTTP_SERVER_PORT));
}
