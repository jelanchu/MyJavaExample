package jelan.propfile.multiplefiles;

import com.google.gson.Gson;

public class CameraDynamicROIProp {
	private transient String TEMPLATE_FILE = "dynamicRoi.template";

	public int DynamicRoiWidth = 200;
	public int DynamicRoiHeight = 200;
	public int DynamicRoiShift = 20;
	public int ObjectMinWidth = 50;
	public int ObjectMinHeight = 50;
	public int BlurThreshold = 120;
	public int DisplayWidth = 400;
	public String Direction = "center";
	public String CropMode = "auto";


	public CameraDynamicROIProp() {
		loadProps(TEMPLATE_FILE);
	}

	public String toJsonString() {
		Gson gson = new Gson();
		return String.format(gson.toJson(this));
	}

	private void loadProps(String templateFile) {
		try {
			CameraDynamicROIPropTemplate.readFile(templateFile);
			DynamicRoiWidth = CameraDynamicROIPropTemplate.dynamicRoiWidth;
			DynamicRoiHeight = CameraDynamicROIPropTemplate.dynamicRoiHeight;
			DynamicRoiShift = CameraDynamicROIPropTemplate.dynamicRoiShift;
			ObjectMinWidth = CameraDynamicROIPropTemplate.objectMinWidth;
			ObjectMinHeight = CameraDynamicROIPropTemplate.objectMinHeight;
			BlurThreshold = CameraDynamicROIPropTemplate.blurThreshold;
			DisplayWidth = CameraDynamicROIPropTemplate.displayWidth;
			Direction = CameraDynamicROIPropTemplate.direction;
			CropMode = CameraDynamicROIPropTemplate.cropMode;
		} catch (Exception e) {

		}
	}
}
