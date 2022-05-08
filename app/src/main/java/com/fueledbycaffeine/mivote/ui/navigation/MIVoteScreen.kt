package com.fueledbycaffeine.mivote.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.fueledbycaffeine.mivote.R

open class MIVoteScreen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
  object RegistrationInput : MIVoteScreen(
    "VoterRegistration",
    R.string.voter_registration,
    Icons.Filled.AccountCircle
  )

  object Home : MIVoteScreen(
    "Home",
    R.string.voter_registration,
    Icons.Filled.AccountCircle
  ) {
    object RegistrationInfo : MIVoteScreen(
      "RegistrationInfo",
      R.string.voter_registration,
      Icons.Filled.AccountCircle
    )

    object BallotStatus : MIVoteScreen(
      "BallotStatus",
      R.string.ballot_status,
      Icons.Filled.Email
    )

    object Elections : MIVoteScreen(
      "Elections",
      R.string.elections,
      Icons.Filled.Home
    )
  }
}
