package com.fueledbycaffeine.mivote.data

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme
import androidx.security.crypto.EncryptedSharedPreferences.PrefValueEncryptionScheme
import androidx.security.crypto.MasterKey
import java.io.IOException
import java.security.GeneralSecurityException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class VoterDataStore(private val context: Context) {
  companion object {
    private const val VOTER_INFO_FILENAME = "voterInfo"
    private const val MASTER_KEY_ALIAS = "mivote-voterInfo"
    private val KEY_ENCRYPTION_SCHEME = PrefKeyEncryptionScheme.AES256_SIV
    private val VALUE_ENCRYPTION_SCHEME = PrefValueEncryptionScheme.AES256_GCM

    private val DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE

    private const val PREF_KEY_VOTER_FIRST_NAME = "firstName"
    private const val PREF_KEY_VOTER_LAST_NAME = "lastName"
    private const val PREF_KEY_VOTER_BIRTHDATE = "birthdate"
    private const val PREF_KEY_VOTER_ZIPCODE = "zipcode"

    private const val PREF_KEY_BALLOT_PREVIEW_AVAILABLE = "ballotPreviewAvailable"
    private const val PREF_KEY_ABSENTEE_APPLICATION_RECEIVED = "absenteeApplicationReceived"
    private const val PREF_KEY_ABSENTEE_BALLOT_SENT = "absenteeBallotSent"
    private const val PREF_KEY_ABSENTEE_BALLOT_RECEIVED = "absenteeBallotReceived"
  }

  var voterInfo: VoterInfo?
    get() {
      val prefs = sharedPreferences ?: return null
      val firstName = prefs.getString(PREF_KEY_VOTER_FIRST_NAME, null) ?: return null
      val lastName = prefs.getString(PREF_KEY_VOTER_LAST_NAME, null) ?: return null
      val birthdate = prefs.getString(PREF_KEY_VOTER_BIRTHDATE, null) ?: return null
      val zipcode = prefs.getString(PREF_KEY_VOTER_ZIPCODE, null) ?: return null
      return VoterInfo(
        firstName,
        lastName,
        DATE_FORMATTER.parse(birthdate, LocalDate::from),
        zipcode
      )
    }
    set(value) {
      if (value == null) {
        context.deleteSharedPreferences(VOTER_INFO_FILENAME)
      } else {
        val prefs = sharedPreferences ?: return
        prefs.edit()
          .putString(PREF_KEY_VOTER_FIRST_NAME, value.firstName)
          .putString(PREF_KEY_VOTER_LAST_NAME, value.lastName)
          .putString(PREF_KEY_VOTER_BIRTHDATE, DATE_FORMATTER.format(value.birthdate))
          .putString(PREF_KEY_VOTER_ZIPCODE, value.zipcode)
          .apply()
      }
    }

  var registrationCache: VoterRegistrationSummary?
    get() {
      return when (voterInfo) {
        null -> null
        else -> {
          val prefs = sharedPreferences ?: return null
          val ballotPreviewAvailable = prefs.getBoolean(PREF_KEY_BALLOT_PREVIEW_AVAILABLE, false)
          val absenteeApplicationReceived = prefs.getString(PREF_KEY_ABSENTEE_APPLICATION_RECEIVED, null)
          val absenteeBallotSent = prefs.getString(PREF_KEY_ABSENTEE_BALLOT_SENT, null)
          val absenteeBallotReceived = prefs.getString(PREF_KEY_ABSENTEE_BALLOT_RECEIVED, null)
          VoterRegistrationSummary(
            ballotPreviewAvailable,
            DATE_FORMATTER.parse(absenteeApplicationReceived, LocalDate::from),
            DATE_FORMATTER.parse(absenteeBallotSent, LocalDate::from),
            DATE_FORMATTER.parse(absenteeBallotReceived, LocalDate::from),
          )
        }
      }
    }
    set(value) {
      if (value != null) {
        val prefs = sharedPreferences ?: return
        prefs.edit()
          .putBoolean(PREF_KEY_BALLOT_PREVIEW_AVAILABLE, value.ballotPreviewAvailable)
          .putString(PREF_KEY_ABSENTEE_APPLICATION_RECEIVED, DATE_FORMATTER.format(value.absenteeApplicationReceived))
          .putString(PREF_KEY_ABSENTEE_BALLOT_SENT, DATE_FORMATTER.format(value.absenteeBallotSent))
          .putString(PREF_KEY_ABSENTEE_BALLOT_RECEIVED, DATE_FORMATTER.format(value.absenteeApplicationReceived))
          .apply()
      }
    }

  private val sharedPreferences: SharedPreferences?
    get() {
      val keyGenParameterSpec = KeyGenParameterSpec
        .Builder(
          MASTER_KEY_ALIAS,
          KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        .setKeySize(256)
        .build()

      val masterKeyAlias = MasterKey.Builder(context, MASTER_KEY_ALIAS)
        .setKeyGenParameterSpec(keyGenParameterSpec)
        .build()

      return try {
        EncryptedSharedPreferences.create(
          context,
          VOTER_INFO_FILENAME,
          masterKeyAlias,
          KEY_ENCRYPTION_SCHEME,
          VALUE_ENCRYPTION_SCHEME
        )
      } catch (e: GeneralSecurityException) {
        null
      } catch (e: IOException) {
        null
      }
    }
}
