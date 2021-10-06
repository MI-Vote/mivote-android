package com.fueledbycaffeine.mivote.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.graphics.vector.ImageVector
import com.fueledbycaffeine.mivote.R

open class MIVoteScreen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
  object VoterRegistration : MIVoteScreen(
    "VoterRegistration",
    R.string.voter_registration,
    Icons.Filled.AccountCircle
  )
}
