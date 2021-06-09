#
#
#
# ----------------------------------------------------------------
DESCRIPTION = "A camera sample for raspberry pi"
SUMMARY = ""
LICENSE = "CLOSED"

# ----------------------------------------------------------------
DEPENDS += "userland libmipi-camera-dev flatbuffers tensorflow-lite tencent-ncnn alibaba-mnn opencv"
# RDEPENDS_${PN} += "libmipi-camera.so"

# ----------------------------------------------------------------
LIC_FILES_CHKSUM = "file://README.md;md5=0b95a9c6d7117aa90ab082f82d2395f6"

# ----------------------------------------------------------------
SRCREV = "639a70b46da1abec97f5febf0d425f0b3fbda3ec"
APP_BRANCH = "raspberrypi-camera-opencv"

SRC_URI = "git://git@github.com/duvallee/image-processing.git;branch=${APP_BRANCH};protocol=ssh"

# ----------------------------------------------------------------
S = "${WORKDIR}/git"

# ----------------------------------------------------------------
# inherit pkgconfig

# ----------------------------------------------------------------
do_configure() {
   bbplain ""
}

# ----------------------------------------------------------------
EXTRA_OEMAKE = "OECORE_TARGET_SYSROOT=${RECIPE_SYSROOT}"

# ----------------------------------------------------------------
do_install() {
   # bbplain "---------------------------------------------"

   install -d ${D}${bindir}
   install -d ${D}/usr/share/deep_learning_camera/training_data

   install -m 0755 ${S}/deep_learning_camera_opencv ${D}${bindir}

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
INSANE_SKIP_${PN} += "dev-deps"
# INSANE_SKIP_${PN} += "file-rdeps"

# ----------------------------------------------------------------
FILES_${PN} += "${bindir}/* /usr/share/deep_learning_camera/training_data/*"


