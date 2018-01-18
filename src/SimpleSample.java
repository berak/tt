package org.opencv.test.bgsegm;

import org.opencv.bgsegm.*;
import org.opencv.bgsegm.Bgsegm.*;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;


public class SimpleSample {
    static Mat img = new Mat(300,300,CvType.CV_8U);


    public static void testCNT() throws Exception {
        BackgroundSubtractorCNT bgs = Bgsegm.createBackgroundSubtractorCNT();
        if(bgs == null) throw new Exception("could not create a CNT instance!");
        Mat mask = new Mat();
        bgs.apply(img,mask);
        if (mask.empty()) throw new Exception("no mask created from CNT");
    }
    public static void testMOG() throws Exception {
        BackgroundSubtractorMOG bgs = Bgsegm.createBackgroundSubtractorMOG();
        if(bgs == null) throw new Exception("could not create a MOG instance!");
        Mat mask = new Mat();
        bgs.apply(img,mask);
        if (mask.empty()) throw new Exception("no mask created from MOG");
    }
    public static void testGSOC() throws Exception {
        BackgroundSubtractorGSOC bgs = Bgsegm.createBackgroundSubtractorGSOC();
        if(bgs == null) throw new Exception("could not create a GSOC instance!");
        Mat mask = new Mat();
        bgs.apply(img,mask);
        if (mask.empty()) throw new Exception("no mask created from GSOC");
    }

    public static void main(String args[]) {
        testCNT();
        testMOG();
        testGSOC();
    }
}
