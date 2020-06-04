// ****************************************************************
//
// File: PageVersion.qml
//
// Written by duvallee in 2020
//
// ***************************************************************
import QtQuick 2.12
import QtQuick.Controls 2.12
import QtQuick.Layouts 1.11
import duvallee.custom.SystemInfo 1.0

// ---------------------------------------------------------------
Page
{
   title: qsTr("PageSystemInformation")

   // ------------------------------------------------------------
   Text
   {
      id : title
      // size
      x : 10
      y : 10
      width : g_screen_width - x
      height : 36

      color: "greenyellow"

      text: "Version"

      font.bold: true;
      font.pixelSize: 36
      style: Text.Raised;
      styleColor: "black"
   }

   // ------------------------------------------------------------
   Text
   {
      id : kernel_parameter
      // size
      x : 10
      y : (title.height + title.y + 5)
      width : g_screen_width - x
      height : 36

      color: "gainsboro"

      text: "Kernel Parameter"

      font.bold: true;
      font.pixelSize: 20
      style: Text.Raised;
      styleColor: "black"
   }

   // ------------------------------------------------------------
   Text
   {
      id : kernel_parameter_value

      // size
      x : 10
      y : (kernel_parameter.height + kernel_parameter.y + 5)
      width : g_screen_width - x
      height : 18

      wrapMode: Text.WordWrap

      color: "gainsboro"
      text: systeminfo.getKernelCmdLine
      font.pixelSize: 14
   }

   // ------------------------------------------------------------
   Text
   {
      id : kernel_version

      // size
      x : 10
      y : (kernel_parameter_value.height + kernel_parameter_value.y + 5)
      width : g_screen_width - x
      height : 36

      color: "gainsboro"

      text: "Kernel Version"

      font.bold: true;
      font.pixelSize: 20
      style: Text.Raised;
      styleColor: "black"
   }

   // ------------------------------------------------------------
   Text
   {
      id : kernel_version_value

      // size
      x : 10
      y : (kernel_version.height + kernel_version.y + 5)
      width : g_screen_width - x
      height : 36

      wrapMode: Text.WordWrap
      color: "gainsboro"
      text: systeminfo.getKernelVersion
      font.pixelSize: 14
   }

   // ------------------------------------------------------------
   Text
   {
      id : os_version

      // size
      x : 10
      y : (kernel_version_value.height + kernel_version_value.y + 5)
      width : g_screen_width - x
      height : 36

      color: "gainsboro"

      text: "OS Version"

      font.bold: true;
      font.pixelSize: 20
      style: Text.Raised;
      styleColor: "black"
   }

   // ------------------------------------------------------------
   Text
   {
      id : os_version_value

      // size
      x : 10
      y : (os_version.height + os_version.y + 5)
      width : g_screen_width - x
      height : 300

      wrapMode: Text.WordWrap
      color: "gainsboro"
      text: systeminfo.getOSVersion
      font.pixelSize: 14
   }
}


