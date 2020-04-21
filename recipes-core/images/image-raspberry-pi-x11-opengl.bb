#
#
#

SUMMARY = "A very basic X11 image with a terminal"
LICENSE = "MIT"

# ============================================================
# inherit from core-image
inherit core-image distro_features_check

# ============================================================
IMAGE_FEATURES += "splash package-management x11 hwcodecs "

# for systemd
DISTRO_FEATURES_append = "systemd "
VIRTUAL-RUNTIME_init_manager = "systemd "
# prevent the SysVinit distribution feature from being automatically enabled.
DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"
VIRTUAL-RUNTIME_initscripts = ""

# for gui
DISTRO_FEATURES_append = "opengl "
DISTRO_FEATURES_append = "x11 "
DISTRO_FEATURES_remove = "wayland"
# PACKAGECONFIG_append_pn-mesa-gl = " gbm"
# RDEPENDS_packagegroup-core-x11-utils_remove_pn-packagegroup-core-x11 = "xinput-calibrator"

# ============================================================
# add openssh & sudo package
IMAGE_INSTALL_append += "openssh sudo "

# ============================================================
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"


