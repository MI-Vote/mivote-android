package com.fueledbycaffeine.mivote.ui.voter

import androidx.annotation.StringRes
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.EmphasisAmbient
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.data.VoterInfo
import com.fueledbycaffeine.mivote.ui.MIVoteTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun VoterInfoForm(
  voterInfo: VoterInfo?,
  voterInfoState: MutableState<VoterInfo?> = remember { mutableStateOf(voterInfo) }
) {
  Column {
    val firstNameState = remember { mutableStateOf(voterInfoState.value?.firstName ?: "") }
    val lastNameState = remember { mutableStateOf(voterInfoState.value?.lastName ?: "") }
    val birthdateState = remember { mutableStateOf(voterInfoState.value?.birthdate) }
    val zipcodeState = remember { mutableStateOf(voterInfoState.value?.zipcode ?: "") }

    NameField(
      hintText = R.string.first_name,
      text = voterInfo?.firstName ?: "",
      textState = firstNameState
    )
    Spacer(modifier = Modifier.preferredHeight(24.dp))

    NameField(
      hintText = R.string.last_name,
      text = voterInfo?.lastName ?: "",
      textState = lastNameState
    )
    Spacer(modifier = Modifier.preferredHeight(24.dp))

    BirthDateField(
      date = voterInfo?.birthdate,
      dateState = birthdateState
    )
    Spacer(modifier = Modifier.preferredHeight(24.dp))

    ZipcodeField(
      zipcode = voterInfo?.zipcode ?: "",
      zipcodeState = zipcodeState
    )
  }
}

@Composable
fun NameField(
  @StringRes hintText: Int,
  text: String,
  textState: MutableState<String> = remember { mutableStateOf("") },
  imeAction: ImeAction = ImeAction.Next,
  onImeAction: () -> Unit = {}
) {
  TextField(
    value = text,
    onValueChange = { textState.value = it },
    label = {
      ProvideEmphasis(emphasis = EmphasisAmbient.current.medium) {
        Text(
          text = stringResource(id = hintText),
          style = MaterialTheme.typography.body2
        )
      }
    },
    textStyle = MaterialTheme.typography.body2,
    modifier = Modifier.fillMaxWidth(),
    keyboardType = KeyboardType.Text,
    imeAction = imeAction,
    onImeActionPerformed = { action, softKeyboardController ->
      if (action == ImeAction.Done) {
        softKeyboardController?.hideSoftwareKeyboard()
      }
      onImeAction()
    }
  )
}

@Composable
fun ZipcodeField(
  zipcode: String,
  zipcodeState: MutableState<String> = remember { mutableStateOf("") },
  imeAction: ImeAction = ImeAction.Done,
  onImeAction: () -> Unit = {}
) {
  TextField(
    value = zipcode,
    onValueChange = { zipcodeState.value = it },
    label = {
      ProvideEmphasis(emphasis = EmphasisAmbient.current.medium) {
        Text(
          text = stringResource(id = R.string.zipcode),
          style = MaterialTheme.typography.body2
        )
      }
    },
    textStyle = MaterialTheme.typography.body2,
    modifier = Modifier.fillMaxWidth(),
    keyboardType = KeyboardType.Number,
    imeAction = imeAction,
    onImeActionPerformed = { action, softKeyboardController ->
      if (action == ImeAction.Done) {
        softKeyboardController?.hideSoftwareKeyboard()
      }
      onImeAction()
    }
  )
}

@Composable
fun BirthDateField(
  date: LocalDate?,
  dateState: MutableState<LocalDate?> = remember { mutableStateOf(date) },
) {
  val dateText = when (val ds = dateState.value) {
    null -> ""
    else -> DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(ds)
  }

  TextField(
    value = dateText,
    onValueChange = { /* Does not allow direct editing */ },
    label = {
      ProvideEmphasis(emphasis = EmphasisAmbient.current.medium) {
        Text(
          text = stringResource(id = R.string.birthdate),
          style = MaterialTheme.typography.body2
        )
      }
    },
    textStyle = MaterialTheme.typography.body2,
    modifier = Modifier.fillMaxWidth(),
  )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MIVoteTheme {
    VoterInfoForm(
      VoterInfo("Joshua", "Friend", LocalDate.of(1991, 4, 1), "12345")
    )
  }
}
