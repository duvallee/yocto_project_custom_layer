// ****************************************************************
//
// File: SystemInfo.cpp
//
// Written by duvallee in 2020
//
// ****************************************************************
#include "SystemInfo.h"

// --------------------------------------------------------------------------
// #define CPU_INFO_PATH                                    "/proc/cpuinfo"
#define CPU_INFO_PATH                                    "cpuinfo.txt"

#define CPU_INFO_PROCESSOR                               "processor"
#define CPU_INFO_MODEL                                   "model name"

// --------------------------------------------------------------------------
//  Name : SystemInfo()
//
//
// --------------------------------------------------------------------------
SystemInfo::SystemInfo(QObject *parent) : QObject(parent)
{
   // -------------------------------------------------------------------------------
   QFile qfile;
   FILE*  file = NULL;

   QString cpu_architecture_string = "unknown processor";
   int cpu_core_num = 0;

   // -------------------------------------------------------------------------------
   file = fopen(CPU_INFO_PATH, "r");
   if (file != NULL)
   {
      if (qfile.open(file, QFile::ReadOnly | QFile::Text))
      {
         QString ConfigText;
         while (!qfile.atEnd())
         {
            ConfigText  =  qfile.readLine();

            if (ConfigText.contains(CPU_INFO_PROCESSOR))
            {
            }

            if (ConfigText.contains(CPU_INFO_MODEL))
            {
            }
         }
         qfile.close();
      }
   }
   // -------------------------------------------------------------------------------
   QString test_text;
   test_text = "processor       : 0";

   int index = test_text.indexOf(":");

   if (test_text.contains(CPU_INFO_PROCESSOR))
   {
      cpu_core_num++;
   }
   
   if (test_text.contains(CPU_INFO_MODEL))
   {
      cpu_architecture_string = "";
   }


   test_text = "model name      : ARMv7 Processor rev 5 (v7l)";
   if (test_text.contains(CPU_INFO_PROCESSOR))
   {
      cpu_core_num++;
   }
   
   if (test_text.contains(CPU_INFO_MODEL))
   {
      cpu_architecture_string = "";
   }


   test_text = "processor       : 1";
   if (test_text.contains(CPU_INFO_PROCESSOR))
   {
      cpu_core_num++;
   }
   
   if (test_text.contains(CPU_INFO_MODEL))
   {
      cpu_architecture_string = "";
   }

   // -------------------------------------------------------------------------------
   m_processor_info = "unknown processor";


}

// --------------------------------------------------------------------------
//  Name : getCpuInfo()
//
//
// --------------------------------------------------------------------------
QString SystemInfo::getCpuInfo()
{
   return m_processor_info;
}




