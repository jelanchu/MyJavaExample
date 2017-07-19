package jelan.propfile.multiplefiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import jelan.utils.Tools;
//import com.google.gson.JsonParser;
//import com.ironyun.cityeyes.edge.datastruct.CameraROI;
//import com.ironyun.cityeyes.edge.model.opencvmotion.CameraDynamicROI;
//import com.ironyun.cityeyes.edge.properties.CameraProperties;
//import com.ironyun.cityeyes.edge.sub.EdgeSubLogger;
//import com.ironyun.cityeyes.edge.utils.Tools;

public class CameraProperties {
	private static String name = CameraProperties.class.getSimpleName();
	public int cameraId;
	public String rtsp;
	public boolean enable;
	public List<CameraROI> cameraRois;
	public int fps = 0;

	public CameraProperties() {
		this.cameraId = 0;
		this.enable = false;
		this.fps = 0;
	}

	public CameraProperties(int cameraId, String rtsp, boolean enable) {
		this.cameraId = cameraId;
		this.rtsp = rtsp;
		this.enable = enable;
	}

	public Object[] readFile(String file) {
		ArrayList<CameraProperties> cameraProps = new ArrayList<CameraProperties>();
		boolean result = readFile(cameraProps, file, ",");
		return (result == false) ? null : cameraProps.toArray();
	}

	public static boolean readFile(ArrayList<CameraProperties> cameraProps, String file, String regex) {
		Properties props = null;
		try {
			props = Tools.loadProperties(file);
			for (final String id : props.stringPropertyNames()) {
				CameraProperties cameraProp = new CameraProperties();
				cameraProp.cameraId = Integer.valueOf(id);
				String value = props.getProperty(id);

				cameraProp.rtsp = value.split(regex)[0];
				cameraProp.enable = Boolean.valueOf(value.split(regex)[1]);

				String cameraRoiArrayStr = null;
				if (value.split(regex).length > 2) {
					if (value.contains("[") && value.contains("]")) {
						cameraRoiArrayStr = value.substring(value.indexOf("["), value.indexOf("]") + 1);
					} else
						cameraRoiArrayStr = value.split(regex)[2];
					cameraProp.cameraRois = parseCameraRoiArrayString(cameraRoiArrayStr);
				}

				String tempValue = value.replace(cameraRoiArrayStr, "");
				if (tempValue.split(regex).length > 3) {
					int fps = Integer.valueOf(tempValue.split(regex)[3]);
					if (fps > 0)
						cameraProp.fps = fps;
					else
						cameraProp.fps = 15;
				} else {
					cameraProp.fps = 15;

				}
				cameraProps.add(cameraProp);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean writeFile(String filepath, CameraProperties cameraProp) {
		boolean result = false;
		FileWriter fw;
		try {
			File f = new File(filepath);
			if (f.exists()) {
				f.delete();
			}

			fw = new FileWriter(filepath, true);

			String line = String.format("%d=%s,%s,%s,%d", cameraProp.cameraId, cameraProp.rtsp,
					String.valueOf(cameraProp.enable), arrayListToJsonString(cameraProp.cameraRois), cameraProp.fps);

			fw.write(line);

			fw.close();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	private static List<CameraROI> parseCameraRoiArrayString(String cameraRoiArrayString) {
		List<CameraROI> cameraRois = new ArrayList<CameraROI>();

		String pattern = null;
		Pattern r = null;
		Matcher m = null;
		boolean isWrongSetting = false;
		try {
			//EdgeSubLogger.logInfo(name, "orginal roi ratio string: [ " + roiRatioString + " ]");
			pattern = "[0-1]*:[0-1]*:[0-1]*:[0-1]*";
			r = Pattern.compile(pattern);
			m = r.matcher(cameraRoiArrayString);
			if (m.find()) {
				CameraROI cameraRoi = new CameraROI();
				//EdgeSubLogger.logInfo(name, "camera ROI string: [ " + m.group(0) + " ]");
				cameraRoi.roiId = 0;
				cameraRoi.roi.xRatio = Float.valueOf(cameraRoiArrayString.split(":")[0]);
				cameraRoi.roi.yRatio = Float.valueOf(cameraRoiArrayString.split(":")[1]);
				cameraRoi.roi.widthRatio = Float.valueOf(cameraRoiArrayString.split(":")[2]);
				cameraRoi.roi.heightRatio = Float.valueOf(cameraRoiArrayString.split(":")[3]);
				cameraRois.add(cameraRoi);
			} else if (Tools.isJSONValid(cameraRoiArrayString)) {
				//EdgeSubLogger.logInfo(name, "camera ROI string: [ " + cameraRoiArrayString + " ]");
				JsonParser jsonParser = new JsonParser();
				JsonArray jsonArray = (JsonArray) jsonParser.parse(cameraRoiArrayString);
				Gson googleJson = new Gson();
				CameraROI[] cameraRoiArray = googleJson.fromJson(jsonArray, CameraROI[].class);
				cameraRois = Arrays.asList(cameraRoiArray);
			} else {
				//EdgeSubLogger.logWarn(name, String.format("Wrong camera ROI string format: [ %s ]", cameraRoiArrayString));
				isWrongSetting = true;
			}
			// if wrong roi setting, set one full roi area
			if (isWrongSetting == false) {
				for (CameraROI cameraRoi : cameraRois) {
					if (cameraRoi.roi.xRatio < 0 || cameraRoi.roi.xRatio > 1 || cameraRoi.roi.yRatio < 0 || cameraRoi.roi.yRatio > 1
							|| cameraRoi.roi.widthRatio < 0 || cameraRoi.roi.widthRatio > 1 || cameraRoi.roi.heightRatio < 0
							|| cameraRoi.roi.heightRatio > 1 || (cameraRoi.roi.xRatio + cameraRoi.roi.widthRatio > 1)
							|| ((cameraRoi.roi.yRatio + cameraRoi.roi.heightRatio > 1))) {
//						EdgeSubLogger.logWarn(name, String.format("Wrong ROI values: [ %.2f:%.2f:%.2f:%.2f ]",
//								cameraRoi.roi.xRatio, cameraRoi.roi.yRatio, cameraRoi.roi.widthRatio, cameraRoi.roi.heightRatio));
						isWrongSetting = true;
						break;
					}
				}
			}

		} catch (Exception e) {
			//EdgeSubLogger.logWarn(name, String.format("Exception: [ %s ]", e.getMessage()));
			isWrongSetting = true;
		}

		if (isWrongSetting) {
			cameraRois.clear();
			CameraROI cameraRoi = new CameraROI();
			cameraRois.add(cameraRoi);
			//EdgeSubLogger.logWarn(name, "So set one ROI area [ 0:0:1:1 ].");
		}

		return cameraRois;
	}

	private static String arrayListToJsonString(List<CameraROI> cameraRois) {
		Gson gson = new GsonBuilder().create();
		JsonArray ja = gson.toJsonTree(cameraRois).getAsJsonArray();
		return ja.toString();
	}

}
