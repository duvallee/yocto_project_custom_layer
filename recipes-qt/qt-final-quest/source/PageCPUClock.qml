// ****************************************************************
//
// File: PageCPUClock.qml
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
   Rectangle
   {
      antialiasing: true
      color : "black"

      // ---------------------------------------------------------
      ColumnLayout
      {
         spacing : 0

         // ------------------------------------------------------
         ColumnLayout
         {
            height : 10
         }
         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin : 10
            height : 36

            Text
            {
               color: "greenyellow"

               text: "CPU Frequency"

               font.bold: true;
               font.pixelSize: g_title_font_size
               style: Text.Raised;
               styleColor: "black"
            }
         }
         // ------------------------------------------------------
         ColumnLayout
         {
            height : 10
         }

         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin : 10
            height : 36

            Text
            {
               color: "gainsboro"

               text: "Available CPU Frequency(KHz) : "

               font.pixelSize: g_content_font_size
               style: Text.Raised;
               styleColor: "black"
            }
         }
         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin : 20
            height : 36

            Text
            {
               color: "gainsboro"

               text: systeminfo.getAvailCpuFreq

               font.pixelSize: g_content_font_size
               style: Text.Raised;
               styleColor: "black"
            }
         }

         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin : 10
            height : 36

            Text
            {
               color: "gainsboro"

               text: "MIN : " + (systeminfo.getMinFreq / 1000).toString() + " MHz ~ MAX : " + (systeminfo.getMaxFreq / 1000).toString() + " MHz"

               font.pixelSize: g_content_font_size
               style: Text.Raised;
               styleColor: "black"
            }
         }
         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin : 10
            height : 36

            Text
            {
               color: "gainsboro"

               text: "Current CPU Frequency : " + (systeminfo.getCpuFreq.toString() / 1000).toString() + " MHz"

               font.pixelSize: g_content_font_size
               style: Text.Raised;
               styleColor: "black"
            }
         }

         // ------------------------------------------------------
         ColumnLayout
         {
            height : 10
         }

         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin : 10
            height : 36

            Text
            {
               color: "gainsboro"

               text: "Available Governors : "

               font.pixelSize: g_content_font_size
               style: Text.Raised;
               styleColor: "black"
            }
         }
         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin : 20
            height : 36

            Text
            {
               color: "gainsboro"

               text: systeminfo.getCpuAvailGovernors

               font.pixelSize: g_content_font_size
               style: Text.Raised;
               styleColor: "black"
            }
         }

         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin : 10
            height : 36

            Text
            {
               color: "gainsboro"

               text: "Current Governor : " + systeminfo.getCpuGovernors

               font.pixelSize: g_content_font_size
               style: Text.Raised;
               styleColor: "black"
            }
         }
         // ------------------------------------------------------
         ColumnLayout
         {
            height : 10
         }

         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin : 10
            height : 36

            Text
            {
               color: "greenyellow"

               text: "CPU LOAD"

               font.bold: true;
               font.pixelSize: g_title_font_size
               style: Text.Raised;
               styleColor: "black"
            }
         }

         // ------------------------------------------------------
         ColumnLayout
         {
            height : 10
         }

         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin : 10
            height : 36

            Text
            {
               id : cpu_load
               color: "gainsboro"

               text: "Current Governor : " + systeminfo.getCpuGovernors

               font.pixelSize: g_content_font_size
               style: Text.Raised;
               styleColor: "black"
            }
         }

         // ------------------------------------------------------
      }
   }
   // ------------------------------------------------------------
   // timer
   Timer
   {
      id : timer
      interval: 1000
      running: true;
      repeat: true
      onTriggered:
      {
         cpu_load.text = (systeminfo.getCpuLoadValue).toString() + " % (cpu), " + (systeminfo.getCpu_0_LoadValue).toString() + " % (cpu 0), " + (systeminfo.getCpu_1_LoadValue).toString() + " % (cpu 1)"
      }
   }
}


