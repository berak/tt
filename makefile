all:
	$(CXX) main.cpp -Iocv/include -Locv/lib -lopencv_core -o main
