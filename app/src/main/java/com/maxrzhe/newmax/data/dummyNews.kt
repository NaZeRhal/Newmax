package com.maxrzhe.newmax.data

import com.maxrzhe.newmax.R
import com.maxrzhe.newmax.screens.news.Article
import com.maxrzhe.newmax.screens.topics.Categories

val dummyNews = listOf(
    Article(
      id = 0,
      topic = Categories.POLITICS.title,
      title = "The latest situation in the presidential election",
      text = "",
      image = R.drawable.dummy_politics1
    ),
    Article(
      id = 1,
      topic = Categories.FASHION.title,
      title = "Creating Color Palette from world around you",
      text = "",
      image = R.drawable.dummy_colors1
    ),
    Article(
      id = 2,
      topic = Categories.ART.title,
      title = "An updated daily front page",
      text = "",
      image = R.drawable.dummy_art1
    ),
    Article(
      id = 3,
      topic = Categories.ART.title,
      title = "A Simple Trick For Creating Color Palettes Quickly",
      text = "",
      image = R.drawable.dummy_art2
    ),
    Article(
      id = 4,
      topic = Categories.ART.title,
      title = "Six steps to creating a color palette",
      text = "",
      image = R.drawable.dummy_ui1
    ),
  )