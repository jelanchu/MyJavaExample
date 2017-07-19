package jelan.propfile.multiplefiles;

public class Constants {
	// proxy setting
	public static String HA_PROXY_IP = "172.16.22.183";
	public static int HA_PROXY_PORT = 8080;
	public static int HA_PROXY_SSL_PORT = 443;
	public static int HA_PROXY_REGISTER_TRY_COUNT = 10;
	public static int HA_PROXY_REGISTER_PERIODIC = (150 * 1000);
	
	public static String ENGINE_URL="/licenserecognize/";
	public static String EDGE_TEMP_FOLDER = "/tmp/lpredge"; 

	public static int FRAME_W_H_MINIMUM_LENGTH = 10;
	 
	// run multiple cameras 
	public static String RUN_INSTANCE_FOLDER = "run";
	public static String FFMPEG_CAPTURE_IMAGE_FOLDER = "captured";
	public static String FULL_IMAGE_TEMP_FOLDER_NAME = "temp";
	
	// camera default value
	public static int CAMERA_MINIMUM_FPS = 15;
	public static int CAMERA_MAXIMUM_FPS = 50;	
	
	public static int FFMPEG_CAPTURE_DEFAULT_FPS = 50;
    public static int FFMPEG_TIMEOUT_DEFAULT_US = 30000000;

    public static String PID_FOLDER = "pid";
    public static String TEMP_FOLDER = "temp";
    public static String LOG_FILE_FOLDER = "logs";

    public static String FFMPEG_PID_FILENAME = "ffmpeg.pid";
    
    public static String FFMPEG_PID_FILENAME_TEMPLATE = "@PID.ffmpegpid";
    public static String FFMPEG_DEFAULT_MEDIA_OPTION = "-q:v 1 -vf fps=fps=5,setpts='N/(5*TB)'";
    public static String FFMPEG_DEFAULT_OPTION = "-stimeout 30000000"; // 30 seconds
    public static int FFMPEG_RESPONSE_TIMEOUT = 300; // 5 minutes
    public static int FFMPEG_RESTART_NUMBER = 5;
    public static int FFMPEG_FPS = 5;
    
    public static String MAIN_ALIVE_SHARED_MEMORY_FILE = "/tmp/lpredge/mainalivestatus.dat";
    public static String HAPROXY_REGISTER_STATUS_SMF = "/tmp/lpredge/haproxyregisterstatus.dat";
    
    public static int QUEUE_MAX_SIZE = 3000; // fps = 5; 10 minutes, 5 * 60 * 10 = 3000
    public static int LOCAL_FILTER_QUEUE_TIMEOUT_SECONDS = 30;
    public static int IMAGE_ASHMAN_PERIODIC_MINUTES = 10;
    
    public static String OPENALPR_MODEL_NAME = "openalpr";
    public static String OPENCV_MOTION_MODEL_NAME = "opencvmotion";
    
    // property file name
    public static String ADVANCE_PROPERTIES_FILENAME = "advance.properties";
    public static String CONFIG_PROPERTIES_FILENAME = "config.properties";
    public static String CAMERA_PROPERTIES_FILENAME = "camera.properties";
    public static String FFMPEG_PROPERTIES_FILENAME = "ffmpeg.properties"; 
}
