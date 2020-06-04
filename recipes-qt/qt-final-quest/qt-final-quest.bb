#
#
#

# ----------------------------------------------------------------
DESCRIPTION = "A QT5 final-quest Sample Program "
LICENSE = "CLOSED"

# ----------------------------------------------------------------
DEPENDS = "qtbase qtdeclarative qtquickcontrols2 qtcharts "
RDEPENDS_qt-swipeview-sample_append = "bash systemd "

# ----------------------------------------------------------------
SRC_URI ?=  ""

# qml
SRC_URI += "file://main.qml "
SRC_URI += "file://PageSystemInformation.qml "
SRC_URI += "file://PageCPUClock.qml "
SRC_URI += "file://PageMemory.qml "
SRC_URI += "file://PageVersion.qml "

# image

#
SRC_URI += "file://README "
SRC_URI += "file://qml.qrc "
SRC_URI += "file://qt-final-quest.pro "
SRC_URI += "file://qtquickcontrols2.conf "

# source
SRC_URI += "file://main.cpp "
SRC_URI += "file://SystemInfo.h "
SRC_URI += "file://SystemInfo.cpp "
SRC_URI += "file://cpumonitoring.h "
SRC_URI += "file://cpumonitoring.cpp "
SRC_URI += "file://cputhermalmonitoring.h "
SRC_URI += "file://cputhermalmonitoring.cpp "
SRC_URI += "file://systemmonitoring.h "
SRC_URI += "file://systemmonitoring.cpp "


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

    # ----------------------------------------------------
	bbplain "SRC : ${WORKDIR}"
	bbplain "DES : ${D}${bindir}"

    # ----------------------------------------------------
	install -d ${D}${bindir}
	install -m 0755 ${S}/build/qt-final-quest ${D}${bindir}

    bbplain "---------------------------------------------"

}



