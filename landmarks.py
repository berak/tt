import sys
sys.path.append('/home/travis/build/berak/tt/ocv/lib/python2.7/dist-packages/')

import cv2, numpy as np

img = cv2.imread("david2.jpg",0)
cas = cv2.CascadeClassifier("haarcascade_frontalface_alt2.xml")
faces = cas.detectMultiScale(img, 1.5, 5)
print("faces",faces)

print("testing LBF")
obj = cv2.face.createFacemarkLBF()
obj.loadModel("lbfmodel.yaml")
ok, landmarks = obj.fit(img, faces)
print ("landmarks LBF",ok, landmarks)

print("testing AAM")
obj = cv2.face.createFacemarkAAM()
obj.loadModel("aam.xml")
ok, landmarks = obj.fit(img, faces)
print ("landmarks AAM",ok, landmarks)

print("testing Kazemi")
obj = cv2.face.createFacemarkKazemi()
obj.loadModel("face_landmark_model.dat")
ok, landmarks = obj.fit(img, faces)
print ("landmarks Kazemi",ok, landmarks)
