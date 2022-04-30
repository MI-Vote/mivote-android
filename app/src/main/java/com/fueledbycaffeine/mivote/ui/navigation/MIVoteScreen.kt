package com.fueledbycaffeine.mivote.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.ui.graphics.vector.ImageVector
import com.fueledbycaffeine.mivote.R

open class MIVoteScreen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
  object VoterInput : MIVoteScreen(
    "VoterInput",
    R.string.voter_input,
    Icons.Filled.Edit
  )

  object VoterRegistration : MIVoteScreen(
    "VoterRegistration",
    R.string.voter_registration,
    Icons.Filled.AccountCircle
  )

  object BallotStatus : MIVoteScreen(
    "BallotStatus",
    R.string.ballot_status,
    Icons.Filled.Email
  )
}
