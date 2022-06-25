#
#
#
# ----------------------------------------------------------------------------
inherit ros_distro_foxy
inherit ros_superflore_generated

# ----------------------------------------------------------------------------
DESCRIPTION = "Example of minimal publisher/subscriber using rclcpp."
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;md5=cdb13ce4913629c99636665ea33e26c7"
FILESEXTRAPATHS_append = "${THISDIR}/source"

# ----------------------------------------------------------------------------
SRC_URI = "file://*"

# ----------------------------------------------------------------------------
# Modify these as desired
ROS_CN = "spotmicro_ros"
ROS_BPN = "spotmicro_ros"

# ----------------------------------------------------------------------------
ROS_BUILD_DEPENDS = " \
   rclcpp \
   std-msgs  \
   rosidl-typesupport-fastrtps-c-native   \
   rosidl-typesupport-fastrtps-cpp-native \
   "

ROS_BUILDTOOL_DEPENDS = " \
   ament-cmake-native \
   "

ROS_EXPORT_DEPENDS = ""
ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
   rclcpp \
   std-msgs \
   "

# ----------------------------------------------------------------------------
DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

# ----------------------------------------------------------------------------
RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# ----------------------------------------------------------------------------
S = "${WORKDIR}"

# ----------------------------------------------------------------------------
#
do_install_append () {
   install -d ${D}/usr/bin
   install -m 0755 ${B}/helloworld_publisher ${D}/usr/bin
   install -m 0755 ${B}/helloworld_subscriber ${D}/usr/bin
}

# ----------------------------------------------------------------------------


# ----------------------------------------------------------------------------
# NOTE: unable to map the following CMake package dependencies: rclcpp ament_lint_auto std_msgs ros_ament_cmake
inherit ros_ament_cmake


