#
#
#
# --------------------------------------------------------
SUMMARY = "Alibaba's MNN(Mobile Neural Network) Library"
HOMEPAGE = "https://github.com/alibaba/MNN"
SECTION = "libs"

# --------------------------------------------------------
LICENSE = "Apache-2.0"

# --------------------------------------------------------
LIC_FILES_CHKSUM = "file://README.md;md5=692d64646daeb35b7c4ea4293840cc18"

# --------------------------------------------------------
DEPENDS += "protobuf \
            protobuf-native \
            alibaba-mnn-flatbuffers-native \
            "

do_patch[depends] += "alibaba-mnn-flatbuffers-native:do_populate_sysroot"
do_configure_extra[depends] += "alibaba-mnn-flatbuffers-native:do_populate_sysroot"

# --------------------------------------------------------
SRCREV = "3dada34c16cc2143ca3837c89711d4f5455e8fc1"
SRC_URI = "git://github.com/alibaba/MNN.git;name=mnn \
          "

S = "${WORKDIR}/git"

# --------------------------------------------------------
CMAKE_VERBOSE = "VERBOSE=1"
EXTRA_OECMAKE = "-D CMAKE_BUILD_TYPE=Release \
                 -D ARCHS=armv7 \
                 -D MNN_BUILD_QUANTOOLS=ON \
                 -D MNN_BUILD_CONVERTER=ON \
                 -D MNN_BUILD_DEMO=OFF \
                 -D MNN_BUILD_SHARED_LIBS=ON \
                 -D MNN_BUILD_BENCHMARK=OFF \
                "

CXXFLAGS += "-std=c++11 -fPIC"
BUILD_CXXFLAGS += "-std=c++11 -fPIC"

# --------------------------------------------------------
inherit cmake 

# --------------------------------------------------------
do_configure_extra() {
   # bbplain "do_configure_extra"

   mkdir -p ${S}/3rd_party/flatbuffers/tmp
#   cp ${WORKDIR}/flatc ${S}/3rd_party/flatbuffers/tmp
   cp ${WORKDIR}/recipe-sysroot-native/usr/bin/flatc ${S}/3rd_party/flatbuffers/tmp

   CURRENT_DIR=${PWD}
   cd ${S}
   ./schema/generate.sh
   cd ${CURRENT_DIR}

   # bbplain "do_configure_extra"
}
addtask do_configure_extra before do_configure after do_patch 

# --------------------------------------------------------
do_install () {
   # bbplain "do_install ..."
   # bbplain "${PV}"

   install -d ${D}${bindir}
   install -d ${D}${libdir}
   install -d ${D}${includedir}

   cp ${B}/libMNN.so ${D}${libdir}/libMNN.so.${PV}
   ln -srf ${D}${libdir}/libMNN.so.${PV} ${D}${libdir}/libMNN.so
   cd ${S}/include
   find -name "*.*" | cpio -pdm ${D}${includedir}/

   cp ${B}/backendTest.out ${D}${bindir}
   cp ${B}/checkInvalidValue.out ${D}${bindir}
   cp ${B}/getPerformance.out ${D}${bindir}
#   cp ${B}/MNNConvert ${D}${bindir}
   cp ${B}/MNNDump2Json ${D}${bindir}
   cp ${B}/MNNV2Basic.out ${D}${bindir}
   cp ${B}/mobilenetTest.out ${D}${bindir}
   cp ${B}/OnnxClip ${D}${bindir}
#   cp ${B}/quantized_model_optimize.out ${D}${bindir}
#   cp ${B}/quantized.out ${D}${bindir}
#   cp ${B}/TestConvertResult ${D}${bindir}
#   cp ${B}/testModel.out ${D}${bindir}
#   cp ${B}/testModelWithDescrisbe.out ${D}${bindir}
#   cp ${B}/TestPassManager ${D}${bindir}
#   cp ${B}/timeProfile.out ${D}${bindir}
}


# --------------------------------------------------------
PACKAGES_${PN} += "${bindir}/* ${libdir}/* ${includedir}/*"
PACKAGES_${PN}-dev += "${libdir}/* ${includedir}/*"

FILES_${PN} += "${libdir}/* ${bindir}/* ${includedir}/*"
# ALLOW_EMPTY_${PN} = "1"


