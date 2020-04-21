#
#
#

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

#
SRC_URI += " \
    file://eth.network \
    file://wlan.network \
    file://wlp1s0u.network \
    "

#
FILES_${PN} += " \
    ${sysconfdir}/systemd/network/eth.network \
    ${sysconfdir}/systemd/network/wlan.network \
    ${sysconfdir}/systemd/network/wlp1s0u.network \
"

#
do_install_append() {
#   bbplain "================================================="
#   bbplain "system-conf"
#   bbplain "D          : ${D}"
#   bbplain "sysconfdir : ${sysconfdir}"
#   bbplain "WORKDIR    : ${WORKDIR}"
#   bbplain "================================================="

    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/eth.network ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/wlan.network ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/wlp1s0u.network ${D}${sysconfdir}/systemd/network
}


