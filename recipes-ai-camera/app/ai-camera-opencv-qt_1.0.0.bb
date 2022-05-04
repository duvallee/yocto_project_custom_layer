#
#
#
# ----------------------------------------------------------------
DESCRIPTION = "A camera qt sample for raspberry pi"
SUMMARY = ""
LICENSE = "CLOSED"

# ----------------------------------------------------------------
DEPENDS += "userland libmipi-camera-dev flatbuffers tensorflow-lite tencent-ncnn alibaba-mnn opencv"
DEPENDS += "qtbase"

# ----------------------------------------------------------------
# RDEPENDS_qt-swipeview-sample_append = "bash systemd "

# ----------------------------------------------------------------
LIC_FILES_CHKSUM = "file://README.md;md5=0b95a9c6d7117aa90ab082f82d2395f6"

# ----------------------------------------------------------------
SRCREV = "1b55343dd5d3de8033c31eb1b8b1db6c4dccf63d"
APP_BRANCH = "raspberrypi-camera-opencv-qt"

SRC_URI = "git://git@github.com/duvallee/raspberrypi-app.git;branch=${APP_BRANCH};protocol=ssh"

# ----------------------------------------------------------------
S = "${WORKDIR}/git"

# ----------------------------------------------------------------
do_compile_prepend() {
   export OECORE_TARGET_SYSROOT="${RECIPE_SYSROOT}"
}

# ----------------------------------------------------------------
inherit qmake5 pkgconfig

# ----------------------------------------------------------------
do_install() {
   # bbplain "---------------------------------------------"

   # -------------------------------------------------------------
   install -d ${D}${bindir}
   install -d ${D}/usr/share/deep_learning_camera/training_data

   # -------------------------------------------------------------
   install -m 0755 ${B}/qt-deeplearning-camera ${D}${bindir}

   # -------------------------------------------------------------
   install -m 0755 ${S}/training_data/COCO_detect.tflite ${D}/usr/share/deep_learning_camera/training_data
   install -m 0755 ${S}/training_data/COCO_labels.txt ${D}/usr/share/deep_learning_camera/training_data

   install -m 0755 ${S}/training_data/face_landmark.bin ${D}/usr/share/deep_learning_camera/training_data
   install -m 0755 ${S}/training_data/face_landmark.param ${D}/usr/share/deep_learning_camera/training_data

   install -m 0755 ${S}/training_data/mobilenet_yolov3.bin ${D}/usr/share/deep_learning_camera/training_data
   install -m 0755 ${S}/training_data/mobilenet_yolov3.param ${D}/usr/share/deep_learning_camera/training_data

   install -m 0755 ${S}/training_data/RFB-320.bin ${D}/usr/share/deep_learning_camera/training_data
   install -m 0755 ${S}/training_data/RFB-320.param ${D}/usr/share/deep_learning_camera/training_data

   install -m 0755 ${S}/training_data/slim-320-quant-ADMM-50.mnn ${D}/usr/share/deep_learning_camera/training_data

   install -m 0755 ${S}/training_data/version-RFB-320_simplified.onnx ${D}/usr/share/deep_learning_camera/training_data
}

# ----------------------------------------------------------------
FILES_${PN} += "${bindir}/* /usr/share/deep_learning_camera/training_data/*"


