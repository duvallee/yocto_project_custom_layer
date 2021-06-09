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
IMAGE_INSTALL_append = " userland"
IMAGE_INSTALL_append = " vcdbg"

# ===============================================================
IMAGE_INSTALL_append = " wayland"
IMAGE_INSTALL_append = " kernel-modules"

IMAGE_INSTALL_append = " qt5-profile"
IMAGE_INSTALL_append = " profile-env"

# ===============================================================
# for opencv
# -D ENABLE_NEON=OFF in 32-bits
IMAGE_INSTALL_append = " make cmake"
# for sys/videoio.h not found
IMAGE_INSTALL_append = " libv4l-dev v4l-utils"
#
IMAGE_INSTALL_append = " libjpeg-turbo tiff libpng"
#
IMAGE_INSTALL_append = " libstdc++"

# IMAGE_INSTALL += " ${@bb.utils.contains('MACHINE', 'raspberrypi4-64', 'opencv', 'opencv-binary-32bit', d)}"
IMAGE_INSTALL_append = " opencv"

# ===============================================================
# for OpenMP
# in buildtools-extended-tarball
# no longer neceesary omp.h file in c source.
# add -fopenmp to CFLAGS in Makefile or option of gcc
IMAGE_INSTALL_append = " libgomp libgomp-dev libgomp-staticdev"

# ===============================================================
# for tensorflow-lite
# IMAGE_INSTALL_append = " flatbuffers flatbuffers-staticdev"
# IMAGE_INSTALL_append = " tensorflow-lite tensorflow-lite-staticdev"

TOOLCHAIN_TARGET_TASK_append = " flatbuffers-staticdev"
TOOLCHAIN_TARGET_TASK_append = " tensorflow-lite-staticdev"

# ===============================================================
# for alibaba-mnn 
IMAGE_INSTALL_append = " alibaba-mnn"

# ===============================================================
# for Tencents-ncnn
# IMAGE_INSTALL_append = " tencent-ncnn"
TOOLCHAIN_TARGET_TASK_append = " tencent-ncnn-staticdev"

# ===============================================================
# for camera
IMAGE_INSTALL_append = " libmipi-camera libmipi-camera-dev"
IMAGE_INSTALL_append = " ai-camera-opencv"

# ===============================================================
IMAGE_INSTALL += "openssh sudo"

IMAGE_INSTALL += "tzdata"

# for libboost-dev
IMAGE_INSTALL += "boost"
# for libgnutls28-dev
IMAGE_INSTALL += "gnutls"
IMAGE_INSTALL += "openssl"
# for libtiff5-dev
IMAGE_INSTALL += "tiff"

# IMAGE_INSTALL += " meson"

IMAGE_INSTALL += "python3"
IMAGE_INSTALL += "python3-numpy"
IMAGE_INSTALL += "python3-pyyaml"
IMAGE_INSTALL += "python3-ply"

IMAGE_INSTALL += "htop"
IMAGE_INSTALL += "gdb"

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
   qwt-qt5 \
   \
   qtwayland \
   \
   "

# ===============================================================
IMAGE_INSTALL += "qt-example"

# ===============================================================
#
# IMAGE_INSTALL += "libgles2"

# ===============================================================
# for SDK
TOOLCHAIN_HOST_TASK_append = " nativesdk-flatbuffers-compiler"

# ===============================================================
DEFAULT_TIMEZONE = "Asia/Seoul"

# ===============================================================
IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"



