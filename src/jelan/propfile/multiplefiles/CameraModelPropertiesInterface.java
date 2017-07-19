package jelan.propfile.multiplefiles;

public interface CameraModelPropertiesInterface {
	public String EdgeDetectorName = ModelTemplate.default_EdgeDetectorName; //opencvmotion
	public boolean DoFindNext = false;
	public boolean EnableCropPreview = false;
	public CameraDynamicROI[] dynamicRois = null;
	void readProps(String filepath);
}
