package jelan.propfile.multiplefiles;

import java.io.IOException;
import java.util.Properties;

import jelan.utils.Tools;

//import com.ironyun.cityeyes.edge.sub.EdgeSubLogger;
//import com.ironyun.cityeyes.edge.utils.Constants;
//import com.ironyun.cityeyes.edge.utils.Tools;

public class ModelTemplate {
	private static String key_EdgeDetectorName = "EdgeDetectorName";
	private static String key_DoFindNext = "DoFindNext";
	public static String key_EnableCropPreview = "EnableCropPreview";

	public static String default_EdgeDetectorName = Constants.OPENALPR_MODEL_NAME;

	public static String EdgeDetectorName = default_EdgeDetectorName; //opencvmotion
	public static boolean DoFindNext = false;
	public static boolean EnableCropPreview = false;

	
	public static void readFile(String file) throws IOException {

		Properties prop = Tools.loadProperties(file);

		if (prop.getProperty(key_EdgeDetectorName) != null) {
			EdgeDetectorName = prop.getProperty(key_EdgeDetectorName, default_EdgeDetectorName);
		}

		if (prop.getProperty(key_DoFindNext) != null) {
			DoFindNext = Boolean.valueOf(prop.getProperty(key_DoFindNext));
		}
		
		if (prop.getProperty(key_EnableCropPreview) != null) {
			EnableCropPreview = Boolean.valueOf(prop.getProperty(key_EnableCropPreview));
		}
	}
}
