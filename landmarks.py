import cv2, numpy as np

img = cv2.imread("david2.jpg",0)
obj = cv2.face.createFacemarkLBF()
obj.loadModel("lbfmodel.yaml")
cas = cv2.CascadeClassifier("haarcascade_frontalface_alt2.xml")
faces = face_cascade.detectMultiScale(img, 1.5, 5)
ok, landmarks = obj.fit(img, faces)
print ok, landmarks
