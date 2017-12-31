
import java.util.*;
import org.opencv.core.*;
import org.opencv.imgproc.*;


import org.opencv.face.*;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;


class SimpleSample {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    public static void help(String cls){ ClassSpy.reflect(cls,"CLASS"); }
    public static void help(String cls,String item){ ClassSpy.reflect(cls,item); }

    public static void main(String[] args) {
        help("org.opencv.face.MACE", "ALL");
        MACE mace = MACE.create();
        mace.salt("12325454");
        ArrayList<Mat> imgs = new ArrayList<Mat>();
        for (int i=0; i<20; i++) {
            imgs.add(new Mat(64,64,CvType.CV_8U));
        }
        mace.train(imgs);
        Mat m = new Mat(64,64,CvType.CV_8U);
        boolean s = mace.same(m);
        System.exit(0); // to break out of the ant shell.
    }
}
