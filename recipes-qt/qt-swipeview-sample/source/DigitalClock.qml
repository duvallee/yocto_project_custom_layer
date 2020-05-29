import QtQuick 2.11
import QtQuick.Layouts 1.11

Rectangle
{
   id : clock
   property alias running: timer.running

   antialiasing: true
   color : "black"

   function timeChanged()
   {
      var today = new Date;
      clock_text.text = Qt.formatTime(today, "HH:mm:ss AP")
   }

   Timer
   {
      id : timer
      interval: 100;
      running: true;
      repeat: true
      onTriggered: clock.timeChanged();
   }

   Text
   {
      id: clock_text
      color: "green"

      font.bold: true;
      font.pixelSize: 36
      style: Text.Raised;
      styleColor: "black"

      anchors.verticalCenter: parent.verticalCenter
      anchors.horizontalCenter: parent.horizontalCenter
   }
}

