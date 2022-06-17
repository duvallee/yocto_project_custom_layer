# 
#
# 
# ---------------------------------------------------------------------

DESCRIPTION = "eProsima MICRO XRCE-DDS"
AUTHOR = "eProsima"
HOMEPAGE = "https://micro-xrce-dds.docs.eprosima.com/en/latest/installation.html#installation-label"
LICENSE = "Apache-2.0"

# ---------------------------------------------------------------------
LIC_FILES_CHKSUM = "file://README.md;md5=7d29e5fa56e47b793c663672f64a302e"

# ---------------------------------------------------------------------
DEPENDS = " \
   asio  \
   fastcdr  \
   fastrtps \
   tinyxml2-vendor   \
   foonathan-memory  \
   spdlog   \
   json-glib  \
   json-c  \
   cli11 \
   gcc-sanitizers \
   "

# ---------------------------------------------------------------------
SRCREV = "a1f412b93ca3e33fe105ee20ab2fb49caa169f36"
BRANCH ?= "branch=foxy"
SRC_URI = "git://github.com/eProsima/Micro-XRCE-DDS-Agent.git;${BRANCH};protocol=https"

S = "${WORKDIR}/git"

# ---------------------------------------------------------------------
# 
do_install_append () {
#   bbplain "----------------------------------------------"
#   bbplain "B : ${B}"
#   bbplain "D : ${D}"
#   bbplain "S : ${S}"
#   bbplain "----------------------------------------------"

   install -d ${D}/usr/lib
   install -d ${D}/usr/include
   install -d ${D}/usr/share/microxrcedds_agent/cmake/config

   install -m 0755 ${B}/cmake/config/microxrcedds_agentConfig.cmake ${D}/usr/share/microxrcedds_agent/cmake/config/microxrcedds_agentConfig.cmake
   install -m 0755 ${B}/cmake/config/microxrcedds_agentConfigVersion.cmake ${D}/usr/share/microxrcedds_agent/cmake/config/microxrcedds_agentConfigVersion.cmake

   install -m 0755 ${B}/libmicroxrcedds_agent.so.2.1.1 ${D}/usr/lib/libmicroxrcedds_agent.so.2.1.1
   ln -srf ${D}/usr/lib/libmicroxrcedds_agent.so.2.1.1 ${D}/usr/lib/libmicroxrcedds_agent.so.2.1
   ln -srf ${D}/usr/lib/libmicroxrcedds_agent.so.2.1 ${D}/usr/lib/libmicroxrcedds_agent.so
}

# ---------------------------------------------------------------------
FILES_${PN} = "/usr/share/* /usr/lib/* /usr/bin/*"
FILES_${PN}-dev += "/usr/lib/libmicroxrcedds_agent.so"

# ---------------------------------------------------------------------
inherit cmake

