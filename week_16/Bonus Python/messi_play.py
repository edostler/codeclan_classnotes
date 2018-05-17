import cv2
import numpy

img = cv2.imread("messi.jpg")

#Point 1: 360, 280
#Point 2: 410, 330
#[y1:y2, x1:x2]

ball = img[280:330, 360:410]

img[180:230, 260:310] = ball

cv2.imwrite("messimodified.jpg", img)
