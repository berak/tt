import sys
print(sys.path)
sys.path.append('/home/travis/build/berak/tt/ocv/lib/python2.7/dist-packages/')
print(sys.path)

import cv2, numpy as np

img = cv2.imread("david2.jpg",0)
obj = cv2.face.createFacemarkLBF()
obj.loadModel("lbfmodel.yaml")
cas = cv2.CascadeClassifier("haarcascade_frontalface_alt2.xml")
faces = cas.detectMultiScale(img, 1.5, 5)
print("faces",faces)
ok, landmarks = obj.fit(img, faces)
print ("landmarks",ok, landmarks)
