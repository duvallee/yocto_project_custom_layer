// ****************************************************************
//
// File: main.qml
//
// Written by duvallee in 2020
//
// ***************************************************************
import QtQuick 2.12
import QtCharts 2.12
import QtQuick.Layouts 1.11
import QtQuick.Controls 2.12
import duvallee.custom.SystemInfo 1.0

// ---------------------------------------------------------------
Item
{
   // ------------------------------------------------------------
   // for screen
   property int g_screen_width : 480
   property int g_screen_height : 800

   // for text of system
   property int g_system_layout_width : 480
   property int g_system_layout_height : 400
   property int g_system_layout_margin : 8

   // for chart
   property int g_chart_layout_width : 480
   property int g_chart_layout_height : 400


   // ------------------------------------------------------------
   // for text
   property int g_title_font_size : 34
   property int g_content_font_size : 22

   // ------------------------------------------------------------
   property int time_count : 0
   property int chart_index : 0

   property int shift_count : 30
//   property int shift_count : 10

   // ------------------------------------------------------------
   width: g_screen_width
   height: g_screen_height

   // ------------------------------------------------------------
   // class
   SystemInfo
   {
      id: systeminfo
   }

   // ------------------------------------------------------------
   // all screen
   Rectangle
   {
      anchors.fill: parent
      color: "black"
   }

   // ------------------------------------------------------------
   Rectangle
   {
      // ---------------------------------------------------------
      // size
      x : 0
      y : 0
      width : g_system_layout_width
      height : g_system_layout_height

      // ---------------------------------------------------------
      SwipeView
      {
         id: swipeView
         currentIndex: 0

         anchors.fill: parent

         PageSystemInformation
         {
            title: qsTr("System Information")
         }

         PageCPUClock
         {
            title: qsTr("Cpu Clock")
         }

         PageMemory
         {
            title: qsTr("memory")
         }

         PageVersion
         {
            title: qsTr("version")
         }

      }

      PageIndicator
      {
         id: pageindicator

         count: swipeView.count
         currentIndex: swipeView.currentIndex

         anchors.bottom: swipeView.bottom
         anchors.horizontalCenter: parent.horizontalCenter
      }
   }

   // ------------------------------------------------------------
   // cpu load & temperature
   ChartView
   {
      id: system_chartView

      // ---------------------------------------------------------
      titleFont: Qt.font({pointSize: 1})

      // ---------------------------------------------------------
      // size
      x : 0
      y : g_system_layout_height
      width : g_chart_layout_width
      height : g_chart_layout_height

      // ---------------------------------------------------------
      // margins
      margins.top: 0
      margins.left: 0
      margins.right: 0
      margins.bottom: 0

      // ---------------------------------------------------------
//      legend.visible: false
      legend.font.pointSize: 7

      // ---------------------------------------------------------
      legend.alignment: Qt.AlignTop
      animationOptions: ChartView.SeriesAnimations

      localizeNumbers: false
      antialiasing: true

      theme: ChartView.ChartThemeDark

      // ---------------------------------------------------------
      // time X
      ValueAxis
      {
         id : second_AxisX
         min : 0
         max : (shift_count - 1)
         tickCount : shift_count

         // ------------------------------------------------------
         titleFont.pointSize: 3
         labelsFont.pointSize: 3
      }

      // ---------------------------------------------------------
      ValueAxis
      {
         id : value_valueAxisY
         min : 0
         max : 100

         // ------------------------------------------------------
         titleFont.pointSize : 3
         labelsFont.pointSize : 3

         titleText : "&deg;C  %"
      }

      // ---------------------------------------------------------
      LineSeries
      {
         id : cpu_load_Series
         axisX : second_AxisX
         axisY : value_valueAxisY

         // ------------------------------------------------------
         pointLabelsFont.pointSize : 3

         color : "yellow"

         name : "c"
      }

      // ---------------------------------------------------------
      LineSeries
      {
         id : cpu_0_load_Series
         axisX : second_AxisX
         axisY : value_valueAxisY

         // ------------------------------------------------------
         pointLabelsFont.pointSize : 3

         color : "greenyellow"

         name : "c0"
      }

      // ---------------------------------------------------------
      LineSeries
      {
         id : cpu_1_load_Series
         axisX : second_AxisX
         axisY : value_valueAxisY

         // ------------------------------------------------------
         pointLabelsFont.pointSize : 3

         color : "gold"
         
         name : "c1"
      }

      // ---------------------------------------------------------
      LineSeries
      {
         id : memory_Series
         axisX : second_AxisX
         axisY : value_valueAxisY

         // ------------------------------------------------------
         pointLabelsFont.pointSize : 3

         color : "white"
         
         name : "mem"
      }

      // ---------------------------------------------------------
      LineSeries
      {
         id : temp_load_Series
         axisX : second_AxisX
         axisY : value_valueAxisY

         // ------------------------------------------------------
         pointLabelsFont.pointSize : 3

         color : "magenta"
         
         name : "temp"
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
         if (time_count > shift_count)
         {
            second_AxisX.min = time_count - shift_count;
            second_AxisX.max = time_count;

            cpu_load_Series.remove(time_count % shift_count);
            cpu_0_load_Series.remove(time_count % shift_count);
            cpu_1_load_Series.remove(time_count % shift_count);

            memory_Series.remove(time_count % shift_count);

            temp_load_Series.remove(time_count % shift_count);
         }

         cpu_load_Series.insert(time_count % shift_count, time_count, systeminfo.getCpuLoadValue);
         cpu_0_load_Series.insert(time_count % shift_count, time_count, systeminfo.getCpu_0_LoadValue);
         cpu_1_load_Series.insert(time_count % shift_count, time_count, systeminfo.getCpu_1_LoadValue);

         memory_Series.insert(time_count % shift_count, time_count, systeminfo.getMemUsedValue);

         temp_load_Series.insert(time_count % shift_count, time_count, systeminfo.getTemperatureValue);

         time_count++;
      }
   }
}



