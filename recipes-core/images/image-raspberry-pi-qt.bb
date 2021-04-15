#
#
# ===============================================================
SUMMARY = "A qt image for raspberry pi 4B"
IMAGE_LINGUAS = " "
LICENSE = "CLOSED"

# ===============================================================
IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"
inherit core-image

# ===============================================================
IMAGE_INSTALL += " userland"
IMAGE_INSTALL += " wayland"
IMAGE_INSTALL += " kernel-modules"

IMAGE_INSTALL += " qt5-profile"
IMAGE_INSTALL += " profile-env"

# ===============================================================
IMAGE_INSTALL += " ${@bb.utils.contains('MACHINE', 'raspberrypi4-64', 'opencv', '', d)}"

# ===============================================================
IMAGE_INSTALL += " openssh sudo "

IMAGE_INSTALL += " tzdata"
IMAGE_INSTALL += " make cmake"

# for libboost-dev
IMAGE_INSTALL += " boost"
# for libgnutls28-dev
IMAGE_INSTALL += " gnutls"
IMAGE_INSTALL += " openssl"
# for libtiff5-dev
IMAGE_INSTALL += " tiff"

# IMAGE_INSTALL += " meson"

IMAGE_INSTALL += " python3"
IMAGE_INSTALL += " python3-numpy"
IMAGE_INSTALL += " python3-pyyaml"
IMAGE_INSTALL += " python3-ply"

# ===============================================================
IMAGE_INSTALL += " \ 
   qtbase \
   qtbase-dev \
   qtbase-mkspecs \
   qtbase-plugins \
   qtbase-tools \
   \
   qt3d \
   qt3d-dev \
   qt3d-mkspecs \
   \
   qtcharts \
   qtcharts-dev \
   qtcharts-mkspecs \
   \
   qtconnectivity-dev \
   qtconnectivity-mkspecs \
   \
   qtquickcontrols2 \
   qtquickcontrols2-dev \
   qtquickcontrols2-qmlplugins   \
   qtquickcontrols2-mkspecs \
   \
   qtdeclarative \
   qtdeclarative-dev \
   qtdeclarative-mkspecs \
   \
   qtgraphicaleffects \
   qtgraphicaleffects-dev \
   \
   liberation-fonts  \
   \
   qtmultimedia   \
   qtmultimedia-plugins \
   qtmultimedia-qmlplugins \
   \
   qtscript \
   \
   ttf-dejavu-sans   \
   ttf-dejavu-sans-mono \
   ttf-dejavu-sans-condensed  \
   ttf-dejavu-serif  \
   ttf-dejavu-serif-condensed  \
   ttf-dejavu-common \
   \
   qtwayland \
   \
   "

IMAGE_INSTALL += " qt-example "

# ===============================================================
#
# IMAGE_INSTALL += " libgles2 "

# ===============================================================
DEFAULT_TIMEZONE = "Asia/Seoul"

# ===============================================================
IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"



