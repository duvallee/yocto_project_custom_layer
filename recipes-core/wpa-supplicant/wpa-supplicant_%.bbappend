#
#
#

# -------------------------------------------------------------------------
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# -------------------------------------------------------------------------
SRC_URI += "file://wpa_supplicant-wext@wlp1s0u1u3.service "
SRC_URI += "file://wpa_supplicant-nl80211-wlan0.conf "
SRC_URI += "file://wpa_supplicant-nl80211-wlan1.conf "
SRC_URI += "file://wpa_supplicant-wext-wlp1s0u1u3.conf "

# -------------------------------------------------------------------------
SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN}_append += "wpa_supplicant-nl80211@wlan0.service"
SYSTEMD_SERVICE_${PN}_append += "wpa_supplicant-nl80211@wlan1.service"
SYSTEMD_SERVICE_${PN}_append += "wpa_supplicant-wext@wlp1s0u1u3.service"

# -------------------------------------------------------------------------
do_install_append () {
   install -d ${D}${sysconfdir}/wpa_supplicant/
   install -D -m 600 ${WORKDIR}/wpa_supplicant-nl80211-wlan0.conf ${D}${sysconfdir}/wpa_supplicant/
   install -D -m 600 ${WORKDIR}/wpa_supplicant-nl80211-wlan1.conf ${D}${sysconfdir}/wpa_supplicant/
   install -D -m 600 ${WORKDIR}/wpa_supplicant-wext-wlp1s0u1u3.conf ${D}${sysconfdir}/wpa_supplicant/

   install -d ${D}${systemd_unitdir}/system/
   install -D -m 600 ${WORKDIR}/wpa_supplicant-wext@wlp1s0u1u3.service ${D}${systemd_unitdir}/system/

   install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
   ln -s ${systemd_unitdir}/system/wpa_supplicant-nl80211@.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant-nl80211@wlan0.service
   ln -s ${systemd_unitdir}/system/wpa_supplicant-nl80211@.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant-nl80211@wlan1.service
   ln -s ${systemd_unitdir}/system/wpa_supplicant-wext@wlp1s0u1u3.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant-wext@wlp1s0u1u3.service
}


