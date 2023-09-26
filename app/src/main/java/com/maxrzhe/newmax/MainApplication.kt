package com.maxrzhe.newmax

import android.app.Application
import com.maxrzhe.newmax.di.appDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@MainApplication)
      modules(appDiModule)
    }
  }
}