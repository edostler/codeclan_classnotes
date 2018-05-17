import numpy
import cv2

face_cascade = cv2.CascadeClassifier("./.env/lib/python3.6/site-packages/cv2/data/haarcascade_frontalface_default.xml")

capture = cv2.VideoCapture(0)
capture.set(3, 640)
capture.set(4, 480)

while(1):
    _, frame = capture.read()
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    faces = face_cascade.detectMultiScale(gray, 1.3, 5)

    if len(faces) > 0:
        for (x, y, w, h) in faces:
            frame = cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 0, 255), 2)

    cv2.imshow("frame", frame)

    key = cv2.waitKey(5) & 0xFF
    if key == 27:
        break

cv2.destroyAllWindows()
