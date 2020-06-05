// ****************************************************************
//
// File: SystemInfo.cpp
//
// Written by duvallee in 2020
//
// ****************************************************************
#include <QDebug>
#include <QNetworkInterface>

#include <QRandomGenerator>

#include "SystemInfo.h"

// --------------------------------------------------------------------------
#define CPU_INFO_PATH                                    "/proc/cpuinfo"

#define CPU_INFO_PROCESSOR                               "processor"
#define CPU_INFO_MODEL                                   "model name"
#define CPU_INFO_REVISION                                "Revision"
#define CPU_INFO_SERIAL                                  "Serial"

// --------------------------------------------------------------------------
#define CPU_STAT_PATH                                    "/proc/stat"
#define CPU_STAT_CPU                                     "cpu"
#define CPU_STAT_CPU0                                    "cpu0"
#define CPU_STAT_CPU1                                    "cpu1"

// --------------------------------------------------------------------------
#define MEM_INFO_PATH                                    "/proc/meminfo"
#define MEM_TOTAL                                        "MemTotal:"
#define MEM_FREE                                         "MemFree:"
#define MEM_AVAILABLE                                    "MemAvailable:"

// --------------------------------------------------------------------------
#define KERNEL_VERSION                                   "/proc/version"

// --------------------------------------------------------------------------
#define KERNEL_CMD_LINE                                  "/proc/cmdline"

// --------------------------------------------------------------------------
#define CPU_CUR_FREQ                                     "/sys/devices/system/cpu/cpufreq/policy0/cpuinfo_cur_freq" 
#define CPU_MAX_FREQ                                     "/sys/devices/system/cpu/cpufreq/policy0/cpuinfo_max_freq" 
#define CPU_MIN_FREQ                                     "/sys/devices/system/cpu/cpufreq/policy0/cpuinfo_min_freq" 

#define CPU_AVAILABLE_FREQ                               "/sys/devices/system/cpu/cpufreq/policy0/scaling_available_frequencies" 
#define CPU_AVAILABLE_GOVERNORS                          "/sys/devices/system/cpu/cpufreq/policy0/scaling_available_governors" 
#define CPU_CUR_GOVERNORS                                "/sys/devices/system/cpu/cpufreq/policy0/scaling_governor" 

// --------------------------------------------------------------------------
#define CPU_THERMAL_TEMP                                 "/sys/class/thermal/thermal_zone0/temp"
#define CPU_THERMAL_TYPE                                 "/sys/class/thermal/thermal_zone0/type" 

#define CPU_THERMAL_TRIP_HYST                            "/sys/class/thermal/trip_point_0_hyst" 
#define CPU_THERMAL_TRIP_TEMP                            "/sys/class/thermal/trip_point_0_temp" 
#define CPU_THERMAL_TRIP_TYPE                            "/sys/class/thermal/trip_point_0_type" 

// --------------------------------------------------------------------------
#define CPU_LOADAVG_PATH                                 "/proc/loadavg"

// --------------------------------------------------------------------------
//  Name : SystemInfo()
//
//
// --------------------------------------------------------------------------
SystemInfo::SystemInfo(QObject *parent) : QObject(parent)
{
   // -------------------------------------------------------------------------------
   m_cpu_num = 0;
   m_cpu_arch = QString("Unknown");
   m_cpu_rev = QString("Unknown");
   m_cpu_serial = QString("Unknown");

   m_total_mem_kb = 0;
   m_free_mem_kb = 0;
   m_availed_mem_kb = 0;

   m_cpu_cur_freq = 0;
   m_cpu_max_freq = 0;
   m_cpu_min_freq = 0;

   m_available_freq  = QString("Unknown");
   m_available_governors = QString("Unknown");
   m_cur_goverors  = QString("Unknown");

   m_ipaddress = QString("Unknown");

   m_temperature = 0;
   m_cpu_load = 0;
   m_cpu_0_load = 0;
   m_cpu_1_load = 0;
   m_mem_used = 0;

   m_kernel_cmd_line = QString("Unknown");
   m_kernel_version = QString("Unknown");
   m_os_version = QString("Unknown");

   QString env_str = getenv("PATH");
   qWarning() << env_str;

   // -------------------------------------------------------------------------------
   if (GetSystemInformation() == 0)
   {
   }
   else
   {
   }

   // -------------------------------------------------------------------------------
   if (GetSystemMemory() == 0)
   {
   }
   else
   {
   }

   // -------------------------------------------------------------------------------
   GetSysfs((char*) KERNEL_CMD_LINE, m_kernel_cmd_line);
   // -------------------------------------------------------------------------------
   GetSysfs((char*) KERNEL_VERSION, m_kernel_version);

   // -------------------------------------------------------------------------------
   QString cur_freq;
   GetSysfs((char*) CPU_CUR_FREQ, cur_freq);
   m_cpu_cur_freq = cur_freq.toInt();

   QString max_freq;
   GetSysfs((char*) CPU_MAX_FREQ, max_freq);
   m_cpu_max_freq = max_freq.toInt();

   QString min_freq;
   GetSysfs((char*) CPU_MIN_FREQ, min_freq);
   m_cpu_min_freq = min_freq.toInt();

   GetSysfs((char*) CPU_AVAILABLE_FREQ, m_available_freq);
   GetSysfs((char*) CPU_AVAILABLE_GOVERNORS, m_available_governors);
   GetSysfs((char*) CPU_CUR_GOVERNORS, m_cur_goverors);

   // -------------------------------------------------------------------------------
   QList<QHostAddress> list = QNetworkInterface::allAddresses();

   for(int nIter=0; nIter<list.count(); nIter++)
   {
      if(!list[nIter].isLoopback())
      {
         if (list[nIter].protocol() == QAbstractSocket::IPv4Protocol)
         {
            m_ipaddress = list[nIter].toString();
         }
      }
   }

   // -------------------------------------------------------------------------------
   qWarning(" CPU  : %d \n", m_cpu_num);
   qWarning() << " ARCH : " + m_cpu_arch;
   qWarning() << " REV  : " + m_cpu_rev;
   qWarning() << " SERI : " + m_cpu_serial;

   qWarning(" MEM  : %d kB\n", m_total_mem_kb);

   qWarning() << " CMD  : " + m_kernel_cmd_line;

   qWarning("FREQ  : %d (%d ~ %d) kHz\n", m_cpu_cur_freq, m_cpu_min_freq, m_cpu_max_freq);

   qWarning() << "AVAIL FREQ  : " + m_available_freq;
   qWarning() << "AVAIL GOVERNORS  : " + m_available_governors;
   qWarning() << "CUR GOVERNORS  : " + m_cur_goverors;
   qWarning() << "IP ADDRESS  : " + m_ipaddress;



}


// --------------------------------------------------------------------------
//  Name : GetSysfs()
//
//
// --------------------------------------------------------------------------
int SystemInfo::GetSysfs(char* sysfs, QString& retString)
{
   QFile qfile;
   FILE* file = NULL;

   // -------------------------------------------------------------------------------
   file = fopen(sysfs, "r");

   if (file != NULL)
   {
      if (qfile.open(file, QFile::ReadOnly | QFile::Text))
      {
         QByteArray array_cpuinfo;
         array_cpuinfo = qfile.readAll();

         // -------------------------------------------------------------------------------
         // split line
         QList<QByteArray> cpuinfo_byte_arrary = array_cpuinfo.split('\n');
         QByteArray cpuinfo_byte_line;
         QString cpuinfo_string_line;

         // get from list
         cpuinfo_byte_line = cpuinfo_byte_arrary.at(0);
         // byte to QString.
         retString = QString(cpuinfo_byte_line);

         // -------------------------------------------------------------------------------
         qfile.close();
      }
      else
      {
         qWarning("cannot open file (%s) \n", sysfs);
         return -1;
      }
      fclose(file);
   }
   else
   {
      qWarning("cannot open file (%s) \n", sysfs);
      return -1;
   }

   return 0;
}


// --------------------------------------------------------------------------
//  Name : GetSystemInformation()
//
//
// --------------------------------------------------------------------------
int SystemInfo::GetSystemInformation()
{
   QFile qfile;
   FILE*  file = NULL;

   // -------------------------------------------------------------------------------
   m_cpu_num = 0;
   m_cpu_arch = QString("known architecture");
   m_cpu_rev = QString("known");
   m_cpu_serial = QString("known");

   // -------------------------------------------------------------------------------
   file = fopen(CPU_INFO_PATH, "r");

   if (file != NULL)
   {
      if (qfile.open(file, QFile::ReadOnly | QFile::Text))
      {
         QByteArray array_cpuinfo;
         array_cpuinfo = qfile.readAll();

         // -------------------------------------------------------------------------------
         // split line
         QList<QByteArray> cpuinfo_byte_arrary = array_cpuinfo.split('\n');
         QByteArray cpuinfo_byte_line;
         QString cpuinfo_string_line;
         QString args;
         QString param;

         for (int i = 0; i < cpuinfo_byte_arrary.count(); i++)
         {
            // get from list
            cpuinfo_byte_line = cpuinfo_byte_arrary.at(i);
            // byte to QString.
            cpuinfo_string_line = QString(cpuinfo_byte_line);

            if (cpuinfo_string_line.contains(CPU_INFO_PROCESSOR))
            {
               args = cpuinfo_string_line.left(cpuinfo_string_line.indexOf("\t") - 1);
               param = cpuinfo_string_line.right(cpuinfo_string_line.length() - cpuinfo_string_line.indexOf(":") - 2);

               m_cpu_num++;
            }
            else if (cpuinfo_string_line.contains(CPU_INFO_MODEL))
            {
               args = cpuinfo_string_line.left(cpuinfo_string_line.indexOf("\t") - 1);
               param = cpuinfo_string_line.right(cpuinfo_string_line.length() - cpuinfo_string_line.indexOf(":") - 2);

               m_cpu_arch = param;
            }
            else if (cpuinfo_string_line.contains(CPU_INFO_REVISION))
            {
               args = cpuinfo_string_line.left(cpuinfo_string_line.indexOf("\t") - 1);
               param = cpuinfo_string_line.right(cpuinfo_string_line.length() - cpuinfo_string_line.indexOf(":") - 2);

               m_cpu_rev = param;
            }
            else if (cpuinfo_string_line.contains(CPU_INFO_SERIAL))
            {
               args = cpuinfo_string_line.left(cpuinfo_string_line.indexOf("\t") - 1);
               param = cpuinfo_string_line.right(cpuinfo_string_line.length() - cpuinfo_string_line.indexOf(":") - 2);

               m_cpu_serial = param;
            }
         }

         // -------------------------------------------------------------------------------
         qfile.close();
      }
      else
      {
         qWarning("cannot open file (%s) \n", CPU_INFO_PATH);
         return -1;
      }
      fclose(file);
   }
   else
   {
      qWarning("cannot open file (%s) \n", CPU_INFO_PATH);
      return -1;
   }
   return 0;
}

// --------------------------------------------------------------------------
//  Name : GetSystemMemory()
//
//
// --------------------------------------------------------------------------
int SystemInfo::GetSystemMemory()
{
   QFile qfile;
   FILE*  file = NULL;

   // -------------------------------------------------------------------------------
   m_total_mem_kb = 0;

   // -------------------------------------------------------------------------------
   file = fopen(MEM_INFO_PATH, "r");

   if (file != NULL)
   {
      if (qfile.open(file, QFile::ReadOnly | QFile::Text))
      {
         QByteArray array_cpuinfo;
         array_cpuinfo = qfile.readAll();

         // -------------------------------------------------------------------------------
         // split line
         QList<QByteArray> cpuinfo_byte_arrary = array_cpuinfo.split('\n');
         QByteArray cpuinfo_byte_line;
         QString cpuinfo_string_line;

         for (int i = 0; i < cpuinfo_byte_arrary.count(); i++)
         {
            // get from list
            cpuinfo_byte_line = cpuinfo_byte_arrary.at(i);
            // byte to QString.
            cpuinfo_string_line = QString(cpuinfo_byte_line);

            QList<QString> meminfo_byte_arrary = cpuinfo_string_line.split(' ');

//            qWarning() << meminfo_byte_arrary;

            if (meminfo_byte_arrary.at(0) == MEM_TOTAL)
            {
               for (int j = 0; j < meminfo_byte_arrary.count(); j++)
               {
                  if ((meminfo_byte_arrary.at(j)).toInt() != 0)
                  {
                     m_total_mem_kb = (meminfo_byte_arrary.at(j)).toInt();
                  }
               }
            }
            else if (meminfo_byte_arrary.at(0) == MEM_FREE)
            {
               for (int j = 0; j < meminfo_byte_arrary.count(); j++)
               {
                  if ((meminfo_byte_arrary.at(j)).toInt() != 0)
                  {
                     m_free_mem_kb = (meminfo_byte_arrary.at(j)).toInt();
                  }
               }
            }
            else if (meminfo_byte_arrary.at(0) == MEM_AVAILABLE)
            {
               for (int j = 0; j < meminfo_byte_arrary.count(); j++)
               {
                  if ((meminfo_byte_arrary.at(j)).toInt() != 0)
                  {
                     m_availed_mem_kb = (meminfo_byte_arrary.at(j)).toInt();
                  }
               }
               m_mem_used = ((m_total_mem_kb - m_availed_mem_kb) * 100) / m_total_mem_kb;
            }
         }

         // -------------------------------------------------------------------------------
         qfile.close();
      }
      else
      {
         qWarning("cannot open file (%s) \n", MEM_INFO_PATH);
         return -1;
      }
      fclose(file);
   }
   else
   {
      qWarning("cannot open file (%s) \n", MEM_INFO_PATH);
      return -1;
   }
   return 0;
}


// --------------------------------------------------------------------------
//  Name : GetCPULoad()
//
//
// --------------------------------------------------------------------------
int SystemInfo::GetCPULoad()
{
   QFile qfile;
   FILE* file = NULL;

   // -------------------------------------------------------------------------------
   m_total_mem_kb = 0;

   // -------------------------------------------------------------------------------
   file = fopen(CPU_STAT_PATH, "r");

   if (file != NULL)
   {
      if (qfile.open(file, QFile::ReadOnly | QFile::Text))
      {
         QByteArray array_cpuinfo;
         array_cpuinfo = qfile.readAll();

         // -------------------------------------------------------------------------------
         // split line
         QList<QByteArray> cpuinfo_byte_arrary = array_cpuinfo.split('\n');
         QByteArray cpuinfo_byte_line;
         QString cpuinfo_string_line;

         for (int i = 0; i < cpuinfo_byte_arrary.count(); i++)
         {
            // get from list
            cpuinfo_byte_line = cpuinfo_byte_arrary.at(i);
            // byte to QString.
            cpuinfo_string_line = QString(cpuinfo_byte_line);

            QList<QString> cpuinfo_byte_arrary = cpuinfo_string_line.split(' ');
            uint user_mode = 0;
            uint nice_user_mode = 0;
            uint system_mode = 0;
            uint idle = 0;

            if (cpuinfo_byte_arrary.at(0) == CPU_STAT_CPU)
            {
               user_mode = (cpuinfo_byte_arrary.at(2)).toInt();
               nice_user_mode = (cpuinfo_byte_arrary.at(3)).toInt();
               system_mode = (cpuinfo_byte_arrary.at(4)).toInt();
               idle = (cpuinfo_byte_arrary.at(5)).toInt();

               m_cpu_load = ((user_mode + nice_user_mode + system_mode) * 100) / (user_mode + nice_user_mode + system_mode + idle);
//               qWarning("%d, %d, %d, %d : %d \n", user_mode, nice_user_mode, system_mode, idle, m_cpu_load);
            }
            else if (cpuinfo_byte_arrary.at(0) == CPU_STAT_CPU0)
            {
               user_mode = (cpuinfo_byte_arrary.at(1)).toInt();
               nice_user_mode = (cpuinfo_byte_arrary.at(2)).toInt();
               system_mode = (cpuinfo_byte_arrary.at(3)).toInt();
               idle = (cpuinfo_byte_arrary.at(4)).toInt();

               m_cpu_0_load = ((user_mode + nice_user_mode + system_mode) * 100) / (user_mode + nice_user_mode + system_mode + idle);
//               qWarning("%d, %d, %d, %d : %d \n", user_mode, nice_user_mode, system_mode, idle, m_cpu_0_load);
            }
            else if (cpuinfo_byte_arrary.at(0) == CPU_STAT_CPU1)
            {
               user_mode = (cpuinfo_byte_arrary.at(1)).toInt();
               nice_user_mode = (cpuinfo_byte_arrary.at(2)).toInt();
               system_mode = (cpuinfo_byte_arrary.at(3)).toInt();
               idle = (cpuinfo_byte_arrary.at(4)).toInt();

               m_cpu_1_load = ((user_mode + nice_user_mode + system_mode) * 100) / (user_mode + nice_user_mode + system_mode + idle);
//               qWarning("%d, %d, %d, %d : %d \n", user_mode, nice_user_mode, system_mode, idle, m_cpu_1_load);
            }
         }

         // -------------------------------------------------------------------------------
         qfile.close();
      }
      else
      {
         qWarning("cannot open file (%s) \n", CPU_STAT_PATH);
         return -1;
      }
      fclose(file);
   }
   else
   {
      qWarning("cannot open file (%s) \n", CPU_STAT_PATH);
      return -1;
   }
   return 0;
}


// --------------------------------------------------------------------------
//  Name : getCpuNum()
//
//
// --------------------------------------------------------------------------
uint SystemInfo::getCpuNum()
{
   return m_cpu_num;
}

// --------------------------------------------------------------------------
//  Name : getCpuArch()
//
//
// --------------------------------------------------------------------------
QString SystemInfo::getCpuArch()
{
   return m_cpu_arch;
}

// --------------------------------------------------------------------------
//  Name : getCpuRev()
//
//
// --------------------------------------------------------------------------
QString SystemInfo::getCpuRev()
{
   return m_cpu_rev;
}

// --------------------------------------------------------------------------
//  Name : getCpuSerial()
//
//
// --------------------------------------------------------------------------
QString SystemInfo::getCpuSerial()
{
   return m_cpu_serial;
}

// --------------------------------------------------------------------------
//  Name : getTotalMem()
//
//
// --------------------------------------------------------------------------
uint SystemInfo::getTotalMem()
{
   return m_total_mem_kb;
}

// --------------------------------------------------------------------------
//  Name : getTotalMem()
//
//
// --------------------------------------------------------------------------
uint SystemInfo::getFreeMem()
{
   if (GetSystemMemory() != 0)
   {
   }
   return m_free_mem_kb;
}

// --------------------------------------------------------------------------
//  Name : getCpuFreq()
//
//
// --------------------------------------------------------------------------
uint SystemInfo::getCpuFreq()
{
   return m_cpu_cur_freq;
}

// --------------------------------------------------------------------------
//  Name : getMinFreq()
//
//
// --------------------------------------------------------------------------
uint SystemInfo::getMinFreq()
{
   return m_cpu_min_freq;
}

// --------------------------------------------------------------------------
//  Name : getMaxFreq()
//
//
// --------------------------------------------------------------------------
uint SystemInfo::getMaxFreq()
{
   return m_cpu_max_freq;
}

// --------------------------------------------------------------------------
//  Name : getAvailCpuFreq()
//
//
// --------------------------------------------------------------------------
QString SystemInfo::getAvailCpuFreq()
{
   return m_available_freq;
}


// --------------------------------------------------------------------------
//  Name : getCpuGovernors()
//
//
// --------------------------------------------------------------------------
QString SystemInfo::getCpuGovernors()
{
   return m_cur_goverors;
}

// --------------------------------------------------------------------------
//  Name : getCpuAvailGovernors()
//
//
// --------------------------------------------------------------------------
QString SystemInfo::getCpuAvailGovernors()
{
   return m_available_governors;
}

// --------------------------------------------------------------------------
//  Name : getIPaddress()
//
//
// --------------------------------------------------------------------------
QString SystemInfo::getIPaddress()
{
   return m_ipaddress;
}

// --------------------------------------------------------------------------
//  Name : getTemperatureValue()
//
//
// --------------------------------------------------------------------------
uint SystemInfo::getTemperatureValue()
{
   QString cur_tem;
   if (GetSysfs((char*) CPU_THERMAL_TEMP, cur_tem) == 0)
   {
      m_temperature = cur_tem.toInt() / 1000;
   }
   else
   {
//      m_temperature = (QRandomGenerator::global()->generate() % 100);
   }

   return m_temperature;
}

// --------------------------------------------------------------------------
//  Name : getCpuLoadValue()
//
//
// --------------------------------------------------------------------------
uint SystemInfo::getCpuLoadValue()
{
   if (GetCPULoad() != 0)
   {
//      m_cpu_load = (QRandomGenerator::global()->generate() % 100);
//      m_cpu_0_load = (QRandomGenerator::global()->generate() % 100);
//      m_cpu_1_load = (QRandomGenerator::global()->generate() % 100);
   }

   return m_cpu_load;
}

// --------------------------------------------------------------------------
//  Name : getCpu_0_LoadValue()
//
//
// --------------------------------------------------------------------------
uint SystemInfo::getCpu_0_LoadValue()
{
   return m_cpu_0_load;
}

// --------------------------------------------------------------------------
//  Name : getCpu_1_LoadValue()
//
//
// --------------------------------------------------------------------------
uint SystemInfo::getCpu_1_LoadValue()
{
   return m_cpu_1_load;
}


// --------------------------------------------------------------------------
//  Name : getMemUsedValue()
//
//
// --------------------------------------------------------------------------
uint SystemInfo::getMemUsedValue()
{
   if (GetSystemMemory() != 0)
   {
//      m_mem_used = (QRandomGenerator::global()->generate() % 100);
   }
   return m_mem_used;
}

// --------------------------------------------------------------------------
//  Name : getMemUsedValue()
//
//
// --------------------------------------------------------------------------
QString SystemInfo::getKernelVersion()
{
   return m_kernel_version;
}

// --------------------------------------------------------------------------
//  Name : getMemUsedValue()
//
//
// --------------------------------------------------------------------------
QString SystemInfo::getKernelCmdLine()
{
   return m_kernel_cmd_line;
}

// --------------------------------------------------------------------------
//  Name : getMemUsedValue()
//
//
// --------------------------------------------------------------------------
QString SystemInfo::getOSVersion()
{
   return m_os_version;
}


