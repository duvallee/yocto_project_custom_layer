LINUX_VERSION ?= "4.19.75"
# LINUX_RPI_BRANCH ?= "rpi-4.19.y"
LINUX_RPI_BRANCH ?= "raspberrypi_3b"

SRCREV = "979b04486f700d9406157d51fe173b4eadc8d31b"

KERNEL_VERSION_SANITY_SKIP = "1"

require linux-raspberrypi_4.19.inc
