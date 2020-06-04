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

   // for text
   // title
   property int g_system_title_font_size : 18
   property string g_system_title_font_color : "lavender"
   property int g_system_title_left_margin : 10

   // contents
   property int g_system_content_font_size : 14
   property string g_system_content_font_color : "hotpink"
   property int g_system_content_left_margin : 16

   // line
   property int g_system_text_alignmet : 34

   // for chart
   property int g_chart_layout_width : 480
   property int g_chart_layout_height : 400


   // ------------------------------------------------------------
   property int time_count : 0
   property int chart_index : 0

//   property int shift_count : 30
   property int shift_count : 6

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
      x: g_system_layout_margin
      y: g_system_layout_margin
      width: (g_system_layout_width - (g_system_layout_margin * 2))
      height: (g_system_layout_height - g_system_layout_margin)

      // ---------------------------------------------------------
      color: "darkslategray"
      radius: 10

      // ---------------------------------------------------------
      ColumnLayout
      {
         spacing: 0

         // ------------------------------------------------------
         // title
         ColumnLayout
         {
            Layout.leftMargin : g_system_title_left_margin
            height: (g_system_text_alignmet * 1.2)

            Text
            {
               Layout.alignment : Qt.AlignLeft
               text : "System Inforamtion"
               color : g_system_title_font_color
               font.bold : true;
               font.pixelSize : g_system_title_font_size
            }
         }

         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin: g_system_content_left_margin
            height: g_system_text_alignmet
            Text
            {
               Layout.alignment : Qt.AlignLeft
               text : systeminfo.getCpuInfo
               color : g_system_content_font_color
               font.pixelSize : g_system_content_font_size
            }
         }

         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin: g_system_content_left_margin
            height: g_system_text_alignmet
            Text
            {
               text : systeminfo.getCpuArch
               color : g_system_content_font_color
               font.pixelSize : g_system_content_font_size
            }
         }

         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin: g_system_content_left_margin
            height: g_system_text_alignmet
            Text
            {
               text : systeminfo.getCpuRev
               color : g_system_content_font_color
               font.pixelSize : g_system_content_font_size
            }
         }

         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin: g_system_content_left_margin
            height: g_system_text_alignmet
            Text
            {
               text : systeminfo.getCpuSerial
               color : g_system_content_font_color
               font.pixelSize : g_system_content_font_size
            }
         }
         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin: g_system_content_left_margin
            height: g_system_text_alignmet
            Text
            {
               text : systeminfo.getTotalMem
               color : g_system_content_font_color
               font.pixelSize : g_system_content_font_size
            }
         }
         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin: g_system_content_left_margin
            height: g_system_text_alignmet
            Text
            {
               text : systeminfo.getCpuFreq
               color : g_system_content_font_color
               font.pixelSize : g_system_content_font_size
            }
         }
         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin: g_system_content_left_margin
            height: g_system_text_alignmet
            Text
            {
               text : systeminfo.getAvailCpuFreq
               color : g_system_content_font_color
               font.pixelSize : g_system_content_font_size
            }
         }
         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin: g_system_content_left_margin
            height: g_system_text_alignmet
            Text
            {
               text : systeminfo.getCpuGovernors
               color : g_system_content_font_color
               font.pixelSize : g_system_content_font_size
            }
         }
         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin: g_system_content_left_margin
            height: g_system_text_alignmet
            Text
            {
               text : systeminfo.getCpuAvailGovernors
               color : g_system_content_font_color
               font.pixelSize : g_system_content_font_size
            }
         }
         // ------------------------------------------------------
         ColumnLayout
         {
            Layout.leftMargin: g_system_content_left_margin
            height: g_system_text_alignmet
            Text
            {
               text : systeminfo.getIPaddress
               color : g_system_content_font_color
               font.pixelSize : g_system_content_font_size
            }
         }
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
      margins.top: 2
      margins.left: 2
      margins.right: 2
      margins.bottom: 4

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
         titleFont.pointSize: 7
         labelsFont.pointSize: 7
      }

      // ---------------------------------------------------------
      ValueAxis
      {
         id : value_valueAxisY
         min : 0
         max : 100

         // ------------------------------------------------------
         titleFont.pointSize : 7
         labelsFont.pointSize : 7

         titleText : "&deg;C  %"
      }

      // ---------------------------------------------------------
      LineSeries
      {
         id : cpu_load_Series
         axisX : second_AxisX
         axisY : value_valueAxisY

         // ------------------------------------------------------
         pointLabelsFont.pointSize : 7

         color : "yellow"

         name : "cpu"
      }

      // ---------------------------------------------------------
      LineSeries
      {
         id : cpu_0_load_Series
         axisX : second_AxisX
         axisY : value_valueAxisY

         // ------------------------------------------------------
         pointLabelsFont.pointSize : 7

         color : "greenyellow"

         name : "cpu 0"
      }

      // ---------------------------------------------------------
      LineSeries
      {
         id : cpu_1_load_Series
         axisX : second_AxisX
         axisY : value_valueAxisY

         // ------------------------------------------------------
         pointLabelsFont.pointSize : 7

         color : "gold"
         
         name : "cpu 1"
      }

      // ---------------------------------------------------------
      LineSeries
      {
         id : memory_Series
         axisX : second_AxisX
         axisY : value_valueAxisY

         // ------------------------------------------------------
         pointLabelsFont.pointSize : 7

         color : "white"
         
         name : "mem used"
      }

      // ---------------------------------------------------------
      LineSeries
      {
         id : temp_load_Series
         axisX : second_AxisX
         axisY : value_valueAxisY

         // ------------------------------------------------------
         pointLabelsFont.pointSize : 7

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



