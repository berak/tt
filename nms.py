import sys
sys.path.append('/home/travis/build/berak/tt/ocv/lib/python2.7/dist-packages/')

import cv2, numpy as np

#help(cv2.dnn.NMSBoxes)
#help(cv2.dnn.NMSBoxesRotated)

boxes = np.array([[[1,2,3,4],[2,1,3,4],[4,3,2,1]]],np.int32)
#boxes = [[1,2,3,4],[2,1,3,4],[4,3,2,1]]
conf = [.6, .5, .2]
#conf = np.array([.6, .5, .2],np.float32)
#try:
indices = cv2.dnn.NMSBoxes(boxes, conf, .1, .1)
print("NMS", indices)
#except:
#    print("Unexpected error:", sys.exc_info())


rboxes = np.array([[[1,2,3,4,5],[2,1,3,4,6],[4,3,2,1,2]]],np.float32)
rboxes = [[1,2,3,4,5],[2,1,3,4,6],[4,3,2,1,2]]
#rboxes = [(1,2,3,4,5),(2,1,3,4,6),(4,3,2,1,2)]
conf = [.6, .5, .2]
try:
    indices = cv2.dnn.NMSBoxesRotated(rboxes, conf, .1, .1)
    print("NMSRot", indices)
except:
    print("Unexpected error:", sys.exc_info())


