// ****************************************************************
//
// File: PageSystemInformation.qml
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

               text: "STM32MP157C-DK2"

               font.bold: true;
               font.pixelSize: 36
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

               text: "Core Num : " + (systeminfo.getCpuNum).toString()

               font.pixelSize: 24
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

               text: "Archtecture : " + systeminfo.getCpuArch

               font.pixelSize: 24
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

               text: "Core Rev : " + systeminfo.getCpuRev

               font.pixelSize: 24
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

               text: "Core Serial : " + systeminfo.getCpuSerial

               font.pixelSize: 24
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

               text: "NETWORK"

               font.bold: true;
               font.pixelSize: 36
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

               text: "IP4v ADDR : " + systeminfo.getIPaddress

               font.pixelSize: 24
               style: Text.Raised;
               styleColor: "black"
            }
         }

         // ------------------------------------------------------
      }
   }
}



