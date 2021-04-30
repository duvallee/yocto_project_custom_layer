#
#
#
# --------------------------------------------------------
SUMMARY = "Tencent's MCNN(Novel Convolutional Neural Network) Library, Yolo V3"
HOMEPAGE = "https://github.com/Tencent/ncnn.git"
SECTION = "libs"

# --------------------------------------------------------
LICENSE = "Apache-2.0"

# --------------------------------------------------------
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=8c8b5b19ef89ee9cfa47e768aa904935"

# --------------------------------------------------------
DEPENDS += "protobuf \
            protobuf-native \
            "

# --------------------------------------------------------
SRCREV = "e86799e95f79177824b42a5d6f91abf8d1beb076"
SRC_URI = "git://github.com/Tencent/ncnn.git;name=ncnn \
           file://001_CMakeLists_for_Yocto_Patch.patch \
          "

S = "${WORKDIR}/git"

# --------------------------------------------------------
CMAKE_VERBOSE = "VERBOSE=1"
EXTRA_OECMAKE = ""

CXXFLAGS += "-std=c++11 -fPIC"
BUILD_CXXFLAGS += "-std=c++11 -fPIC"

# --------------------------------------------------------
inherit cmake 

# --------------------------------------------------------

# --------------------------------------------------------


# --------------------------------------------------------
PACKAGES_${PN} += "${libdir}/* ${includedir}/*"
PACKAGES_${PN}-staticdev += "${libdir}/* ${includedir}/*"

FILES_${PN} += "${libdir}/* ${includedir}/*"

ALLOW_EMPTY_${PN} = "1"


