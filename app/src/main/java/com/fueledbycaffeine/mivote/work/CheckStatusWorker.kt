package com.fueledbycaffeine.mivote.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy.KEEP
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Operation
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.fueledbycaffeine.mivote.data.VoterDataStore
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.michiganelections.api.model.Ballot
import io.michiganelections.api.model.Precinct
import io.michiganelections.api.service.ApiService
import timber.log.Timber
import java.time.LocalDate
import java.util.concurrent.TimeUnit

@HiltWorker
class CheckStatusWorker @AssistedInject constructor(
  @Assisted context: Context,
  @Assisted params: WorkerParameters,
  private val userDataStore: VoterDataStore,
  private val api: ApiService
) : CoroutineWorker(context, params) {
  companion object {
    const val DATA_IS_REGISTERED = "is_registered"
    const val DATA_BALLOT = "ballot"

    const val WORK_TAG = "CheckStatusWorker"
    val logger get() = Timber.tag(WORK_TAG)

    private val WORK_CONSTRAINTS = Constraints.Builder()
      .setRequiredNetworkType(NetworkType.CONNECTED)
      .build()

    fun runNow(context: Context): Operation {
      val work = OneTimeWorkRequestBuilder<CheckStatusWorker>()
        .setConstraints(WORK_CONSTRAINTS)
        .addTag(WORK_TAG)
        .build()
      return WorkManager.getInstance(context).enqueue(work)
    }

    fun scheduleRecurring(context: Context): Operation {
      val work = PeriodicWorkRequestBuilder<CheckStatusWorker>(8, TimeUnit.HOURS)
        .setConstraints(WORK_CONSTRAINTS)
        .addTag(WORK_TAG)
        .build()
      return WorkManager.getInstance(context).enqueueUniquePeriodicWork(WORK_TAG, KEEP, work)
    }
  }

  override suspend fun doWork(): Result {
    val voter = userDataStore.voterInfo
    if (voter == null) {
      logger.w("No voter info entered")
      return Result.success(workDataOf(DATA_IS_REGISTERED to false))
    }

    val registration = api.getVoter(voter.firstName, voter.lastName, voter.birthdate, voter.zipcode)
    val precinct = registration.precinct
    if (precinct == null) {
      logger.w("Voter registration not found!")
      return Result.success(workDataOf(DATA_IS_REGISTERED to false))
    }

    val ballot = when (registration.ballot) {
      false -> null
      else -> getBallot(precinct)
    }

    return Result.success(workDataOf(
      DATA_IS_REGISTERED to true,
      DATA_BALLOT to ballot?.mvicUrl,
    ))
  }

  private suspend fun getBallot(precinct: Precinct): Ballot? {
    val elections = api.getElections().results
    val election = elections.firstOrNull { it.date >= LocalDate.now() }
    return election?.let { api.getBallots(it.id, precinct.id).results.firstOrNull() }
  }
}
