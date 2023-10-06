package com.maxrzhe.newmax.data.remote.api

import android.util.Log
import com.maxrzhe.newmax.common.Constants.TAG
import com.maxrzhe.newmax.data.remote.dto.Article
import com.maxrzhe.newmax.data.remote.dto.TopHeadlinesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import java.util.Locale

class NewsServiceImpl(
  private val client: HttpClient
) : NewsService {
  override suspend fun getTopHeadlines(category: String): List<Article?> {
    return try {
      val response = client.get {
        val urlString = HttpRouts.createUrlString(
          HttpRouts.TOP_HEADLINES,
          mapOf(
            "category" to category,
            "country" to Locale.getDefault().country,
            "pageSize" to 100,
          )
        )
        Log.w(TAG, "**** URL **** $urlString")
        url(urlString)
      }
      val topHeadlines: TopHeadlinesResponse = response.body()
      topHeadlines.articles ?: emptyList()
    } catch (e: RedirectResponseException) {
      // 3xx responses
      Log.i(TAG, "Error: ${e.response.status.description}")
      throw e
    } catch (e: ClientRequestException) {
      // 4xx responses
      Log.i(TAG, "Error: ${e.response.status.description}")
      throw e
    } catch (e: ServerResponseException) {
      // 5xx responses
      Log.i(TAG, "Error: ${e.response.status.description}")
      throw e
    } catch (e: Exception) {
      Log.i(TAG, "Error: ${e.message}")
      throw e
    }
  }

}