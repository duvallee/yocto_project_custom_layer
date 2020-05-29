// ****************************************************************
//
// File: main.cpp
//
// Written by duvallee in 2020
//
// ****************************************************************
#include <QGuiApplication>
#include <QQmlApplicationEngine>

// --------------------------------------------------------------------------
#include "SystemInfo.h"
#include "cpumonitoring.h"
#include "cputhermalmonitoring.h"
#include "systemmonitoring.h"


// --------------------------------------------------------------------------
//  Name : main()
//         
// 
// --------------------------------------------------------------------------
int main(int argc, char *argv[])
{
   QCoreApplication::setAttribute(Qt::AA_EnableHighDpiScaling);

   // --------------------------------------------------------------------------
   QGuiApplication app(argc, argv);

   // --------------------------------------------------------------------------
   qmlRegisterType<SystemInfo>("duvallee.custom.SystemInfo", 1, 0, "SystemInfo");
   qmlRegisterType<CPUMonitoring>("duvallee.custom.CPUMonitoring", 1, 0, "CPUMonitoring");
   qmlRegisterType<CPUThermalMonitoring>("duvallee.custom.CPUThermalMonitoring", 1, 0, "CPUThermalMonitoring");
   qmlRegisterType<SystemMonitoring>("duvallee.custom.SystemMonitoring", 1, 0, "SystemMonitoring");



   // --------------------------------------------------------------------------
   QQmlApplicationEngine engine;
   const QUrl url(QStringLiteral("qrc:/main.qml"));

   // --------------------------------------------------------------------------
   QObject::connect(&engine, &QQmlApplicationEngine::objectCreated, &app, [url](QObject *obj, const QUrl &objUrl)
   {
      if (!obj && url == objUrl)
      {
         QCoreApplication::exit(-1);
      }
   }, Qt::QueuedConnection);

   // --------------------------------------------------------------------------
   engine.load(url);

   return app.exec();
}

