
import QtQuick 2.11
import QtQuick.Layouts 1.11
import QtQuick.Controls 2.11

ApplicationWindow
{
   id: main_window
   visible: true
   width: 480
   height: 800
   title: qsTr("qt-swipe-view")

   SwipeView
   {
      id: swipeView
      currentIndex: 0

      anchors.fill: parent

      Page
      {
         title: qsTr("Home")
         Rectangle
         {
            id : clock
            antialiasing: true
            color : "black"

            anchors.verticalCenter: parent.verticalCenter
            anchors.horizontalCenter: parent.horizontalCenter

            Text
            {
               color: "green"

               text: "QT Sample Program"

               font.bold: true;
               font.pixelSize: 36
               style: Text.Raised;
               styleColor: "black"

               anchors.verticalCenter: parent.verticalCenter
               anchors.horizontalCenter: parent.horizontalCenter
            }
         }
      }

      PageDigitalClock
      {
         title: qsTr("Watch")
         id: pagedigitalclock
      }

      PageStopWatch
      {
         title: qsTr("Timer")
         id: pagestopwatch
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

