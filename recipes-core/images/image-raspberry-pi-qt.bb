#
# by duvallee.lee
#   2020-06-09
#
# ------------------------------------------------------
SUMMARY = "A small image for RASPBERRY PI 3B Board"
LICENSE = "CLOSED"

# ============================================================
# inherit from core-image
inherit core-image

# ============================================================
# init system 
DISTRO_FEATURES_append = "systemd "
VIRTUAL-RUNTIME_init_manager = "systemd "
# prevent the SysVinit distribution feature from being automatically enabled.
DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"
VIRTUAL-RUNTIME_initscripts = ""

# ============================================================
QT_BASE = " \
   qtbase \
   qtbase-dev \
   qtbase-mkspecs \
   qtbase-plugins \
   qtbase-tools \
   "

QT_PKGS = " \
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
   "


# ============================================================
# add openssh & sudo package
IMAGE_INSTALL_append += "openssh sudo "
IMAGE_INSTALL_append += "ldd stress "

IMAGE_INSTALL_append += " \
    ${QT_BASE} \
    ${QT_PKGS} \
"

# ------------------------------------------------------
IMAGE_INSTALL_append = " tzdata"
DEFAULT_TIMEZONE = "Asia/Seoul"



# ============================================================
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"


