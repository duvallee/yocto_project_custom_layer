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

# ----------------------------------------------------------------
LIC_FILES_CHKSUM = "file://README.md;md5=0b95a9c6d7117aa90ab082f82d2395f6"

# ----------------------------------------------------------------
SRCREV = "78f4f97061d657bd5401bc630cedea6f078432b0"
LIBMIPI_CAMERA_BRANCH = "raspberrypi-camera-library"

SRC_URI = "git://git@github.com/duvallee/image-processing.git;branch=${LIBMIPI_CAMERA_BRANCH};protocol=ssh"

# ----------------------------------------------------------------
S = "${WORKDIR}/git"

# ----------------------------------------------------------------
# inherit autotools

# ----------------------------------------------------------------
do_install() {
   # bbplain "---------------------------------------------"

   install -d ${D}${libdir}
   install -d ${D}${includedir}

   install -m 0755 ${S}/output/libmipi_camera.so.1.1.0 ${D}/${libdir}
   ln -srf ${D}${libdir}/libmipi_camera.so.1.1.0 ${D}${libdir}/libmipi_camera.so

   cp ${S}/inc/libmipi_api.h ${D}${includedir}
}

# ----------------------------------------------------------------
PACKAGES_${PN} += "${libdir}/*"
PACKAGES_${PN}-dev += "${libdir}/* ${includedir}/*"

# ----------------------------------------------------------------
FILES_${PN} += "/usr/lib"


