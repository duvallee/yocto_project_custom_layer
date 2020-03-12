LINUX_VERSION ?= "4.19.75"
# LINUX_RPI_BRANCH ?= "rpi-4.19.y"
LINUX_RPI_BRANCH ?= "raspberrypi_3b"

SRCREV = "03b0e33190ba64b91b8d00cf7d91af6361b4f065"

KERNEL_VERSION_SANITY_SKIP = "1"

require linux-raspberrypi_4.19.inc
