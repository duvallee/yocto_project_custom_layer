#
#
#
# ---------------------------------------------------------------------
DESCRIPTION = "Very fast, header only, C++ logging library."
HOMEPAGE = "https://github.com/gabime/spdlog/wiki"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bd5cc7fa6ff5ee46fc1047f0f0c895b7"

SRCREV = "eb3220622e73a4889eee355ffa37972b3cac3df5"
SRC_URI = "git://github.com/gabime/spdlog.git;protocol=https;branch=v1.x;"

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native"

# ---------------------------------------------------------------------
CXXFLAGS:append = " -fuse-ld=gold"

# ---------------------------------------------------------------------
# no need to build example&text&benchmarks on pure yocto
EXTRA_OECMAKE += "-DSPDLOG_INSTALL=on -DSPDLOG_BUILD_EXAMPLES=off -DSPDLOG_BUILD_TESTS=off -DSPDLOG_BUILD_BENCH=off"
EXTRA_OECMAKE += "-DSPDLOG_BUILD_SHARED=on"

# ---------------------------------------------------------------------
inherit cmake

# ---------------------------------------------------------------------
# Header-only library
RDEPENDS_${PN}-dev = ""
RRECOMMENDS_${PN}-dbg = "${PN}-dev (= ${EXTENDPKGV})"

