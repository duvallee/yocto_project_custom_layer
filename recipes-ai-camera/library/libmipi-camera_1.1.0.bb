#
#
#
# ----------------------------------------------------------------
DESCRIPTION = "library for camera for raspberry pi"
SUMMARY = ""
LICENSE = "CLOSED"
SECTION = "libs"

# ----------------------------------------------------------------
DEPENDS += "userland"

PROVIDES = "libmipi-camera libmipi-camera-dev"

# ----------------------------------------------------------------
LIC_FILES_CHKSUM = "file://README.md;md5=0b95a9c6d7117aa90ab082f82d2395f6"

# ----------------------------------------------------------------
SRCREV = "e83e8621892a8cbb288988d8bae00b0303c8f9d7"
LIBMIPI_CAMERA_BRANCH = "raspberrypi-camera-library"

SRC_URI = "git://git@github.com/duvallee/raspberrypi-app.git;branch=${LIBMIPI_CAMERA_BRANCH};protocol=ssh"

# ----------------------------------------------------------------
S = "${WORKDIR}/git"

# ----------------------------------------------------------------
# inherit autotools

# ----------------------------------------------------------------
do_install() {
   # bbplain "---------------------------------------------"

   install -d ${D}${libdir}
   install -d ${D}${includedir}

   install -m 0755 ${S}/libmipi_camera.so.1.1.0 ${D}/${libdir}
   ln -srf ${D}${libdir}/libmipi_camera.so.1.1.0 ${D}${libdir}/libmipi_camera.so
   ln -srf ${D}${libdir}/libmipi_camera.so.1.1.0 ${D}${libdir}/libmipi_camera.so.1

   cp ${S}/inc/libmipi_api.h ${D}${includedir}
}

# ----------------------------------------------------------------
PACKAGES_${PN} += "${libdir}/*"
PACKAGES_${PN}-dev += "${libdir}/* ${includedir}/*"

# ----------------------------------------------------------------
FILES_${PN} += "/usr/lib"


