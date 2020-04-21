#
#
#

SUMMARY = "A very basic X11 image with a terminal"
LICENSE = "MIT"

# ============================================================
# inherit from core-image
inherit core-image distro_features_check

# ============================================================
IMAGE_FEATURES += "splash package-management x11-base x11-sato "

# ============================================================
# prevent the SysVinit distribution feature from being automatically enabled.
DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"

# ============================================================
# add openssh & sudo package
IMAGE_INSTALL_append += "openssh sudo "

# ============================================================
REQUIRED_DISTRO_FEATURES = "x11"


# ============================================================
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"


