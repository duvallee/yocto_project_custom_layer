
import QtQuick 2.11
import QtQuick.Layouts 1.11
import QtQuick.Controls 2.11


import duvallee.custom.StopWatchControl 1.0

ColumnLayout
{
   id : stopwatch
   spacing: 0

   // ----------------------------------------------------------------
   property bool stopwatch_running: false

   // ----------------------------------------------------------------
   // for update screen
   function timeChanged()
   {
      stop_watch_text.text = stopwatchcontrol.getStopWatchTimerValue
   }

   Timer
   {
      id: stopwatchTimer
      interval: 20
      repeat: true
      running: true
      triggeredOnStart: true
      onTriggered: stopwatch.timeChanged()
   }

   // ----------------------------------------------------------------
   StopWatchControl
   {
      id: stopwatchcontrol
   }

   // ===============================================================
   Rectangle
   {
      color: main_window.color

      Layout.preferredHeight: (parent.height * 0.2)
      Layout.preferredWidth: parent.width

      Text
      {
         id: stop_watch_text
         color: "lavender"

         font.bold: true;
         font.pixelSize: 36
         style: Text.Raised;
         styleColor: "black"

         anchors.verticalCenter: parent.verticalCenter
         anchors.horizontalCenter: parent.horizontalCenter
      }
   }

   // ===============================================================
   Rectangle
   {
      color: main_window.color

      Layout.preferredHeight: (parent.height * 0.5)
      Layout.preferredWidth: (parent.width * 0.4)
      Layout.leftMargin: (parent.width * 0.14)
      Layout.topMargin: (parent.height * 0.05)
      Layout.bottomMargin: (parent.height * 0.05)

      Layout.alignment: Qt.AlignHCenter


      ListView
      {
         id: stopwatch_history
         model: input_stopwatch_history

         delegate: Text
         {
            width: stopwatch_history.width

            color: "greenyellow"

            font.pixelSize: 22
            style: Text.Raised;
            styleColor: "black"

            text: history
         }

         verticalLayoutDirection: ListView.TopToBottom
         width: parent.width
         height: parent.height

         onCountChanged:
         {
            stopwatch_history.currentIndex = count - 1
         }
      }

      ListModel
      {
         id: input_stopwatch_history
      }
   }

   // ===============================================================
   RowLayout
   {
      id: button_row_layout

      Layout.preferredHeight: (parent.height * 0.2)
      Layout.preferredWidth: (parent.width * 0.4)
      Layout.alignment: Qt.AlignHCenter

      spacing: 0

      // ----------------------------------------------------------------
      ColumnLayout
      {
         Layout.preferredHeight: parent.height
         Layout.preferredWidth: (parent.width * 0.33)

         spacing: 0

         Button
         {
            id: button_start
            Layout.alignment: Qt.AlignRight | Qt.AlignVCenter

            contentItem: Text
            {
               text: stopwatch_running == true ? "stop" : "start"
               font: button_record.font
               // color:
               horizontalAlignment: Text.AlignHCenter
               verticalAlignment: Text.AlignVCenter
            }

            background: Rectangle
            {
               implicitWidth: 100
               implicitHeight: 40

               color: stopwatch_running == false ? (button_start.down ? "#404040" : "#808080") : (button_start.down ? "#404040" : "#808080")

               border.color: "#26282a"
               border.width: 1
               radius: 4
            }

            onClicked:
            {
               if (stopwatchcontrol.getStopWatchRunnig == 1)
               {
                  stopwatchcontrol.StopStopWatch
                  stopwatch_running = false
               }
               else
               {
                  stopwatchcontrol.StartStopWatch
                  stopwatch_running = true
               }
            }
         }
      }

      // ----------------------------------------------------------------
      ColumnLayout
      {
         Layout.preferredHeight: parent.height
         Layout.preferredWidth: (parent.width * 0.33)

         Button
         {
            text: "LAB"
            id: button_record

            Layout.alignment: Qt.AlignHCenter | Qt.AlignVCenter

            background: Rectangle
            {
               implicitWidth: 100
               implicitHeight: 40
               color: button_record.down ? "#404040" : "#808080"
               border.color: "#26282a"
               border.width: 1
               radius: 4
            }

            onClicked:
            {
               if (stopwatchcontrol.getStopWatchRunnig == 1)
               {
                  input_stopwatch_history.append({history: stopwatchcontrol.getStopWatchTimerRecording});
               }
            }
         }
      }

      // ----------------------------------------------------------------
      ColumnLayout
      {
         Layout.preferredHeight: parent.height
         Layout.preferredWidth: (parent.width * 0.33)

         Button
         {
            text: "Reset"
            id: button_reset

            Layout.alignment: Qt.AlignLeft | Qt.AlignVCenter

            background: Rectangle
            {
               implicitWidth: 100
               implicitHeight: 40
               color: button_reset.down ? "#404040" : "#808080"
               border.color: "#26282a"
               border.width: 1
               radius: 4
            }

            onClicked:
            {
               stopwatchcontrol.ReStartStopWatch
               input_stopwatch_history.clear()
               stopwatch_running = false
            }
         }
      }
   }
}

