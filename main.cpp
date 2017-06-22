#include <iostream>
#include "opencv2/opencv.hpp"

using namespace cv;

int main() {
	std::cerr << Mat::eye(3,3,CV_32F) << std::endl;
	return 0;
}
