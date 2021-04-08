#
#
# ===============================================================
SUMMARY = "A small image for raspberry pi 4B"
IMAGE_LINGUAS = " "
LICENSE = "CLOSED"

# ===============================================================
IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"
inherit core-image

# ===============================================================
IMAGE_INSTALL += " tzdata"
IMAGE_INSTALL += " make cmake"

# for libboost-dev
IMAGE_INSTALL += " boost"
# for libgnutls28-dev
IMAGE_INSTALL += " gnutls"
IMAGE_INSTALL += " openssl"
# for libtiff5-dev
IMAGE_INSTALL += " tiff"

IMAGE_INSTALL += " meson"

IMAGE_INSTALL += " python3"
IMAGE_INSTALL += " python3-numpy"
IMAGE_INSTALL += " python3-pyyaml"
IMAGE_INSTALL += " python3-ply"

#
IMAGE_INSTALL += " libgles2 "

# ===============================================================
DEFAULT_TIMEZONE = "Asia/Seoul"

# ===============================================================
IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"



