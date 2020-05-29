// ****************************************************************
//
// File: StopWatchControl.cpp
//
// Written by duvallee in 2020
//
// ****************************************************************

#include "StopWatchControl.h"

// --------------------------------------------------------------------------
//  Name : StopWatchControl()
//         
// 
// --------------------------------------------------------------------------
StopWatchControl::StopWatchControl(QObject *parent) : QObject(parent)
{
   m_StopWatchTimer = 0;
   m_StopWatchTimerRunning = 0;
   m_StopWatchTimerHitoryIndex = 0;
}

// --------------------------------------------------------------------------
//  Name : getStopWatchTimerValue()
//         
// 
// --------------------------------------------------------------------------
QString StopWatchControl::getStopWatchTimerValue()
{
   QString stopwatch_string;
   qint64 timer_count = 0;
   qint64 elapsed_timer_count = 0;

   if (m_StopWatchTimerRunning)
   {
      elapsed_timer_count = m_StopWatchElapsedTimer.elapsed();
      timer_count = m_StopWatchTimer + elapsed_timer_count;
   }
   else
   {
      timer_count = m_StopWatchTimer;
   }

   stopwatch_string.sprintf("%02d : %02d . %03d", (int)((timer_count / (1000 * 60)) % 60), (int)((timer_count / 1000) % 60), (int)(timer_count % 1000));
   return stopwatch_string;
}

// --------------------------------------------------------------------------
//  Name : StartStopWatch()
//         
// 
// --------------------------------------------------------------------------
uint StopWatchControl::StartStopWatch()
{
   m_StopWatchElapsedTimer.start();
   m_StopWatchTimerRunning = 1;
   return 0;
}

// --------------------------------------------------------------------------
//  Name : StopStopWatch()
//         
// 
// --------------------------------------------------------------------------
uint StopWatchControl::StopStopWatch()
{
   qint64 elapsed_timer_count = 0;
   if (m_StopWatchTimerRunning)
   {
      elapsed_timer_count = m_StopWatchElapsedTimer.elapsed();
      m_StopWatchTimer += elapsed_timer_count;
   }

   m_StopWatchTimerRunning = 0;
   return 0;
}

// --------------------------------------------------------------------------
//  Name : ReStartStopWatch()
//         
// 
// --------------------------------------------------------------------------
uint StopWatchControl::ReStartStopWatch()
{
   m_StopWatchTimerRunning = 0;
   m_StopWatchTimer = 0;
   m_StopWatchTimerHitoryIndex = 0;

   return 0;
}


// --------------------------------------------------------------------------
//  Name : getStopWatchRunnig()
//         
// 
// --------------------------------------------------------------------------
uint StopWatchControl::getStopWatchRunnig()
{
   if (m_StopWatchTimerRunning)
   {
      return 1;
   }
   return 0;
}

// --------------------------------------------------------------------------
//  Name : getStopWatchReset()
//         
// 
// --------------------------------------------------------------------------
uint StopWatchControl::getStopWatchReset()
{
   if (m_StopWatchTimerRunning)
   {
      return 1;
   }

   if (m_StopWatchTimer)
   {
      return 1;
   }

   return 0;
}


// --------------------------------------------------------------------------
//  Name : getStopWatchTimerRecording()
//         
// 
// --------------------------------------------------------------------------
QString StopWatchControl::getStopWatchTimerRecording()
{
   QString stopwatch_string;
   qint64 timer_count = 0;
   qint64 elapsed_timer_count = 0;

   if (m_StopWatchTimerRunning)
   {
      elapsed_timer_count = m_StopWatchElapsedTimer.elapsed();
      timer_count = m_StopWatchTimer + elapsed_timer_count;
   }
   else
   {
      timer_count = m_StopWatchTimer;
   }

   m_StopWatchTimerHitoryIndex++;
   stopwatch_string.sprintf("%03d         %02d:%02d.%03d", m_StopWatchTimerHitoryIndex, (int)((timer_count / (1000 * 60)) % 60), (int)((timer_count / 1000) % 60), (int)(timer_count % 1000));
   return stopwatch_string;
}


