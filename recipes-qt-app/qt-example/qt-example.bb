#
#
#

# ----------------------------------------------------------------
DESCRIPTION = "A QT5 example Program "
LICENSE = "CLOSED"

# ----------------------------------------------------------------
DEPENDS = "qtbase qtdeclarative qtquickcontrols2"
RDEPENDS_qt-swipeview-sample_append = "bash systemd "

# ----------------------------------------------------------------
SRC_URI ?=  ""
SRC_URI += "file://DigitalClock.qml "
SRC_URI += "file://PageDigitalClock.qml "
SRC_URI += "file://PageStopWatch.qml "
SRC_URI += "file://README "
SRC_URI += "file://StopWatch.qml "
SRC_URI += "file://StopWatchControl.cpp "
SRC_URI += "file://StopWatchControl.h "
SRC_URI += "file://main.cpp "
SRC_URI += "file://main.qml "
SRC_URI += "file://qml.qrc "
SRC_URI += "file://qt-swipe-view.pro "
SRC_URI += "file://qt-swipe-view_en_US.ts "
SRC_URI += "file://qtquickcontrols2.conf "

LIC_FILES_CHKSUM = "file://README;md5=d41d8cd98f00b204e9800998ecf8427e"
FILESEXTRAPATHS_append = "${THISDIR}/source"

# ----------------------------------------------------------------
S = "${WORKDIR}"

# ----------------------------------------------------------------
inherit qmake5 pkgconfig

# ----------------------------------------------------------------
FILES_${PN} += "/usr/bin"

# ----------------------------------------------------------------
do_install() {
   bbplain "---------------------------------------------"
	bbplain "SRC          : ${WORKDIR}"
	bbplain "DES          : ${D}${bindir}"
   bbplain "BUILD PATH   : ${B}"
   bbplain "BB Threads : ${BB_NUMBER_THREADS}"
   bbplain "PARALLEL_MAKE : ${PARALLEL_MAKE}"
   bbplain "---------------------------------------------"

    # ----------------------------------------------------
	install -d ${D}${bindir}
	install -m 0755 ${S}/build/qt-swipe-view ${D}${bindir}
}



