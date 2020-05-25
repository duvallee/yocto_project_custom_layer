#
#
#

# ----------------------------------------------------------------
DESCRIPTION = "A QT Hellow World Sample Program "
LICENSE = "CLOSED"

# ----------------------------------------------------------------
DEPENDS = "qtbase qtdeclarative"
RDEPENDS_qt-hellow-world_append = "bash systemd "

# ----------------------------------------------------------------
SRC_URI = "file://hellow_world.pro \
           file://hellow_world_en_US.ts \
           file://main.cpp \
           file://main.qml \
           file://qml.qrc \
           file://README "

LIC_FILES_CHKSUM = "file://README;md5=d41d8cd98f00b204e9800998ecf8427e"
FILESEXTRAPATHS_append = "${THISDIR}/hellow_world_source"

# ----------------------------------------------------------------
S = "${WORKDIR}"

# ----------------------------------------------------------------
FILES_${PN} += "/lib"
FILES_${PN} += "/lib/systemd"
FILES_${PN} += "/lib/systemd/system"
FILES_${PN} += "/lib/systemd/system/qt-hellow-world.service"

# ----------------------------------------------------------------
inherit qmake5 pkgconfig

# ----------------------------------------------------------------
do_install() {
    bbplain "---------------------------------------------"
	install -d ${D}${bindir}
    install -d -m 0755 ${D}/lib/systemd/system/
    install -d -m 0755 ${D}/etc/systemd/system/multi-user.target.wants

    # ----------------------------------------------------
	install -m 0755 ${S}/build/hellow_world ${D}${bindir}
    install -m 0644 ${THISDIR}/service/qt-hellow-world.service ${D}/lib/systemd/system/
    ln -s /lib/systemd/system/qt-hellow-world.service ${D}/etc/systemd/system/multi-user.target.wants/qt-hellow-world.service
}



