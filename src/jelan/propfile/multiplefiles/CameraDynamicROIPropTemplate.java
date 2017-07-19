package jelan.propfile.multiplefiles;

import java.io.IOException;
import java.util.Properties;

import jelan.utils.Tools;

//import com.ironyun.cityeyes.edge.sub.EdgeSubLogger;
//import com.ironyun.cityeyes.edge.utils.Tools;

public class CameraDynamicROIPropTemplate {
	// private static final Logger logger =
		// Logger.getLogger(CameraDynamicROIPropTemplate.class);

		// private static String name =
		// CameraDynamicROIPropTemplate.class.getSimpleName();

		public static String value_DirectionTopLeft = "topleft";
		public static String value_DirectionTopRight = "topright";
		public static String value_DirectionCenter = "center";
		public static String value_DirectionRight = "right";
		public static String value_DirectionLeft = "left";
		public static String value_CropModeNone = "none";
		public static String value_CropModeAuto = "auto";
		public static String value_CropModeManual = "manual";

		public static String key_DynamicRoiWidth = "DynamicRoiWidth";
		public static String key_DynamicRoiHeight = "DynamicRoiWeight";
		public static String key_DynamicRoiShift = "DynamicRoiShift";
		public static String key_ObjectMinWidth = "ObjectMinWidth";
		public static String key_ObjectMinHeight = "ObjectMinHeight";
		public static String key_BlurThreshold = "BlurThreshold";
		public static String key_DisplayWidth = "DisplayWidth";
		public static String key_Direction = "Direction"; // topleft, topright,
															// right, left, center
		public static String key_CropMode = "CropMode"; // none, auto, manual
		
		public static int dynamicRoiWidth = 200;
		public static int dynamicRoiHeight = 200;
		public static int dynamicRoiShift = 20;
		public static int objectMinWidth = 50;
		public static int objectMinHeight = 50;
		public static int blurThreshold = 120;
		public static int displayWidth = 400;
		public static String direction = "center";
		public static String cropMode = "auto";
		
		public static void readFile(String file) throws IOException {

			// logger.info("Dynamic ROI template property file specified, going to
			// use it [ " + file + " ]");
			Properties prop = Tools.loadProperties(file);

			if (prop.getProperty(key_DynamicRoiWidth) != null) {
				dynamicRoiWidth = Integer.valueOf(prop.getProperty(key_DynamicRoiWidth));
			}

			if (prop.getProperty(key_DynamicRoiHeight) != null) {
				dynamicRoiHeight = Integer.valueOf(prop.getProperty(key_DynamicRoiHeight));
			}

			if (prop.getProperty(key_DynamicRoiShift) != null) {
				dynamicRoiShift = Integer.valueOf(prop.getProperty(key_DynamicRoiShift));
			}

			if (prop.getProperty(key_ObjectMinWidth) != null) {
				objectMinWidth = Integer.valueOf(prop.getProperty(key_ObjectMinWidth));
			}

			if (prop.getProperty(key_ObjectMinHeight) != null) {
				objectMinHeight = Integer.valueOf(prop.getProperty(key_ObjectMinHeight));
			}

			if (prop.getProperty(key_BlurThreshold) != null) {
				blurThreshold = Integer.valueOf(prop.getProperty(key_BlurThreshold));
			}

			if (prop.getProperty(key_DisplayWidth) != null) {
				displayWidth = Integer.parseInt(prop.getProperty(key_DisplayWidth));
			}

			if (prop.getProperty(key_Direction) != null) {
				direction = prop.getProperty(key_Direction);
			}
			if (prop.getProperty(key_CropMode) != null) {
				cropMode = prop.getProperty(key_CropMode);
			}
			
		}
}
