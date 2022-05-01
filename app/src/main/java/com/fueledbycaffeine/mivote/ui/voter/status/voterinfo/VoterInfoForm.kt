package com.fueledbycaffeine.mivote.ui.voter.status.voterinfo

import android.app.DatePickerDialog
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
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
  voterInfoState: MutableState<VoterInfo>,
) {
  Column {
    NameField(
      hintText = R.string.first_name,
      name = voterInfoState.value.firstName,
      onValueChange = { voterInfoState.value = voterInfoState.value.copy(firstName = it) }
    )
    Spacer(modifier = Modifier.height(24.dp))

    NameField(
      hintText = R.string.last_name,
      name = voterInfoState.value.lastName,
      onValueChange = { voterInfoState.value = voterInfoState.value.copy(lastName = it) }
    )
    Spacer(modifier = Modifier.height(24.dp))

    BirthDateField(
      initialDate = voterInfoState.value.birthdate,
      dateChanged = { voterInfoState.value = voterInfoState.value.copy(birthdate = it)}
    )
    Spacer(modifier = Modifier.height(24.dp))

    ZipcodeField(
      zipcode = voterInfoState.value.zipcode,
      onZipcodeChange = { voterInfoState.value = voterInfoState.value.copy(zipcode = it) }
    )
  }
}

@Composable
fun NameField(
  @StringRes hintText: Int,
  name: String,
  onValueChange: (value: String) -> Unit,
  imeAction: ImeAction = ImeAction.Next,
) {
  val focusManager = LocalFocusManager.current
  TextField(
    value = name,
    onValueChange = { onValueChange(it) },
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
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = imeAction),
    keyboardActions = KeyboardActions(onNext = {
      focusManager.moveFocus(FocusDirection.Down)
    })
  )
}

@Composable
fun ZipcodeField(
  zipcode: String,
  onZipcodeChange: (zipCode: String) -> Unit,
  imeAction: ImeAction = ImeAction.Done,
) {
  val focusManager = LocalFocusManager.current
  TextField(
    value = zipcode,
    onValueChange = { onZipcodeChange(it) },
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
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = imeAction),
    keyboardActions = KeyboardActions(onDone = {
      focusManager.clearFocus()
    })
  )
}

// TODO: Improve this field
@Composable
fun BirthDateField(
  initialDate: LocalDate = LocalDate.now(),
  dateChanged: (date: LocalDate) -> Unit,
  imeAction: ImeAction = ImeAction.Next,
) {
  var date by remember { mutableStateOf(initialDate) }
  val datePickerDialog = DatePickerDialog(
    LocalContext.current,
    { _, year: Int, month: Int, dayOfMonth: Int ->
      date = LocalDate.of(year, month + 1, dayOfMonth)
      dateChanged(date)
    }, date.year, date.monthValue - 1, date.dayOfMonth
  )

  val focusManager = LocalFocusManager.current
  TextField(
    enabled = false,
    value = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(date),
    onValueChange = { },
    label = {
      CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
          text = stringResource(id = R.string.birthdate),
          style = MaterialTheme.typography.body2
        )
      }
    },
    textStyle = MaterialTheme.typography.body2,
    modifier = Modifier
      .fillMaxWidth()
      .clickable { datePickerDialog.show() },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = imeAction),
    keyboardActions = KeyboardActions(onNext = {
      focusManager.moveFocus(FocusDirection.Down)
    })
  )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MIVoteTheme {
    val voterInfo =
      remember {
        mutableStateOf(
          VoterInfo(
            "Joshua",
            "Friend",
            LocalDate.of(1991, 4, 1),
            "12345")
        )
      }
    VoterInfoForm(voterInfo)
  }
}
