// ****************************************************************
//
// File: StopWatchControl.h
//
// Written by duvallee in 2020
//
// ****************************************************************


#ifndef STOPWATCHCONTROL_H_
#define STOPWATCHCONTROL_H_

#include <QObject>
#include <QString>
#include <QElapsedTimer>

class StopWatchControl : public QObject
{
   Q_OBJECT

   // ----------------------------------------------------------------------------------
   Q_PROPERTY(QString getStopWatchTimerValue READ getStopWatchTimerValue)
   Q_PROPERTY(uint StartStopWatch READ StartStopWatch)
   Q_PROPERTY(uint StopStopWatch READ StopStopWatch)
   Q_PROPERTY(uint ReStartStopWatch READ ReStartStopWatch)

   Q_PROPERTY(uint getStopWatchRunnig READ getStopWatchRunnig NOTIFY getStopWatchRunnigChanged)
   Q_PROPERTY(uint getStopWatchReset READ getStopWatchReset NOTIFY getStopWatchResetChanged)

   Q_PROPERTY(QString getStopWatchTimerRecording READ getStopWatchTimerRecording)

// ----------------------------------------------------------------------------------
public:
   explicit StopWatchControl(QObject* parent = nullptr);

   // ----------------------------------------------------------------------------------
   QString getStopWatchTimerValue();
   uint StartStopWatch();
   uint StopStopWatch();
   uint ReStartStopWatch();

   uint getStopWatchRunnig();
   uint getStopWatchReset();

   QString getStopWatchTimerRecording();

// ----------------------------------------------------------------------------------
signals:
   void getStopWatchRunnigChanged();
   void getStopWatchResetChanged();

private:
   // ----------------------------------------------------------------------------------
   qint64 m_StopWatchTimer;
   uint m_StopWatchTimerRunning;

   uint m_StopWatchTimerHitoryIndex;

   QElapsedTimer m_StopWatchElapsedTimer;
};

#endif   // STOPWATCHCONTROL_H_


