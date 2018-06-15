import org.opencv.core.*;
import org.opencv.face.*;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;
import java.util.*;


public class SimpleSample {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        /*if (args.length < 3) {
            System.out.println("use: java Facemark [image file] [cascade file] [model file]");
            return;
        }*/
        // read the image
        Mat img = Imgcodecs.imread("david2.jpg");

        // setup face detection
        CascadeClassifier cascade = new CascadeClassifier("haarcascade_frontalface_alt2.xml");
        MatOfRect faces = new MatOfRect();
        // detect faces
        cascade.detectMultiScale(img, faces);

        // setup landmarks detector
        // Facemark fm = Face.createFacemarkKazemi();
       // fm.loadModel("C:\\data\\mdl\\face_landmark_model.dat");
        Facemark fm = Face.createFacemarkLBF();
        fm.loadModel("lbfmodel.yaml");

        // fit landmarks for each found face
        ArrayList<MatOfPoint2f> landmarks = new ArrayList<MatOfPoint2f>();
        fm.fit(img, faces, landmarks);

        // draw them
        for (int i=0; i<landmarks.size(); i++) {
            MatOfPoint2f lm = landmarks.get(i);
            for (int j=0; j<lm.rows(); j++) {
                double [] dp = lm.get(j,0);
                Point p = new Point(dp[0], dp[1]);
                Imgproc.circle(img,p,2,new Scalar(222),1);
            }
        }
        // save result
        Imgcodecs.imwrite("landmarks.jpg",img);
    }
}
