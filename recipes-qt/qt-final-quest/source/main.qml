// ****************************************************************
//
// File: main.qml
//
// Written by duvallee in 2020
//
// ****************************************************************
import QtQuick 2.12
import QtQuick.Controls 2.12
import QtQuick.Controls.Material 2.12


ApplicationWindow
{
   id: window
   visible: true
   width: 480
   height: 800
   title: qsTr("Qt5-Final-Quest")

   // ================================================================
   header: ToolBar
   {
      // -------------------------------------------------------------
      contentHeight: toolButton.implicitHeight

      Material.foreground: "pink"
      Material.background: "pink"
      Material.accent: "pink"

      // -------------------------------------------------------------
      ToolButton
      {
         id: toolButton
         text: stackView.depth > 1 ? "\u25C0" : "\u2630"
         font.pixelSize: Qt.application.font.pixelSize * 1.6

         onClicked:
         {
            if (stackView.depth > 1)
            {
               stackView.pop()
            }
            else
            {
               drawer.open()
            }
         }
      }

      // -------------------------------------------------------------
      Label
      {
         text: stackView.currentItem.title
         anchors.centerIn: parent
      }

      // -------------------------------------------------------------
      background: Rectangle
      {
         anchors.fill: parent
         color : "darkslategray"
      }
   }

   // ================================================================
   Drawer
   {
      id: drawer
      width: window.width * 0.66
      height: window.height

      Column
      {
         anchors.fill: parent

         ItemDelegate
         {
            text: qsTr("CPU Monitoring")
            width: parent.width
            onClicked:
            {
               stackView.push("CpuMonitoring.qml")
               drawer.close()
            }
         }

         ItemDelegate
         {
            text: qsTr("Thermal Monitoring")
            width: parent.width

            onClicked:
            {
               stackView.push("ThermalMonitoring.qml")
               drawer.close()
            }
         }

         ItemDelegate
         {
            text: qsTr("System Monitoring")
            width: parent.width

            onClicked:
            {
               stackView.push("SystemMonitoring.qml")
               drawer.close()
            }
         }
      }
   }

   // ================================================================
   StackView
   {
      id: stackView
      initialItem: "Home_System_Information.qml"
      anchors.fill: parent
   }
}

