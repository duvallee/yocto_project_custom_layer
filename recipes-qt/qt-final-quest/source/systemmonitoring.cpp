// ****************************************************************
//
// File: systemmonitoring.cpp
//
// Written by duvallee in 2020
//
// ****************************************************************
#include "systemmonitoring.h"

#if 0
// --------------------------------------------------------------------------
#define CPU_THERMAL_CLASS_ROOT_PATH                      "/sys/class/thermal/thermal_zone0"
#define CPU_THERMAL_AVAILABLE_POLICES                    "available_policies"
#define CPU_THERMAL_MODE                                 "mode"
#define CPU_THERMAL_TYPE                                 "type"
#define CPU_THERMAL_TRIP_TYPE                            "trip_point_0_type"
#define CPU_THERMAL_TRIP_TEMP                            "trip_point_0_temp"
#define CPU_THERMAL_TRIP_HYST                            "trip_point_0_hyst"

#define CPU_THERMAL_TEMP                                 "temp"
#endif

// --------------------------------------------------------------------------
//  Name : SystemMonitoring()
//
//
// --------------------------------------------------------------------------
SystemMonitoring::SystemMonitoring(QObject *parent) : QObject(parent)
{


}

// --------------------------------------------------------------------------
//  Name : getCpuInfo()
//
//
// --------------------------------------------------------------------------
// QString SystemInfo::getCpuInfo()
// {
//   return m_processor_info;
// }




