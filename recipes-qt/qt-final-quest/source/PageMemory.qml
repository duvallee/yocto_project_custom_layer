// ****************************************************************
//
// File: PageMemory.qml
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

               text: "Memory"

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

               text: "Total Memory : " + (systeminfo.getTotalMem / 1000).toString() + " MB"

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
               id : free_memory
               color: "gainsboro"

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
               id : memory_used_rate

               color: "gainsboro"

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

               text: "Temperature"

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
               id : temperature

               color: "gainsboro"

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
         free_memory.text = "Free Memory : " + (systeminfo.getFreeMem / 1000).toString() + " MB"
         memory_used_rate.text = "Used Memory Rate : " + (systeminfo.getMemUsedValue).toString() + " %"
         temperature.text = "CPU Temperature : " + (systeminfo.getTemperatureValue).toString() + " °C"
      }
   }
}



