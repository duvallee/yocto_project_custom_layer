#
#
#
# ----------------------------------------------------------
DESCRIPTION = "TensorFlow Lite C++ Library" 
LICENSE = "Apache-2.0"

# ----------------------------------------------------------
LIC_FILES_CHKSUM = "file://LICENSE;md5=64a34301f8e355f57ec992c2af3e5157"
# Compute branch info from ${PV} as Base PV...
# BPV = "${@'.'.join(d.getVar('PV').split('.')[0:2])}"
# DPV = "${@'.'.join(d.getVar('PV').split('.')[0:3])}"

# ----------------------------------------------------------
# tag v2.4.1
SRCREV = "85c8b2a817f95a3e979ecd1ed95bff1dc1335cff"
TENSOR_BRANCH = "r2.4"

# ----------------------------------------------------------
SRC_URI = " \
    git://github.com/tensorflow/tensorflow.git;branch=${TENSOR_BRANCH} \
    file://001-Change-curl-to-wget-command.patch \
    file://001-TensorFlow-Lite_Makefile.patch \
    file://001-Remove-toolchain-setup-and-pybind11.patch \
"

# ----------------------------------------------------------
S = "${WORKDIR}/git"

DEPENDS += "gzip-native \
            unzip-native \
            zlib \
            python3 \
            python3-native \
            python3-numpy-native \
            python3-pip-native \
            python3-wheel-native \
            python3-pybind11 \
"

RDEPENDS_${PN} += " \
   python3 \
   python3-numpy \
   python3-pybind11 \
"

# ----------------------------------------------------------
PACKAGES += "${PN}-benchmark ${PN}-util"

PACKAGES_${PN}-benchmark += "benchmark_model benchmark_model_performance_options"
PACKAGES_${PN}-util += "minimal"

# ----------------------------------------------------------
CXXFLAGS += "--std=c++11"

# ----------------------------------------------------------
do_configure(){
   # bbplain "---------------------------------------------------------"
   export HTTP_PROXY=${HTTP_PROXY}
   export HTTPS_PROXY=${HTTPS_PROXY}
   export http_proxy=${HTTP_PROXY}
   export https_proxy=${HTTPS_PROXY}	

   ${S}/tensorflow/lite/tools/make/download_dependencies.sh
   # bbplain "---------------------------------------------------------"
}

# ----------------------------------------------------------
TENSORFLOW_TARGET = "${@bb.utils.contains('MACHINE', 'raspberrypi4-64', 'aarch64', 'rpi', d)}"
do_compile(){
   # bbplain "---------------------------------------------------------"
   bbplain "compile ..."
   # ${S}/tensorflow/lite/tools/make/build_rpi_lib.sh
   bbplain ${B}
   bbplain ${PARALLEL_MAKE}
   make ${PARALLEL_MAKE} TARGET=${TENSORFLOW_TARGET} -C ${B} -f ${S}/tensorflow/lite/tools/make/Makefile
   # bbplain "---------------------------------------------------------"
}

# ----------------------------------------------------------
TENSORFLOW_TARGET_DIR = "${@bb.utils.contains('MACHINE', 'raspberrypi4-64', 'aarch64', 'rpi_armv7l', d)}"
do_install() {
   # bbplain "---------------------------------------------------------"
   # bbplain "install ..."
   # bbplain "${D} ... ${libdir}"
   # bbplain "${includedir}"
 
   install -d ${D}${libdir}
   install -d ${D}${bindir}
 
   cp ${S}/tensorflow/lite/tools/make/gen/${TENSORFLOW_TARGET_DIR}/lib/* ${D}${libdir}
   cp ${S}/tensorflow/lite/tools/make/gen/${TENSORFLOW_TARGET_DIR}/bin/* ${D}${bindir}

   cd ${S}
   find tensorflow/lite -name "*.h" | cpio -pdm ${D}${includedir}/
   find tensorflow/lite -name "*.inc" | cpio -pdm ${D}${includedir}/
   
   install -d ${D}${includedir}/tensorflow_lite
   cd ${S}/tensorflow/lite
   cp --parents $(find . -name "*.h*") ${D}${includedir}/tensorflow_lite
}

# ----------------------------------------------------------
FILES_${PN}-dev = ""

INSANE_SKIP_${PN} += "ldflags"
INSANE_SKIP_${PN} += "dev-so"

FILES_${PN} += "${libdir}/* ${bindir}/* ${includedir}/*"


