#include <iostream>
#include "opencv2/opencv.hpp"

using namespace cv;
using namespace cv::ml;
using namespace std;
int main() {
    String original_path = "waveform.data";
    String dataname = "waveform";

    Ptr<TrainData> tdata2 = TrainData::loadFromCSV(original_path, 0);
    Mat responses(tdata2->getResponses().rows, 3, CV_32FC1, Scalar(0));
    for (int i = 0; i<tdata2->getResponses().rows; i++)
        responses.at<float>(i, static_cast<int>(tdata2->getResponses().at<float>(i, 0))) = 1;
    Ptr<TrainData> tdata = TrainData::create(tdata2->getSamples(), ml::ROW_SAMPLE, responses);

    CV_Assert(!tdata.empty());
    tdata->setTrainTestSplitRatio(0.8);

    RNG& rng = theRNG();
    rng.state = 1027401484159173092;
    Ptr<ml::ANN_MLP> x = ml::ANN_MLP::create();
    Mat_<int> layerSizes(1, 4);
    layerSizes(0, 0) = tdata->getNVars();
    layerSizes(0, 1) = 100;
    layerSizes(0, 2) = 100;
    layerSizes(0, 3) = tdata->getResponses().cols;
    x->setLayerSizes(layerSizes);
    x->setActivationFunction(ml::ANN_MLP::SIGMOID_SYM);
    x->setTrainMethod(ml::ANN_MLP::BACKPROP);
    x->setTermCriteria(TermCriteria(TermCriteria::COUNT, 10, 0.01));
    x->train(tdata, ml::ANN_MLP::NO_OUTPUT_SCALE);
    Ptr<ml::ANN_MLP> y = Algorithm::load<ANN_MLP>("waveform_backprop.yml");
    Mat testSamples = tdata->getTestSamples();
    Mat rx, ry, dst;
    x->predict(testSamples, rx);
    y->predict(testSamples, ry);

    for (int i=0; i<rx.rows; i++) {
    	Point p,q;
    	minMaxLoc(rx.row(i), 0,0,0,&p);
    	minMaxLoc(ry.row(i), 0,0,0,&p);
    	if (p.x != q.x) {
    		cout << i << rx.row(i) << " " << ry.row(i) << endl;
    	}
    }
    double n = cv::norm(rx, ry, NORM_INF);
    cout << "norm " << n << endl;

	return 0;
}
