#
#
#

# ----------------------------------------------------------------
DESCRIPTION = "A QT Hellow World Sample Program "
LICENSE = "CLOSED"

# ----------------------------------------------------------------
DEPENDS = "qtbase qtdeclarative qtquickcontrols2"
RDEPENDS_qt-stm32mp-app_append = "bash systemd "

# ----------------------------------------------------------------
SRC_URI = "git://${STM32MP_APP_PATH};protocol=file;branch=stm32mp1;"
# SRCREV= "2ee0d937acaa73a258b7a379c10fc714d8f19296"
SRCREV = "${AUTOREV}"

LIC_FILES_CHKSUM = "file://README;md5=d41d8cd98f00b204e9800998ecf8427e"
# FILESEXTRAPATHS_append = "${THISDIR}/hellow_world_source"

# ----------------------------------------------------------------
S = "${WORKDIR}/git"

# ----------------------------------------------------------------
# FILES_${PN} += "/lib"
# FILES_${PN} += "/lib/systemd"
# FILES_${PN} += "/lib/systemd/system"
# FILES_${PN} += "/lib/systemd/system/qt-hellow-world.service"

# ----------------------------------------------------------------
# inherit qmake5 pkgconfig
require recipes-qt/qt5/qt5.inc

# ----------------------------------------------------------------
do_install() {
    bbplain "---------------------------------------------"
	install -d ${D}${bindir}
#    install -d -m 0755 ${D}/lib/systemd/system/
#    install -d -m 0755 ${D}/etc/systemd/system/multi-user.target.wants

    # ----------------------------------------------------
	install -m 0755 ${WORKDIR}/build/qt-swipe-view ${D}${bindir}
#    install -m 0644 ${THISDIR}/service/qt-hellow-world.service ${D}/lib/systemd/system/
#    ln -s /lib/systemd/system/qt-hellow-world.service ${D}/etc/systemd/system/multi-user.target.wants/qt-hellow-world.service
}



