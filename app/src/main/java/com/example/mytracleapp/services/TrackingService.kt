package com.example.mytracleapp.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.example.mytracleapp.R
import com.example.mytracleapp.other.Constants.ACTION_PAUSE_SERVICE
import com.example.mytracleapp.other.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.example.mytracleapp.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.example.mytracleapp.other.Constants.ACTION_STOP_SERVICE
import com.example.mytracleapp.other.Constants.NOTIFICATION_CHANNEL_ID
import com.example.mytracleapp.other.Constants.NOTIFICATION_CHANNEL_NAME
import com.example.mytracleapp.other.Constants.NOTIFICATION_ID
import com.example.mytracleapp.ui.MainActivity
import timber.log.Timber

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 06/09/2022.
 */
class TrackingService : LifecycleService() {

    var isFirstRun = true
    override fun onCreate() {
        super.onCreate()
        Timber.d("OnCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.d("onStartCommand")
        intent?.let {
            when (it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    Timber.d("onStartCommand ACTION_START_OR_RESUME_SERVICE")

                    if (isFirstRun) {
                        startForegroundService()
                        isFirstRun = false
                    } else {
                        Timber.d("Resuming service...")
                    }
                }
                ACTION_PAUSE_SERVICE -> {
                    Timber.d("Paused service")
                }
                ACTION_STOP_SERVICE -> {
                    Timber.d("Stopped service")
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startForegroundService() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_directions_run_black_24)
            .setContentTitle("Running App")
            .setContentText("00:00:00")
            .setContentIntent(getMainActivityPendingIntent())

        startForeground(NOTIFICATION_ID, notificationBuilder.build())
        Timber.d("Ishladi")
    }

    private fun getMainActivityPendingIntent() = PendingIntent.getActivity(
        this,
        0,
        Intent(this, MainActivity::class.java).also {
            it.action = ACTION_SHOW_TRACKING_FRAGMENT
        },
        FLAG_UPDATE_CURRENT
    )

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }
}