package com.fueledbycaffeine.mivote.ui.voter.voterinfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.data.VoterInfo
import java.time.LocalDate

@Composable
fun VoterInputScreen(
  modifier: Modifier = Modifier,
  checkRegistration: (voterInfo: VoterInfo) -> Unit,
) {
  // TODO: Gets an error below of "pending composition has not been applied"
  //  when using rememberSaveable?
  val localVoterInfo = remember {
    mutableStateOf(
      VoterInfo(
        firstName = "",
        lastName = "",
        birthdate = LocalDate.now(),
        zipcode = ""
      )
    )
  }
  Column(
    modifier = modifier
  ) {
    VoterInfoForm(localVoterInfo)
    Spacer(modifier = Modifier.height(24.dp))
    TextButton(
      onClick = {
        checkRegistration(localVoterInfo.value)
      },
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(text = stringResource(id = R.string.check_registration))
    }
  }
}