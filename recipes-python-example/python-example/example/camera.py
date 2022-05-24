
import cv2
from PyQt5 import QtWidgets
from PyQt5 import QtGui

capture = cv2.VideoCapture(0)
capture.set(cv2.CAP_PROP_FRAME_WIDTH, 640)
capture.set(cv2.CAP_PROP_FRAME_HEIGHT, 480)

app = QtWidgets.QApplication([])
label = QtWidgets.QLabel()

pixmap = QtGui.QPixmap('test.jpg')
label.setPixmap(pixmap)
label.resize(pixmap.width(), pixmap.height())

frame = capture.read()
label.setPixmap(frame)
label.show()

capture.release()
app.exec_()

