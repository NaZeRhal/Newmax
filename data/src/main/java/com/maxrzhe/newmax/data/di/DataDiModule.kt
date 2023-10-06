package com.maxrzhe.newmax.data.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.maxrzhe.newmax.data.database.NewsDatabase
import com.maxrzhe.newmax.data.local.NewsDataSource
import com.maxrzhe.newmax.data.local.NewsDataSourceImpl
import com.maxrzhe.newmax.data.remote.api.NewsService
import com.maxrzhe.newmax.data.remote.api.NewsServiceImpl
import com.maxrzhe.newmax.data.repository.NewsRepositoryImpl
import com.maxrzhe.newmax.data_api.repository.NewsRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataDiModule = module {
  single {
    HttpClient(Android) {
      install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
      }
      install(ContentNegotiation) {
        json(Json {
          prettyPrint = true
          isLenient = true
          encodeDefaults = true
        })
      }
    }
  }

  singleOf(::NewsServiceImpl).bind(NewsService::class)

  single<SqlDriver> {
    AndroidSqliteDriver(
      schema = NewsDatabase.Schema,
      context = androidContext(),
      name = "news_database.db"
    )
  }

  single<NewsDataSource> {
    val db = NewsDatabase(driver = get())
    NewsDataSourceImpl(db = db)
  }

  singleOf(::NewsRepositoryImpl).bind(NewsRepository::class)
}