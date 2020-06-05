#
#
#

# ------------------------------------------------------
SUMMARY = "ST example of image based on Qt framework."
LICENSE = "Proprietary"

# ------------------------------------------------------
include recipes-st/images/st-image.inc

# ------------------------------------------------------
inherit core-image distro_features_check

# populate_sdk_qt5

# ------------------------------------------------------
# let's make sure we have a good image..
CONFLICT_DISTRO_FEATURES = "x11 wayland"

# ------------------------------------------------------
IMAGE_LINGUAS = "en-us"

# ------------------------------------------------------
IMAGE_FEATURES += "     \
    splash              \
    package-management  \
    ssh-server-dropbear \
    hwcodecs            \
    tools-profile       \
    eclipse-debug       \
    "

# ------------------------------------------------------
# removed splash
IMAGE_FEATURES_remove += " splash "

# Define ROOTFS_MAXSIZE to 3GB
IMAGE_ROOTFS_MAXSIZE = "3145728"

# Set ST_EXAMPLE_IMAGE property to '1' to allow specific use in image creation process
ST_EXAMPLE_IMAGE = "1"

# ------------------------------------------------------
#
# INSTALL addons
#
CORE_IMAGE_EXTRA_INSTALL += "               \
    packagegroup-framework-core-base        \
    packagegroup-framework-tools-base       \
    \
    packagegroup-framework-core             \
    packagegroup-framework-tools            \
    \
    packagegroup-framework-core-extra       \
    \
    "

# ------------------------------------------------------
# for QT BASE
CORE_IMAGE_EXTRA_INSTALL += "               \
    qtbase                                  \
    qtbase-dev                              \
    qtbase-mkspecs                          \
    qtbase-plugins                          \
    qtbase-tools                            \
    "
# for QT PKGS
CORE_IMAGE_EXTRA_INSTALL += "               \
    qtquickcontrols2                        \
    qtquickcontrols2-qmldesigner            \
    qtquickcontrols2-qmlplugins             \
    qtquickcontrols2-dev                    \
    qtquickcontrols2-mkspecs                \
    "

# for QT PKGS(from st)
CORE_IMAGE_EXTRA_INSTALL += "               \
    liberation-fonts                        \
    qtbase-plugins                          \
    qtbase-tools                            \
    \
    qtdeclarative                           \
    qtconnectivity-dev                      \
    qtconnectivity-mkspecs                  \
    qtdeclarative-qmlplugins                \
    qtdeclarative-tools                     \
    \
    qtmultimedia                            \
    qtmultimedia-plugins                    \
    qtmultimedia-qmlplugins                 \
    \
    qtscript                                \
    "
# for QT PKGS(from st)
CORE_IMAGE_EXTRA_INSTALL += "               \
    qtcharts                                \
    qtcharts-dev                            \
    qtcharts-mkspecs                        \
    "

CORE_IMAGE_EXTRA_INSTALL += "               \
    ttf-dejavu-sans                         \
    ttf-dejavu-sans-mono                    \
    ttf-dejavu-sans-condensed               \
    ttf-dejavu-serif                        \
    ttf-dejavu-serif-condensed              \
    ttf-dejavu-common                       \
    "

# ------------------------------------------------------
CORE_IMAGE_EXTRA_INSTALL += " ldd "
CORE_IMAGE_EXTRA_INSTALL += " openstlinux-qt-eglfs "

# ------------------------------------------------------
CORE_IMAGE_EXTRA_INSTALL += " stress "

# ------------------------------------------------------
IMAGE_INSTALL_append = " tzdata"
DEFAULT_TIMEZONE = "Asia/Seoul"

# ------------------------------------------------------
# add sample program
CORE_IMAGE_EXTRA_INSTALL += " qt-hellow-world "
CORE_IMAGE_EXTRA_INSTALL += " qt-swipeview-sample "
# CORE_IMAGE_EXTRA_INSTALL += " qt-stm32mp-app "




