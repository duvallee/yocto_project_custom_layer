#
#
#

SUMMARY = "A small image for RASPBERRY PI 3B Board"
LICENSE = "CLOSED"

# ============================================================
# inherit from core-image
inherit core-image

# ============================================================
# add openssh & sudo package
IMAGE_INSTALL_append += "openssh sudo "




# ============================================================
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"


