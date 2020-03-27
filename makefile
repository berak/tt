all:
	$(CXX) crnn.cpp -Iocv/include -Locv/lib -Locv/share/OpenCV/3rdparty/lib -lopencv_dnn -lopencv_core -lopencv_imgproc -lopencv_imgcodecs -ljpeg -lpng -llibwebp -lrt -ldl -lz -lpthread -o crnn

