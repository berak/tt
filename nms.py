import sys
sys.path.append('/home/travis/build/berak/tt/ocv/lib/python2.7/dist-packages/')

import cv2, numpy as np

boxes = np.array([[1,2,3,4,5],[2,1,3,4,6],[4,3,2,1,2]],np.float32)
conf = np.array([.6, .5, .2], np.float32)
indices = cv2.dnn.NMSBoxesRotated(boxes, conf, .1, .1);
print("NMS", indices)
