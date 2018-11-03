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
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.MatOfInt;
//import org.opencv.test.OpenCVTestCase;

public class SimpleSample {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static Mat vector_RotatedRect_to_Mat(List<RotatedRect> rs) {
        Mat res;
        int count = (rs != null) ? rs.size() : 0;
        if (count > 0) {
//            res = new Mat(count, 1, CvType.CV_32FC5);
            res = new Mat(count, 1, 37);
            float[] buff = new float[5 * count];
            for (int i = 0; i < count; i++) {
                RotatedRect r = rs.get(i);
                buff[5 * i] = (float)r.center.x;
                buff[5 * i + 1] = (float)r.center.y;
                buff[5 * i + 2] = (float)r.size.width;
                buff[5 * i + 3] = (float)r.size.height;
                buff[5 * i + 4] = (float)r.angle;
            }
            res.put(0, 0, buff);
        } else {
            res = new Mat();
        }
        return res;
    }

    public static void Mat_to_vector_RotatedRect(Mat m, List<RotatedRect> rs) {
        if (rs == null)
            throw new java.lang.IllegalArgumentException("rs == null");
        int count = m.rows();
//        if (CvType.CV_32FC5 != m.type() || m.cols() != 1)
        if (37 != m.type() || m.cols() != 1)
            throw new java.lang.IllegalArgumentException(
                    "CvType.CV_32FC5 != m.type() ||  m.rows()!=1\n" + m);

        rs.clear();
        float[] buff = new float[5 * count];
        m.get(0, 0, buff);
        for (int i = 0; i < count; i++) {
            rs.add(new RotatedRect(new Point(buff[5 * i], buff[5 * i + 1]), new Size(buff[5 * i + 2], buff[5 * i + 3]), buff[5 * i + 3]));
        }
    }

    public static void assertMatEqual(Mat m1, Mat m2) {
    }

    public static void testVector_RotatedRect_to_Mat() {
        List<RotatedRect> rectangles = new ArrayList<RotatedRect>();
        rectangles.add(new RotatedRect(new Point(2, 2), new Size(5, 2), 7));
        rectangles.add(new RotatedRect(new Point(0, 0), new Size(6, 4), 3));

        Mat dst = vector_RotatedRect_to_Mat(rectangles);

        Mat truth = new Mat(2, 1, CvType.CV_32FC5);
        truth.put(0, 0, 2, 2, 5, 2, 7,
                        0, 0, 6, 4, 3);
        assertMatEqual(truth, dst);
    }


    public static void testMat_to_vector_RotatedRect() {
        Mat src = new Mat(2, 1, CvType.CV_32FC5);
        src.put(0, 0, 2, 2, 5, 2, 7,
                      0, 6, 4, 1, 3);
        List<RotatedRect> rectangles = new ArrayList<RotatedRect>();

        Mat_to_vector_RotatedRect(src, rectangles);
        List<RotatedRect> truth = new ArrayList<RotatedRect>();
        truth.add(new RotatedRect(new Point(2, 2), new Size(5, 2), 7));
        truth.add(new RotatedRect(new Point(0, 6), new Size(4, 1), 3));
        assertListRotatedRectEquals(truth, rectangles);
    }

    public static void assertListRotatedRectEquals(List<RotatedRect> list1, List<RotatedRect> list2) {
        if (list1.size() != list2.size()) {
            throw new UnsupportedOperationException();
        }

        for (int i = 0; i < list1.size(); i++)
            assertRotatedRectEquals(list1.get(i), list2.get(i));
    }

    public static void assertEquals(String msg, double a, double b) {
        if (a!=b)
            System.err.println(msg);
    }
    public static void assertEquals(String msg, float a, float b) {
        if (a!=b)
            System.err.println(msg);
    }

    public static void assertRotatedRectEquals(RotatedRect expected, RotatedRect actual) {
        String msg = "expected:<" + expected + "> but was:<" + actual + ">";
        assertEquals(msg, expected.center.x, actual.center.x);
        assertEquals(msg, expected.center.y, actual.center.y);
        assertEquals(msg, expected.size.width, actual.size.width);
        assertEquals(msg, expected.size.height, actual.size.height);
        assertEquals(msg, expected.angle, actual.angle);
    }

    public static void main(String[] args) {
        RotatedRect a = new RotatedRect(new Point(1,2), new Size(3,4), 5.0);
        RotatedRect b = new RotatedRect(new Point(2,1), new Size(3,4), 6.0);
        RotatedRect c = new RotatedRect(new Point(4,3), new Size(2,1), 2.0);
        MatOfRotatedRect rr = new MatOfRotatedRect(a,b,c);
        MatOfFloat scores = new MatOfFloat(.6f, .5f, .2f);
        MatOfInt indices = new MatOfInt();
        System.out.println(rr.size() + " " + scores.size());
        Mat rr2 = new Mat(3,1,37);
        rr2.put(0,0, 1,2,3,4,5, 6,7,8,9,10);
        rr2.put(2,0, 11,12,13,14,15);
        MatOfRotatedRect rr3 = new MatOfRotatedRect(rr2);
        System.out.println(rr3.dump());

        Dnn.NMSBoxesRotated(rr,scores,.1f,.1f, indices);
        System.out.println(indices);
        System.out.println(indices.dump());
        testMat_to_vector_RotatedRect();
        testVector_RotatedRect_to_Mat();
    }
}
