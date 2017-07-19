package jelan.propfile.multiplefiles;

import java.io.IOException;
import java.nio.charset.Charset;

import com.google.gson.Gson;

import jelan.utils.Tools;
//import com.ironyun.cityeyes.edge.model.opencvmotion.CameraDynamicROI;
//import com.ironyun.cityeyes.edge.utils.Tools;

public class _CameraModelProperties {
	private transient final String CAMERA_MODEL_FILENAME_TEMPLATE = "camera@CameraId_model.properties";
	private transient final String MODEL_TEMPLATE_FILENAME = "model.template";
	private transient static _CameraModelProperties instance = null;
	
	public String EdgeDetectorName = ModelTemplate.default_EdgeDetectorName; //opencvmotion
	public boolean DoFindNext = false;
	public boolean EnableCropPreview = false;
	public CameraDynamicROI[] dynamicRois = null;
	
	public _CameraModelProperties getInstance(int cameraId) {
		if (instance == null) {
			try {
				readFile(cameraId);
			} catch (Exception e) {
				instance = new _CameraModelProperties();
				try {
					ModelTemplate.readFile(MODEL_TEMPLATE_FILENAME);
					instance.EdgeDetectorName = ModelTemplate.EdgeDetectorName;
					instance.DoFindNext = ModelTemplate.DoFindNext;
					instance.EnableCropPreview = ModelTemplate.EnableCropPreview;
				} catch (IOException ioe) {
					
				}
			}
		}
		return instance;
	}
	
	public String readFile(int cameraId) throws Exception {
		String filename = CAMERA_MODEL_FILENAME_TEMPLATE.replace("@CameraId", String.valueOf(cameraId));
		if (instance != null && dynamicRois.length > 0)
			return filename;

		String jsonString = Tools.readFile(filename, Charset.defaultCharset());
		Gson gson = new Gson();
		instance = gson.fromJson(jsonString, _CameraModelProperties.class);

		return filename;
	}

	public CameraDynamicROI getROIProp(int cameraId, int roiId) {
		CameraDynamicROI roi = null;

		if (instance == null || dynamicRois == null || roiId >= dynamicRois.length) {
			roi = new CameraDynamicROI(roiId);
		} else {
			roi = dynamicRois[roiId];
		}

		return roi;
	}
	
	public String toJsonString() {
		Gson gson = new Gson();
		return String.format(gson.toJson(this));
	}
}
