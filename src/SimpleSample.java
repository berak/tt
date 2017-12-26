
import java.util.*;
import org.opencv.core.*;
import org.opencv.face.*;
import org.opencv.imgproc.*;


import org.opencv.bgsegm.*;
import org.opencv.bgsegm.Bgsegm.*;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;


class SimpleSample {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    public static void help(String cls){ ClassSpy.reflect(cls,"CLASS"); }
    public static void help(String cls,String item){ ClassSpy.reflect(cls,item); }

    public static void testCNT() {
        System.err.println("!! running CNT test ! ")
        BackgroundSubtractorCNT bgs = Bgsegm.createBackgroundSubtractorCNT();
        assertNotNull("could not create a CNT instance!", bgs);
	    Mat img = new Mat(300,300,CvType.CV_8U);
        Mat mask = new Mat();
        bgs.apply(img,mask);
        assertFalse("no mask created from CNT", mask.empty());
    }
    public static void testMOG() {
        System.err.println("!! running MOG test ! ")
        BackgroundSubtractorMOG bgs = Bgsegm.createBackgroundSubtractorMOG();
        assertNotNull("could not create a MOG instance!", bgs);
	    Mat img = new Mat(300,300,CvType.CV_8U);
        Mat mask = new Mat();
        bgs.apply(img,mask);
        assertFalse("no mask created from MOG", mask.empty());
    }
    public static void testGSOC() {
        System.err.println("!! running GSOC test ! ")
        BackgroundSubtractorGSOC bgs = Bgsegm.createBackgroundSubtractorGSOC();
        assertNotNull("could not create a GSOC instance!", bgs);
	    Mat img = new Mat(300,300,CvType.CV_8U);
        Mat mask = new Mat();
        bgs.apply(img,mask);
        System.err.println("GSOC mask " + mask);
        assertFalse("no mask created from GSOC", mask.empty());
    }
    public static void main(String[] args) {
    	testCNT();
    	testMOG();
    	testGSOC();
        System.exit(0); // to break out of the ant shell.
    }
}
