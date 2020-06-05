// ****************************************************************
//
// File: SystemInfo.h
//
// Written by duvallee in 2020
//
// ****************************************************************
#ifndef SYSTEMINFO_H_
#define SYSTEMINFO_H_

#include <QObject>
#include <QString>
#include <QFile>

// ==================================================================================
class SystemInfo : public QObject
{
   Q_OBJECT

   // -------------------------------------------------------------------------------
   Q_PROPERTY(uint getCpuNum READ getCpuNum CONSTANT)
   Q_PROPERTY(QString getCpuArch READ getCpuArch CONSTANT)
   Q_PROPERTY(QString getCpuRev READ getCpuRev CONSTANT)
   Q_PROPERTY(QString getCpuSerial READ getCpuSerial CONSTANT)

   Q_PROPERTY(uint getCpuFreq READ getCpuFreq CONSTANT)
   Q_PROPERTY(uint getMinFreq READ getMinFreq CONSTANT)
   Q_PROPERTY(uint getMaxFreq READ getMaxFreq CONSTANT)

   Q_PROPERTY(QString getAvailCpuFreq READ getAvailCpuFreq CONSTANT)

   Q_PROPERTY(QString getCpuGovernors READ getCpuGovernors CONSTANT)
   Q_PROPERTY(QString getCpuAvailGovernors READ getCpuAvailGovernors CONSTANT)

   Q_PROPERTY(uint getTotalMem READ getTotalMem CONSTANT)
   Q_PROPERTY(uint getFreeMem READ getFreeMem CONSTANT)

   Q_PROPERTY(QString getIPaddress READ getIPaddress CONSTANT)

   // -------------------------------------------------------------------------------
   Q_PROPERTY(uint getTemperatureValue READ getTemperatureValue)
   Q_PROPERTY(uint getCpuLoadValue READ getCpuLoadValue)
   Q_PROPERTY(uint getCpu_0_LoadValue READ getCpu_0_LoadValue)
   Q_PROPERTY(uint getCpu_1_LoadValue READ getCpu_1_LoadValue)
   Q_PROPERTY(uint getMemUsedValue READ getMemUsedValue)

   // -------------------------------------------------------------------------------
   Q_PROPERTY(QString getKernelVersion READ getKernelVersion CONSTANT)
   Q_PROPERTY(QString getKernelCmdLine READ getKernelCmdLine CONSTANT)
   Q_PROPERTY(QString getOSVersion READ getOSVersion CONSTANT)

// ==================================================================================
public:
   explicit SystemInfo(QObject* parent = nullptr);

   // ----------------------------------------------------------------------------------
   uint getCpuNum();
   QString getCpuArch();
   QString getCpuRev();
   QString getCpuSerial();

   uint getCpuFreq();
   uint getMinFreq();
   uint getMaxFreq();

   QString getAvailCpuFreq();

   QString getCpuGovernors();
   QString getCpuAvailGovernors();

   uint getTotalMem();
   uint getFreeMem();

   QString getIPaddress();

   // ----------------------------------------------------------------------------------
   uint getTemperatureValue();
   uint getCpuLoadValue();
   uint getCpu_0_LoadValue();
   uint getCpu_1_LoadValue();
   uint getMemUsedValue();

   // -------------------------------------------------------------------------------
   QString getKernelVersion();
   QString getKernelCmdLine();
   QString getOSVersion();


// ==================================================================================
signals:

// ==================================================================================
private:
   // ----------------------------------------------------------------------------------
   uint m_cpu_num;
   QString m_cpu_arch;
   QString m_cpu_rev;
   QString m_cpu_serial;

   uint m_cpu_cur_freq;
   uint m_cpu_max_freq;
   uint m_cpu_min_freq;

   QString m_available_freq;
   QString m_available_governors;
   QString m_cur_goverors;

   QString m_ipaddress;

   uint m_total_mem_kb;
   uint m_free_mem_kb;
   uint m_availed_mem_kb;

   //
   uint m_temperature;
   uint m_cpu_load;
   uint m_cpu_0_load;
   uint m_cpu_1_load;
   uint m_mem_used;

   //
   QString m_kernel_cmd_line;
   QString m_kernel_version;
   QString m_os_version;

   // ----------------------------------------------------------------------------------
   int GetSysfs(char*, QString&);
   int GetFile(char*, QString&);

   // ----------------------------------------------------------------------------------
   int GetSystemInformation();
   int GetSystemMemory();
   int GetCPULoad();

};

#endif   // SYSTEMINFO_H_

