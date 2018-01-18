package org.opencv.test.bgsegm;

import org.opencv.bgsegm.*;
import org.opencv.bgsegm.Bgsegm.*;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;


public class SimpleSample {
    Mat img;

    protected static void setUp() {
        img = new Mat(300,300,CvType.CV_8U);
    }

    public static void testCNT() {
        BackgroundSubtractorCNT bgs = Bgsegm.createBackgroundSubtractorCNT();
        if(bgs == null) throw new Exception("could not create a CNT instance!");
        Mat mask = new Mat();
        bgs.apply(img,mask);
        if (mask.empty()) throw new Exception("no mask created from CNT");
    }
    public static void testMOG() {
        BackgroundSubtractorMOG bgs = Bgsegm.createBackgroundSubtractorMOG();
        if(bgs == null) throw new Exception("could not create a MOG instance!");
        Mat mask = new Mat();
        bgs.apply(img,mask);
        if (mask.empty()) throw new Exception("no mask created from MOG");
    }
    public static void testGSOC() {
        BackgroundSubtractorGSOC bgs = Bgsegm.createBackgroundSubtractorGSOC();
        if(bgs == null) throw new Exception("could not create a GSOC instance!");
        Mat mask = new Mat();
        bgs.apply(img,mask);
        if (mask.empty()) throw new Exception("no mask created from GSOC");
    }

    public static void main(String args[]) {
        setUp();
        testCNT();
        testMOG();
        testGSOC();
    }
}
