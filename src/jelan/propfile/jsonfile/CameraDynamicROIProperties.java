package jelan.propfile.jsonfile;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import jelan.utils.Tools;

public class CameraDynamicROIProperties {
	// private static final Logger logger =
	// Logger.getLogger(CameraDynamicROIProperties.class);
	// private final static String name =
	// CameraDynamicROIProperties.class.getSimpleName();
	private final String CAMERA_DYNAMIC_ROI_FILENAME_TEMPLATE = "camera@CameraId_dynamicRoi.properties";
	private CameraDynamicROI[] cameraDynamicROIs = null;

	public String readFile(int cameraId) throws Exception {
		String filename = CAMERA_DYNAMIC_ROI_FILENAME_TEMPLATE.replace("@CameraId", String.valueOf(cameraId));
		if (cameraDynamicROIs != null && cameraDynamicROIs.length > 0)
			return filename;

		String jsonString = Tools.readFile(filename, Charset.defaultCharset());
		Gson gson = new Gson();
		cameraDynamicROIs = gson.fromJson(jsonString, CameraDynamicROI[].class);

		return filename;
	}

	public CameraDynamicROI getROIProp(int cameraId, int roiId) {
		CameraDynamicROI roi = null;

		if (cameraDynamicROIs == null || roiId >= cameraDynamicROIs.length) {
			roi = new CameraDynamicROI(roiId);
		} else {
			roi = cameraDynamicROIs[roiId];
		}

		return roi;
	}
}
