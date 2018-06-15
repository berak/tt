all:
	$(CXX) main.cpp -Iocv/include -Locv/lib -Locv//share/OpenCV/3rdparty/lib -lopencv_face -lopencv_core -lippicv -lrt -lz -lpthread -ldl -o main
