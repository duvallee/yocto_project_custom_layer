#
#
#

SUMMARY = "A very basic X11 image with a terminal"
LICENSE = "MIT"

# ============================================================
# inherit from core-image
inherit core-image distro_features_check

# ============================================================
# for x11
IMAGE_FEATURES += "splash package-management x11-base hwcodecs "

# for systemd init system
REQUIRED_DISTRO_FEATURES += "systemd "
REQUIRED_DISTRO_FEATURES += "opengl "

# ============================================================
MACHINE_FEATURES_append += " vc4graphics "
MACHINE_FEATURES_remove += "wayland "

# ============================================================
# for gui
DISTRO_FEATURES_append += "x11 "
DISTRO_FEATURES_append += "opengl "

# for wifi
DISTRO_FEATURES_append += "wifi "

# for bluetooth
DISTRO_FEATURES_append += "bluez5 bluetooth "

# ============================================================


# ============================================================
# add openssh & sudo package
IMAGE_INSTALL_append += "openssh "
IMAGE_INSTALL_append += "sudo "
IMAGE_INSTALL_append += "systemd "

# for wifi driver
# rtl8188eu
IMAGE_INSTALL_append += "linux-firmware-rtl8188 "
# IMAGE_INSTALL_append += "linux-firmware "

# raspberyy pi 4 wifi
IMAGE_INSTALL_append += "linux-firmware-rpidistro-bcm43430 "
IMAGE_INSTALL_append += "linux-firmware-rpidistro-bcm43455 "
IMAGE_INSTALL_append += "bluez-firmware-rpidistro-bcm43430a1-hcd "
IMAGE_INSTALL_append += "bluez-firmware-rpidistro-bcm4345c0-hcd "

# for wireless-tools (include iwconfig) & wpa-supplicant
IMAGE_INSTALL_append += "wpa-supplicant iw packagegroup-base-wifi dhcp-client "
# IMAGE_INSTALL_append += "wireless-tools "

# for module
IMAGE_INSTALL_append += "kernel-modules " 



# ------------------------------------------------------------
#  do_install ()
# ------------------------------------------------------------
add_configuration_files () {
#   bbplain "================================================="
#   bbplain "x11 for raspberry pi 4"
#   bbplain "================================================="
	bbplain ""

}
ROOTFS_POSTPROCESS_COMMAND += "add_configuration_files; "

# ============================================================
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"


