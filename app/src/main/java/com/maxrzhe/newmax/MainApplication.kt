package com.maxrzhe.newmax

import android.app.Application
import com.maxrzhe.newmax.data.di.dataDiModule
import com.maxrzhe.newmax.di.appDiModule
import com.maxrzhe.newmax.domain.di.domainDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@MainApplication)
      modules(appDiModule, dataDiModule, domainDiModule)
    }
  }
}