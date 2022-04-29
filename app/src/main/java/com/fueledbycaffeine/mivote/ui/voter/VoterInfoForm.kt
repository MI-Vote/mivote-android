package com.fueledbycaffeine.mivote.ui.voter

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.data.VoterInfo
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
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
      textState = firstNameState
    )
    Spacer(modifier = Modifier.height(24.dp))

    NameField(
      hintText = R.string.last_name,
      textState = lastNameState
    )
    Spacer(modifier = Modifier.height(24.dp))

    BirthDateField(
      date = voterInfo?.birthdate,
      dateState = birthdateState
    )
    Spacer(modifier = Modifier.height(24.dp))

    ZipcodeField(
      zipcodeState = zipcodeState
    )
  }
}

@Composable
fun NameField(
  @StringRes hintText: Int,
//  text: String,
  textState: MutableState<String> = remember { mutableStateOf("") },
//  imeAction: ImeAction = ImeAction.Next,
//  onImeAction: () -> Unit = {}
) {
  TextField(
    value = textState.value,
    onValueChange = { textState.value = it },
    label = {
      CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
          text = stringResource(id = hintText),
          style = MaterialTheme.typography.body2
        )
      }
    },
    textStyle = MaterialTheme.typography.body2,
    modifier = Modifier.fillMaxWidth(),
//    keyboardType = KeyboardType.Text,
//    imeAction = imeAction,
//    onImeActionPerformed = { action, softKeyboardController ->
//      if (action == ImeAction.Done) {
//        softKeyboardController?.hideSoftwareKeyboard()
//      }
//      onImeAction()
//    }
  )
}

@Composable
fun ZipcodeField(
//  zipcode: String,
  zipcodeState: MutableState<String> = remember { mutableStateOf("") },
//  imeAction: ImeAction = ImeAction.Done,
//  onImeAction: () -> Unit = {}
) {
  TextField(
    value = zipcodeState.value,
    onValueChange = { zipcodeState.value = it },
    label = {
      CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
          text = stringResource(id = R.string.zipcode),
          style = MaterialTheme.typography.body2
        )
      }
    },
    textStyle = MaterialTheme.typography.body2,
    modifier = Modifier.fillMaxWidth(),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//    imeAction = imeAction,
//    onImeActionPerformed = { action, softKeyboardController ->
//      if (action == ImeAction.Done) {
//        softKeyboardController?.hideSoftwareKeyboard()
//      }
//      onImeAction()
//    }
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
      CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
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
