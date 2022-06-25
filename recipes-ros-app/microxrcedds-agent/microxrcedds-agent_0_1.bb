#
#
#
# ----------------------------------------------------------------------------
inherit ros_distro_foxy
inherit ros_superflore_generated

# ----------------------------------------------------------------------------
DESCRIPTION = "Example of minimal publisher/subscriber using rclcpp."
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;md5=8535705e8bb5a3ae36bffbbc8853193a"
FILESEXTRAPATHS_append = "${THISDIR}/source"

# ----------------------------------------------------------------------------
SRC_URI = "file://*"

# ----------------------------------------------------------------------------
# Modify these as desired
ROS_CN = "microxrcedds_agent"
ROS_BPN = "microxrcedds_agent"

# ----------------------------------------------------------------------------
do_compile_prepend() {
   bbplain "------------------------------------------"
   bbplain "compile_prepend"
   bbplain "------------------------------------------"
}

do_compile_append() {
   bbplain "------------------------------------------"
   bbplain "compile_append"
   bbplain "------------------------------------------"
}

# ----------------------------------------------------------------------------
#   ament-cmake 
#   microxrcedds-agent   

ROS_BUILD_DEPENDS = " \
   ament-cmake \
   fastcdr  \
   fastrtps \
   "

ROS_BUILDTOOL_DEPENDS = " \
   ament-cmake-native \
   "

ROS_EXPORT_DEPENDS = ""
ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
   rosidl-default-runtime  \
   "
# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    ament-lint-auto \
    ament-lint-common \
"

# ----------------------------------------------------------------------------
DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

# ----------------------------------------------------------------------------
RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# ----------------------------------------------------------------------------
S = "${WORKDIR}"

# ----------------------------------------------------------------------------
# NOTE: unable to map the following CMake package dependencies: rclcpp ament_lint_auto std_msgs ros_ament_cmake
inherit ros_ament_cmake


