
import java.util.*;
import org.opencv.core.*;
import org.opencv.face.*;
import org.opencv.imgproc.*;


class SimpleSample {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    public static void help(String cls){ ClassSpy.reflect(cls,"CLASS"); }
    public static void help(String cls,String item){ ClassSpy.reflect(cls,item); }

    public static void main(String[] args) {
        Mat ocv = new Mat(300,300,0);
        FaceRecognizer fr = LBPHFaceRecognizer.create();
		System.out.println("fr: " + fr);

		help("org.opencv.face.FacemarkKazemi", "ALL");
		System.out.println("kz: 1");
		FacemarkKazemi kaz;
        try {
			kaz = FacemarkKazemi.create();
			System.out.println("kz: " + kaz);
        } catch (Exception e) {
            System.out.println(e);
        }
		System.out.println("kz: 2");
        try {
			kaz.loadModel("no.dat");
        } catch (Exception e) {
            System.out.println(e);
        }

		System.out.println("kz: 3");
        try {
	        //List<MatOfPoint2f> contours = new ArrayList<MatOfPoint2f>();
	        Mat contours = new Mat();
	        MatOfRect rects = new MatOfRect();
			kaz.fit(ocv,rects,contours);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.exit(0); // to break out of the ant shell.
    }
}
