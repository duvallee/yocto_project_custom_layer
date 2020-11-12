#
#
#
# --------------------------------------------------------------------------------------------------------------------
SUMMARY = "JETSON-NANO image"

# --------------------------------------------------------------------------------------------------------------------
IMAGE_FEATURES += "splash"

# --------------------------------------------------------------------------------------------------------------------
# DISTRO_FEATURES_append = " weston-xwayland xterm "


# --------------------------------------------------------------------------------------------------------------------
LICENSE = "MIT"

# --------------------------------------------------------------------------------------------------------------------
inherit core-image

# --------------------------------------------------------------------------------------------------------------------
DISTRO_FEATURES_append = " systemd "
DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"
VIRTUAL-RUNTIME_init_manager = "systemd"
VIRTUAL-RUNTIME_initscripts = ""


# --------------------------------------------------------------------------------------------------------------------
IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL} "
IMAGE_INSTALL += "weston-xwayland "

IMAGE_INSTALL += "openssh ldd sudo bash e2fsprogs-mke2fs "
IMAGE_INSTALL += "haveged tar bzip2 gzip jq net-tools htop zbar "
IMAGE_INSTALL += "libjpeg-turbo libpng libc6-dev libstdc++ libgomp libgomp-dev libgomp-staticdev "

# IMAGE_INSTALL += "opencv opencv-dev "

IMAGE_INSTALL += "qtbase qtbase-dev qtbase-mkspecs qtbase-plugins qtbase-tools "
IMAGE_INSTALL += "qt3d qt3d-dev qt3d-mkspecs "
IMAGE_INSTALL += "qtcharts qtcharts-dev qtcharts-mkspecs "
IMAGE_INSTALL += "qtconnectivity-dev qtconnectivity-mkspecs "
IMAGE_INSTALL += "qtquickcontrols2 qtquickcontrols2-dev qtquickcontrols2-qmlplugins qtquickcontrols2-mkspecs "
IMAGE_INSTALL += "qtdeclarative qtdeclarative-dev qtdeclarative-mkspecs "
IMAGE_INSTALL += "qtgraphicaleffects qtgraphicaleffects-dev "
IMAGE_INSTALL += "liberation-fonts "
IMAGE_INSTALL += "qtmultimedia qtmultimedia-dev qtmultimedia-plugins qtmultimedia-qmlplugins "
IMAGE_INSTALL += "qtscript "
IMAGE_INSTALL += "ttf-dejavu-sans ttf-dejavu-sans-mono ttf-dejavu-sans-condensed ttf-dejavu-serif ttf-dejavu-serif-condensed ttf-dejavu-common "
IMAGE_INSTALL += "source-han-sans-kr-fonts "

IMAGE_INSTALL += " "
IMAGE_INSTALL += " "
IMAGE_INSTALL += " "
IMAGE_INSTALL += " "
IMAGE_INSTALL += " "
IMAGE_INSTALL += " "
IMAGE_INSTALL += " "


IMAGE_INSTALL += "tzdata "

# --------------------------------------------------------------------------------------------------------------------
IMAGE_LINGUAS = " "
DEFAULT_TIMEZONE = "Asia/Seoul"


# --------------------------------------------------------------------------------------------------------------------
IMAGE_ROOTFS_SIZE = "1048576"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"


