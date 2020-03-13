#
#
#

SUMMARY = "A small image for RASPBERRY PI 3B Board"
LICENSE = "CLOSED"

# ============================================================
# inherit from core-image
inherit recipes-core/images/core-image-minimal.bb

# ============================================================
# Include modules in rootfs
IMAGE_INSTALL += " \
    kernel-modules \
    "
# ============================================================
SPLASH = "psplash-raspberrypi"

# ============================================================
# add package
IMAGE_INSTALL_append += "systemd "

# prevent the SysVinit distribution feature from being automatically enabled.
DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"

# ============================================================
# add openssh & sudo package
IMAGE_INSTALL_append += "openssh sudo "

IMAGE_INSTALL_append += "splash "


