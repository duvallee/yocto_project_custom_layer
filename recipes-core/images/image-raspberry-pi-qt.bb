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
IMAGE_INSTALL_append = " ai-camera-opencv-qt"

# ===============================================================
# for wifi
IMAGE_INSTALL_append = " wpa-supplicant"
IMAGE_INSTALL_append = " iw"
IMAGE_INSTALL_append = " dhcp-client"
IMAGE_INSTALL_append = " linux-firmware"

# ===============================================================
# for lsusb 
IMAGE_INSTALL_append = " usbutils"

# ===============================================================
IMAGE_INSTALL += " openssh sudo"

IMAGE_INSTALL += " tzdata"

# for libboost-dev
IMAGE_INSTALL += " boost"
# for libgnutls28-dev
IMAGE_INSTALL += " gnutls"
IMAGE_INSTALL += " openssl"
# for libtiff5-dev
IMAGE_INSTALL += " tiff"

# IMAGE_INSTALL += " meson"

IMAGE_INSTALL += " htop"
IMAGE_INSTALL += " gdb"

# ===============================================================
# for python3
IMAGE_INSTALL += " python3"
IMAGE_INSTALL += " python3-numpy"
IMAGE_INSTALL += " python3-pyyaml"
IMAGE_INSTALL += " python3-ply"
IMAGE_INSTALL += " python3-sip3"

IMAGE_INSTALL += " python3-pyqt5"

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
   qtsvg \
   \
   "

# ===============================================================
IMAGE_INSTALL += " qt-example"
IMAGE_INSTALL += " python-example"

# ===============================================================
# ROS2 foxy
IMAGE_INSTALL += " ros-core"


# changed from galactic to foxy of ros2 on 26 Jan 2022
# demos for ros2's galactic
# IMAGE_INSTALL += " action-tutorials-cpp action-tutorials-interfaces action-tutorials-py"
# IMAGE_INSTALL += " composition"
# IMAGE_INSTALL += " demo-nodes-cpp demo-nodes-cpp-rosnative demo-nodes-py"
# IMAGE_INSTALL += " dummy-map-server dummy-robot-bringup dummy-sensors"
# IMAGE_INSTALL += " image-tools intra-process-demo lifecycle logging-demo"
# IMAGE_INSTALL += " pendulum-control pendulum-msgs"

# IMAGE_INSTALL += " turtlesim"

# ---------------------------------------------------------------
# for rqt-graph for ros2
# IMAGE_INSTALL += "   \
#   ament-index-python   \
#   python-qt-binding \
#   qt-dotgraph \
#   rqt-gui-py  \
#   "
# IMAGE_INSTALL += " rqt-graph"

# ---------------------------------------------------------------
# micro-ros-agent
IMAGE_INSTALL += " ament-cmake"
# IMAGE_INSTALL += " microxrcedds_agent"
IMAGE_INSTALL += " rosidl-cmake"
IMAGE_INSTALL += " fastcdr"
IMAGE_INSTALL += " fastrtps"
IMAGE_INSTALL += " fastrtps-cmake-module"
IMAGE_INSTALL += " rmw-dds-common"
IMAGE_INSTALL += " rmw"
IMAGE_INSTALL += " rcutils"
#
IMAGE_INSTALL += " rmw-fastrtps-shared-cpp"
#
IMAGE_INSTALL += " ament-lint-auto"
#
IMAGE_INSTALL += " rosidl-typesupport-fastrtps-cpp"
IMAGE_INSTALL += " rosidl-runtime-cpp"
IMAGE_INSTALL += " rosidl-typesupport-cpp"
IMAGE_INSTALL += " ament-cmake-gtest"
IMAGE_INSTALL += " micro-ros-msgs"

# ---------------------------------------------------------------
# etc DEPENDS
IMAGE_INSTALL += " rosidl-typesupport-c"
IMAGE_INSTALL += " rosidl-runtime-c"
IMAGE_INSTALL += " rosidl-runtime-cpp"
IMAGE_INSTALL += " rosidl-runtime-py"
IMAGE_INSTALL += " rosidl-typesupport-fastrtps-c"
IMAGE_INSTALL += " ament-lint"
IMAGE_INSTALL += " rosidl-cmake"
IMAGE_INSTALL += " rmw-fastrtps-cpp"
IMAGE_INSTALL += " rcpputils"
IMAGE_INSTALL += " rcpputils"
IMAGE_INSTALL += " foonathan-memory"
IMAGE_INSTALL += " asio"
IMAGE_INSTALL += " tinyxml2-vendor"
IMAGE_INSTALL += " spdlog"
IMAGE_INSTALL += " rosidl-generator-dds-idl"
IMAGE_INSTALL += " rmw-dds-common"
IMAGE_INSTALL += " json-glib"
IMAGE_INSTALL += " json-c"
# IMAGE_INSTALL += " cli11"
IMAGE_INSTALL += " gcc-sanitizers"

# for foonathan-memory
TOOLCHAIN_TARGET_TASK_append = " libstdc++-staticdev"
TOOLCHAIN_TARGET_TASK_append = " libc-staticdev"
TOOLCHAIN_TARGET_TASK_append = " foonathan-memory-staticdev"
#

# ############################################################### 
#
IMAGE_INSTALL += " micro-xrce-dds-agent"

# ---------------------------------------------------------------
# app for ros2
IMAGE_INSTALL += " spotmicro-ros"

# ---------------------------------------------------------------

IMAGE_INSTALL += " quality-of-service-demo-cpp quality-of-service-demo-py "
# topic-statics-demo is only in galatic and rolling of ros2
# IMAGE_INSTALL += "topic-monitor topic-statistics-demo "
IMAGE_INSTALL += " topic-monitor"

# ===============================================================
# for SDK
TOOLCHAIN_HOST_TASK_append = " nativesdk-flatbuffers-compiler"

# ===============================================================
DEFAULT_TIMEZONE = "Asia/Seoul"

# ===============================================================
IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"



