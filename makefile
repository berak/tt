all:
	$(CXX) main.cpp -Iocv/include -Locv/lib -lopencv_core -lrt -lz -lpthread -ldl -o main
