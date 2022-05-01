package com.fueledbycaffeine.mivote.ui.voter.status.voterinfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.data.VoterInfo

@Composable
fun VoterInputScreen(
  voterInfo: MutableState<VoterInfo>,
  modifier: Modifier = Modifier,
  checkRegistration: (voterInfo: VoterInfo) -> Unit,
) {
  Column(
    modifier = modifier
  ) {
    VoterInfoForm(voterInfo)
    Spacer(modifier = Modifier.height(24.dp))
    TextButton(
      enabled = voterInfo.value.isValid(),
      onClick = {
        checkRegistration(voterInfo.value)
      },
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(text = stringResource(id = R.string.check_registration))
    }
  }
}
