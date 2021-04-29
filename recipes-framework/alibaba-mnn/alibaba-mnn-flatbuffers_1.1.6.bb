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
DEPENDS = "protobuf \
           protobuf-native \
           "

# --------------------------------------------------------
SRCREV = "3dada34c16cc2143ca3837c89711d4f5455e8fc1"
SRC_URI = "git://github.com/alibaba/MNN.git;name=mnn \
          "

S = "${WORKDIR}/git"

# --------------------------------------------------------
inherit cmake 

# --------------------------------------------------------
do_configure() {
   # bbplain "do_configure"

   CURRENT_DIR=${PWD}
   cd ${S}
   ./schema/generate.sh
   cd ${CURRENT_DIR}

   # bbplain "do_configure"
}

# --------------------------------------------------------
do_compile() {
   bbplain ""
}

# --------------------------------------------------------
do_install () {
   # bbplain "do_install ..."
   # bbplain "${PV}"
   # bbplain "base_prefix : ${base_prefix}"
   # bbplain "bindir : ${bindir}"
   # bbplain "PACKAGE_ARCH : ${PACKAGE_ARCH}"
   # bbplain "SYSROOT_DESTDIR : ${SYSROOT_DESTDIR}"

   install -d ${D}${bindir}
   install -m 0755 ${S}/3rd_party/flatbuffers/tmp/flatc ${D}/${bindir}
}


# --------------------------------------------------------
PACKAGES_${PN} += "${bindir}/flatc"
PACKAGES_${PN}-native += "${bindir}/flatc"

# --------------------------------------------------------
FILES_${PN} += "${bindir}/*"
# ALLOW_EMPTY_${PN} = "1"

# --------------------------------------------------------
BBCLASSEXTEND = "native"


