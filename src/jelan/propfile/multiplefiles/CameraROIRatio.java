package jelan.propfile.multiplefiles;

public class CameraROIRatio {
	
		public float xRatio;
		public float yRatio;
		public float widthRatio;
		public float heightRatio;
		
		public CameraROIRatio () {
			this.xRatio = 0;
			this.yRatio = 0;
			this.widthRatio = 1;
			this.heightRatio = 1;
		}
		public CameraROIRatio (float xRatio, float yRatio, float widthRatio, float heightRatio) {
			this.xRatio = xRatio;
			this.yRatio = yRatio;
			this.widthRatio = widthRatio;
			this.heightRatio = heightRatio;
		}
		public CameraROIRatio (int id, float xRatio, float yRatio, float widthRatio, float heightRatio) {
			this.xRatio = xRatio;
			this.yRatio = yRatio;
			this.widthRatio = widthRatio;
			this.heightRatio = heightRatio;
		}
		@Override
		public String toString() {
			String roiRatioString = String.format("%f:%f:%f:%f", xRatio, yRatio, widthRatio, heightRatio);
			return roiRatioString;
		}
	
}
