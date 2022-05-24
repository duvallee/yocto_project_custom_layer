# Written by duvallee in 2022 
#
# ================================================================================
SUMMARY = "Python3 examplev"
DESCRIPTION = "Python3 example for qt & opencv"
SECTION = "base"
LICENSE = "CLOSED"

# ================================================================================
FILESEXTRAPATHS_append := "${THISDIR}/example"

# ================================================================================
do_install () {
   install -d -m 0755 ${D}/home/root/
   install -m 0775 ${THISDIR}/example/camera.py ${D}/home/root
   install -m 0775 ${THISDIR}/example/camera_qt.py ${D}/home/root
}

# ================================================================================
# add file lists
FILES_${PN} += "home/root "


