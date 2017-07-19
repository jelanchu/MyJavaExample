package jelan.propfile.multiplefiles;

import com.google.gson.Gson;

public class CameraDynamicROI {
	public int roiId;
	public CameraDynamicROIProp dynamicRoi;
	
	public CameraDynamicROI (int roiId) {
		this.roiId = roiId;
		this.dynamicRoi = new CameraDynamicROIProp();
	}
	public String toJsonString() {
		Gson gson = new Gson();
		return String.format(gson.toJson(this));
	}
}
