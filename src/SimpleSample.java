import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.*;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.dnn.*;
import org.opencv.dnn.Dnn;
//import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.MatOfInt;
//import org.opencv.test.OpenCVTestCase;

public class SimpleSample {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    public static void main(String[] args) {
        RotatedRect a = new RotatedRect(new Point(1,2), new Size(3,4), 5.0);
        RotatedRect b = new RotatedRect(new Point(2,1), new Size(3,4), 6.0);
        RotatedRect c = new RotatedRect(new Point(4,3), new Size(2,1), 2.0);
        MatOfRotatedRect rr = new MatOfRotatedRect(a,b,c);
        MatOfFloat scores = new MatOfFloat(.6f, .5f, .2f);
        MatOfInt indices = new MatOfInt();
        System.out.println(rr.size() + " " + scores.size());
        Dnn.NMSBoxesRotated(rr,scores,.1f,.1f, indices);
        System.out.println(indices);
    }
}
