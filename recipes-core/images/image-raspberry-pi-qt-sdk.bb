#
#
#
# --------------------------------------------------------------
SUMMARY = "SDK for image-raspberry-pi-qt"
LICENSE = "CLOSED"

# --------------------------------------------------------------
require recipes-core/images/image-raspberry-pi-qt.bb

# --------------------------------------------------------------


# --------------------------------------------------------------
BBCLASSEXTEND = "native nativesdk"
# DEPENDS = "catkin-native"

# --------------------------------------------------------------
IMAGE_INSTALL += " cmake" 

# --------------------------------------------------------------
IMAGE_INSTALL += "   \
   ament-cmake \
   ament-cmake-auto  \
   ament-cmake-core  \
   ament-cmake-export-definitions   \
   ament-cmake-export-dependencies  \
   ament-cmake-export-include-directories \
   ament-cmake-export-interfaces \
   ament-cmake-export-libraries  \
   ament-cmake-export-link-flags \
   ament-cmake-export-targets \
   ament-cmake-gmock \
   ament-cmake-google-benchmark  \
   ament-cmake-gtest \
   ament-cmake-include-directories  \
   ament-cmake-libraries   \
   ament-cmake-nose  \
   ament-cmake-pytest   \
   ament-cmake-python   \
   ament-cmake-target-dependencies  \
   ament-cmake-test  \
   ament-cmake-version \
   " 
# --------------------------------------------------------------
IMAGE_INSTALL += " ament-cmake-catch2" 

# --------------------------------------------------------------
IMAGE_INSTALL += "   \
   ament-cmake-ros   \
   domain-coordinator   \
   " 

# --------------------------------------------------------------
IMAGE_INSTALL += " \
   python3-colcon-bash \
   python3-colcon-cd \
   python3-colcon-cmake \
   python3-colcon-common-extensions \
   python3-colcon-core  \
   python3-colcon-defaults \
   python3-colcon-devtools \
   python3-colcon-library-path   \
   python3-colcon-metadata \
   python3-colcon-notification   \
   python3-colcon-output   \
   python3-colcon-package-information  \
   python3-colcon-package-selection \
   python3-colcon-parallel-executor \
   python3-colcon-pkg-config  \
   python3-colcon-python-setup-py   \
   python3-colcon-recursive-crawl   \
   python3-colcon-ros   \
   python3-colcon-test-result \
   "

# --------------------------------------------------------------
IMAGE_INSTALL += " yaml-cpp" 

# --------------------------------------------------------------
inherit populate_sdk  populate_sdk_qt5


