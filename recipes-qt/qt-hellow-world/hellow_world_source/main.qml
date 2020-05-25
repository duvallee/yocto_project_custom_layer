import QtQuick 2.11
import QtQuick.Window 2.11

Window {
    visible: true
    width: 640
    height: 480
    color: "black"
    title: qsTr("Hello World")
    Rectangle{
        width: 640
        height: 480
        anchors.centerIn: parent
        color: "white"
        Text {
            id: id
            text: qsTr("Hello World !!!")
            color: "blue"
            font.pixelSize:34
            anchors.centerIn: parent
        }
    }
}
