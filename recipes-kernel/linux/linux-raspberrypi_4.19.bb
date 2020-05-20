LINUX_VERSION ?= "4.19.97"
# LINUX_RPI_BRANCH ?= "rpi-4.19.y"
LINUX_RPI_BRANCH ?= "raspberrypi_4"

SRCREV = "52e79da2e41155dacbd678a46ab03fab6c9c1064"

KERNEL_VERSION_SANITY_SKIP = "1"

require linux-raspberrypi_4.19.inc
