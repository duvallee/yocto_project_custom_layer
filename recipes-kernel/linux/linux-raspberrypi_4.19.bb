LINUX_VERSION ?= "4.19.75"
# LINUX_RPI_BRANCH ?= "rpi-4.19.y"
LINUX_RPI_BRANCH ?= "raspberrypi_3b"

SRCREV = "10f85875c9768d7afe53c93b3ca670dcd2369d1e"

KERNEL_VERSION_SANITY_SKIP = "1"

require linux-raspberrypi_4.19.inc
