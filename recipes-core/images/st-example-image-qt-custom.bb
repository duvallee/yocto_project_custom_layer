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


CORE_IMAGE_EXTRA_INSTALL += " ldd "
CORE_IMAGE_EXTRA_INSTALL += " openstlinux-qt-eglfs "


# ------------------------------------------------------
# add sample program
CORE_IMAGE_EXTRA_INSTALL += " qt-hellow-world "
CORE_IMAGE_EXTRA_INSTALL += " qt-swipeview-sample "
CORE_IMAGE_EXTRA_INSTALL += " qt-stm32mp-app "




