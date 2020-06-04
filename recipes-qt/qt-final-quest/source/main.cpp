// ****************************************************************
//
// File: main.cpp
//
// Written by duvallee in 2020
//
// ****************************************************************
#include <QtWidgets/QApplication>
#include <QtQuick/QQuickView>
#include <QtCore/QDir>
#include <QtQml/QQmlEngine>

#include <QtGui/QScreen>

#include <QFont> 

#include "SystemInfo.h"

#if 0
#include <QDebug>

// --------------------------------------------------------------------------
#include "SystemInfo.h"
#include "cpumonitoring.h"
#include "cputhermalmonitoring.h"
#include "systemmonitoring.h"
#endif

// --------------------------------------------------------------------------
//  Name : main()
//         
// 
// --------------------------------------------------------------------------
int main(int argc, char *argv[])
{
   // Qt Charts uses Qt Graphics View Framework for drawing, therefore QApplication must be used.
   QApplication app(argc, argv);

   QApplication::setFont(QFont("DejaVu Sans")); 


   const auto screens = QGuiApplication::screens();
   for (QScreen *screen : screens)
   {
       screen->setOrientationUpdateMask(Qt::LandscapeOrientation | Qt::PortraitOrientation | Qt::InvertedLandscapeOrientation | Qt::InvertedPortraitOrientation);
   }

   // --------------------------------------------------------------------------
   qmlRegisterType<SystemInfo>("duvallee.custom.SystemInfo", 1, 0, "SystemInfo");

   // --------------------------------------------------------------------------
   QQuickView viewer;

   // The following are needed to make examples run without having to install the module
   // in desktop environments.

#ifdef Q_OS_WIN
   QString extraImportPath(QStringLiteral("%1/../../../../%2"));
#else
   QString extraImportPath(QStringLiteral("%1/../../../%2"));
#endif
   viewer.engine()->addImportPath(extraImportPath.arg(QGuiApplication::applicationDirPath(), QString::fromLatin1("qml")));
   QObject::connect(viewer.engine(), &QQmlEngine::quit, &viewer, &QWindow::close);

   viewer.setTitle(QStringLiteral("QML F1 Legends"));
   viewer.setSource(QUrl("qrc:/main.qml"));
   viewer.setResizeMode(QQuickView::SizeRootObjectToView);
   viewer.show();

   return app.exec();
}

