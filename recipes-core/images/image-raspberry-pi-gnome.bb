#
#
#

SUMMARY = "A small image for RASPBERRY PI 3B Board"
LICENSE = "CLOSED"

# ============================================================
# inherit from core-image
inherit core-image

# ============================================================
IMAGE_FEATURES += " splash "
IMAGE_FEATURES += " x11-base "
DISTRO_FEATURES_append = " opengl x11"

# ============================================================
# add package
IMAGE_INSTALL_append += "systemd "

# prevent the SysVinit distribution feature from being automatically enabled.
DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"

# ============================================================
# IMAGE_INSTALL += "gnome-desktop3 "

# ============================================================
# add openssh & sudo package
IMAGE_INSTALL_append += "openssh sudo "

# ============================================================
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"


