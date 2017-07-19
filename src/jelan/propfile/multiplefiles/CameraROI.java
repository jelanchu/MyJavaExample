package jelan.propfile.multiplefiles;

public class CameraROI {
		public int roiId;
		public CameraROIRatio roi;
		
		public CameraROI () {
			roiId = 0;
			roi = new CameraROIRatio();
		}
		@Override
		public String toString() {
			String str = String.format("roiId: [ %d ], roi: [ %s ]", roiId, roi.toString());
			return str;
		}
	
}
