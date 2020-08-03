#
#
#

# -------------------------------------------------------------------------------
# Adaptation from u-boot-common_${PV}.inc

HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "dtc-native bc-native"
DEPENDS += "flex-native bison-native"

COMPATIBLE_MACHINE = "(stm32mpcommon)"

# -------------------------------------------------------------------------------
SRC_URI = "git://${STM32MP_BSP_PATH}/u-boot;protocol=file;branch=${SRCBRANCH}"
SRCBRANCH = "stm32mp1_dunfell"
SRCREV = "${AUTOREV}"

# -------------------------------------------------------------------------------
U_BOOT_VERSION = "2020.01"
PV = "${U_BOOT_VERSION}"

S = "${WORKDIR}/git"

# ---------------------------------
# Configure devupstream class usage
# ---------------------------------
BBCLASSEXTEND = "devupstream:target"

# SRC_URI_class-devupstream = "git://github.com/STMicroelectronics/u-boot.git;name=uboot;protocol=https;branch=v${U_BOOT_VERSION}-stm32mp"
# SRCREV_class-devupstream = "764fc8b2591139fb6a729516ccb4f9836b310d63"
# SRCREV_FORMAT_class-devupstream = "uboot"
# PV_class-devupstream = "${U_BOOT_VERSION}+github+${SRCPV}"

# ---------------------------------
# Configure default preference to manage dynamic selection between tarball and github
# ---------------------------------
STM32MP_SOURCE_SELECTION ?= "tarball"

DEFAULT_PREFERENCE = "${@bb.utils.contains('STM32MP_SOURCE_SELECTION', 'github', '-1', '1', d)}"

