#
# by duvallee.lee
#
# ============================================================
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# ============================================================
SRC_URI += " \
   file://20-wired.network \
   file://30-wireless.network \
   file://35-usb-wireless.link   \
   file://40-usb-wireless.network   \
   file://resolved.conf \
"

# ============================================================
FILES_${PN} += " \
   ${sysconfdir}/systemd   \
   ${sysconfdir}/systemd/network \
   ${sysconfdir}/systemd/network/20-wired.network  \
   ${sysconfdir}/systemd/network/30-wireless.network  \
   ${sysconfdir}/systemd/network/35-usb-wireless.link \
   ${sysconfdir}/systemd/network/40-usb-wireless.network \
   ${sysconfdir}/systemd/resolved.conf \
"

# ============================================================
do_install_append() {
   bbplain "================================================="
   bbplain "system-conf"
   bbplain "D          : ${D}"
   bbplain "sysconfdir : ${sysconfdir}"
   bbplain "WORKDIR    : ${WORKDIR}"
   bbplain "================================================="

   #
   install -d ${D}${sysconfdir}/systemd
   install -d ${D}${sysconfdir}/systemd/network

   #
#   install -m 0644 ${WORKDIR}/resolved.conf ${D}${sysconfdir}/systemd/resolved.conf

   #
   ln -sf /dev/null ${D}${sysconfdir}/systemd/network/99-default.link

   install -m 0644 ${WORKDIR}/20-wired.network ${D}${sysconfdir}/systemd/network/20-wired.network
   install -m 0644 ${WORKDIR}/30-wireless.network ${D}${sysconfdir}/systemd/network/30-wireless.network
   install -m 0644 ${WORKDIR}/35-usb-wireless.link ${D}${sysconfdir}/systemd/network/35-usb-wireless.link
   install -m 0644 ${WORKDIR}/40-usb-wireless.network ${D}${sysconfdir}/systemd/network/40-usb-wireless.network
}

# ============================================================
do_override_resolved_conf() {
   bbplain "================================================="
   bbplain "system-conf"
   bbplain "D          : ${D}"
   bbplain "sysconfdir : ${sysconfdir}"
   bbplain "WORKDIR    : ${WORKDIR}"
   bbplain "================================================="

   install -d ${IMAGE_ROOTFS}/${sysconfdir}/systemd
   install -m 0644 ${WORKDIR}/resolved.conf ${IMAGE_ROOTFS}/${sysconfdir}/systemd/resolved.conf
}

ROOTFS_POSTPROCESS_COMMAND += "do_override_resolved_conf; "


