#
# Copyright (C) 2022 by duvallee
#
#
# ================================================================================
SUMMARY = "mcu reset for SpotMicro"
DESCRIPTION = "mcu reset for SpotMicro"
SECTION = "base"
LICENSE = "CLOSED"

# ================================================================================
# md5sum ReadMe
LIC_FILES_CHKSUM = "file://README.txt;md5=e2a75e4a5612777e9c9ca07e3c81910c"
FILESEXTRAPATHS_append = "${THISDIR}/source"
SRC_URI = "file://*"

# ================================================================================
DEPENDS += " bash"
DEPENDS += " libmipi-camera"

# ================================================================================
S = "${WORKDIR}"

# ================================================================================
do_install_append () {
#   bbplain "---------------------------------------------------------------------"
   install -d ${D}${bindir}
   install -m 0755 ${S}/output/stm32-mcu-reset ${D}${bindir}
}

# ================================================================================
FILES_${PN} += "/usr/bin/stm32-mcu-reset "
PACKAGE_${PN} += "/usr/bin/stm32-mcu-reset "

inherit pkgconfig

