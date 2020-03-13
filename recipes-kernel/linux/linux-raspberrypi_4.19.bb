LINUX_VERSION ?= "4.19.75"
# LINUX_RPI_BRANCH ?= "rpi-4.19.y"
LINUX_RPI_BRANCH ?= "raspberrypi_4"

SRCREV = "4e2a37d07a2a619b25ba35f89c5d8da72324b06e"

KERNEL_VERSION_SANITY_SKIP = "1"

require linux-raspberrypi_4.19.inc
