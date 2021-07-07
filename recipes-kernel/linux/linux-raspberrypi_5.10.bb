LINUX_VERSION ?= "5.10.17"
LINUX_RPI_BRANCH ?= "rpi-5.10.y"

SRCREV_machine = "623ca2ba45d86eb1b0323637330295c3f8d93c76"
SRCREV_meta = "623ca2ba45d86eb1b0323637330295c3f8d93c76"

require linux-raspberrypi_5.10.inc

SRC_URI += "file://001-uart_i2c.patch "

# SRC_URI += "file://0001-Revert-selftests-bpf-Skip-perf-hw-events-test-if-the.patch \
#            file://0002-Revert-selftests-bpf-Fix-perf_buffer-test-on-systems.patch \
#            file://powersave.cfg \
#            file://android-drivers.cfg \
#           "

