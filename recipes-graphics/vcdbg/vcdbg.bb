#
#
#
# ----------------------------------------------------------------------
SUMMARY = "vcdbg utility"
LICENSE = "CLOSED"

# ----------------------------------------------------------------------
LIC_FILES_CHKSUM = "file://vcdbg;md5=8ed8c318a83e7e6f435b4538b46707df"

# ----------------------------------------------------------------------
RDEPENDS_${PN} += "bash"

# ----------------------------------------------------------------------
SRC_URI = " \
          file://vcdbg \
          file://libelftoolchain.so \
          "

# ----------------------------------------------------------------------
do_configure() {
   bbplain ""
}

do_compile() {
   bbplain ""
}

# ----------------------------------------------------------------------
do_install() {
   bbplain "D : ${D}"
   bbplain "${WORKDIR}"
   install -d ${D}${bindir}
   install -d ${D}${libdir}

   install -m 0755 ${WORKDIR}/vcdbg ${D}${bindir}
   install -m 0755 ${WORKDIR}/libelftoolchain.so ${D}${libdir}/libelftoolchain.so.1.0.0
   ln -srf ${D}${libdir}/libelftoolchain.so.1.0.0 ${D}${libdir}/libelftoolchain.so
}

# ----------------------------------------------------------------------
# FILES += "${bindir}/vcdbg ${libdir}/libelftoolchain.so"

PACKAGES_${PN}-dev = ""

INSANE_SKIP_${PN} += "file-rdeps dev-deps dev-elf"


