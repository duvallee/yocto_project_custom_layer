
import QtQuick 2.11
import QtQuick.Controls 2.11

Page
{
   id: page_stopwatch

   StopWatch
   {
      anchors.left: parent.left;
      anchors.top: parent.top;
      anchors.right: parent.right;
      anchors.bottom: parent.bottom;
   }
}

