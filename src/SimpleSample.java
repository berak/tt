
import java.util.*;
import org.opencv.core.*;
import org.opencv.face.*;
import org.opencv.imgproc.*;


class SimpleSample {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        Mat ocv = new Mat(300,300,0);
        FaceRecognizer fr = Face.createLBPHFaceRecognizer();
		System.out.println("fr: " + fr);

		FacemarkKazemi kaz;
        try {
			FacemarkKazemi kaz = FacemarkKazemi.create();
			System.out.println("kz: " + kz);
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        try {
			kaz.loadModel("no.dat");
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        System.exit(0); // to break out of the ant shell.
    }
}
