#
# by duvallee.lee
#

# ------------------------------------------------------------------------------------
SUMMARY = "custom image "
LICENSE = "Closed"

# ------------------------------------------------------------------------------------
inherit core-image features_check

# ------------------------------------------------------------------------------------
# let's make sure we have a good image...
# REQUIRED_DISTRO_FEATURES = "wayland"

IMAGE_FEATURES += "                     \
   splash                               \
   package-management                   \
   hwcodecs                             \
   tools-profile                        \
   eclipse-debug                        \
   "

IMAGE_FEATURES_remove = " ssh-server-dropbear "

# ------------------------------------------------------------------------------------
#
# INSTALL addons
#
CORE_IMAGE_EXTRA_INSTALL += "           \
   resize-helper                        \
   \
   packagegroup-framework-core-base     \
   packagegroup-framework-tools-base    \
   \
   packagegroup-framework-core          \
   packagegroup-framework-tools         \
   \
   packagegroup-framework-core-extra    \
   \
   ${@bb.utils.contains('COMBINED_FEATURES', 'optee', 'packagegroup-optee-core', '', d)}      \
   ${@bb.utils.contains('COMBINED_FEATURES', 'optee', 'packagegroup-optee-test', '', d)}      \
   \
   ${@bb.utils.contains('COMBINED_FEATURES', 'tpm2', 'packagegroup-security-tpm2', '', d)}    \
   \
   packagegroup-st-demo                 \
   "

# ------------------------------------------------------------------------------------
SYSTEM_UTIL_PKGS = "                    \
   openssh                              \
   sudo                                 \
   ldd                                  \
   wpa-supplicant                       \
   bash                                 \
   systemd-analyze                      \
   tar                                  \
   bzip2                                \
   gzip                                 \
   net-tools                            \
   "

# --------------------------------------------------------------
# standard C++ Library
# open mp
# png library
# libc.so.6
DEV_PKGS = "                            \
   libjpeg-turbo                        \
   libpng                               \
   libc6-dev                            \
   libstdc++                            \
   libgomp                              \
   libgomp-dev                          \
   libgomp-staticdev                    \
   "

# --------------------------------------------------------------
QT_PKGS = "                             \
   qtbase                               \
   qtbase-dev                           \
   qtbase-mkspecs                       \
   qtbase-plugins                       \
   qtbase-tools                         \
   \
   qt3d                                 \
   qt3d-dev                             \
   qt3d-mkspecs                         \
   \
   qtcharts                             \
   qtcharts-dev                         \
   qtcharts-mkspecs                     \
   \
   qtconnectivity-dev                   \
   qtconnectivity-mkspecs               \
   \
   qtquickcontrols2                     \
   qtquickcontrols2-dev                 \
   qtquickcontrols2-qmlplugins          \
   qtquickcontrols2-mkspecs             \
   \
   qtdeclarative                        \
   qtdeclarative-dev                    \
   qtdeclarative-mkspecs                \
   \
   qtgraphicaleffects                   \
   qtgraphicaleffects-dev               \
   \
   liberation-fonts                     \
   \
   qtmultimedia                         \
   qtmultimedia-dev                     \
   qtmultimedia-plugins                 \
   qtmultimedia-qmlplugins              \
   \
   qtscript                             \
   \
   ttf-dejavu-sans                      \
   ttf-dejavu-sans-mono                 \
   ttf-dejavu-sans-condensed            \
   ttf-dejavu-serif                     \
   ttf-dejavu-serif-condensed           \
   ttf-dejavu-common                    \
   \
   "

# --------------------------------------------------------------
PACKAGECONFIG_pn-python = "tkinter"
PHYTHON_PKGS = "                        \
   python3                              \
   python3-numpy                        \
   python3-tkinter                      \
   tcl                                  \
   "

# --------------------------------------------------------------
OPENCV_PKGS = "                         \
   opencv                               \
   opencv-dev                           \
   opencv-apps                          \
   opencv-samples                       \
   python3-opencv                       \
   \
   libopencv-core-dev                   \
   libopencv-highgui-dev                \
   libopencv-imgproc-dev                \
   libopencv-objdetect-dev              \
   libopencv-ml-dev                     \
   "

# --------------------------------------------------------------
MULTIMEIDA_PKGS = "                     \
   "

# --------------------------------------------------------------
APP_PKGS = "                            \
   "

# --------------------------------------------------------------
IMAGE_INSTALL += " ${SYSTEM_UTIL_PKGS} ${DEV_PKGS} ${QT_PKGS} ${PHYTHON_PKGS} ${OPENCV_PKGS} ${MULTIMEIDA_PKGS} ${APP_PKGS} " 


# ------------------------------------------------------------------------------------
IMAGE_LINGUAS = "en-us"

# --------------------------------------------------------------
MAGE_INSTALL_append = " tzdata"
DEFAULT_TIMEZONE = "Asia/Seoul"



