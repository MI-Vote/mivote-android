package com.fueledbycaffeine.mivote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.fueledbycaffeine.mivote.ui.navigation.MIVoteNavigation
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
import com.fueledbycaffeine.mivote.work.CheckStatusWorker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      MIVoteTheme {
        MIVoteNavigation()
      }
    }

//    val constraints = Constraints.Builder()
//      .setRequiredNetworkType(NetworkType.CONNECTED)
//      .build()
    val work = OneTimeWorkRequestBuilder<CheckStatusWorker>()
//      .setConstraints(constraints)
      .build()
    WorkManager.getInstance(this).enqueue(work)
  }
}

