package com.fueledbycaffeine.mivote.data

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.io.IOException
import java.security.GeneralSecurityException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class VoterDataStore(private val context: Context) {
  companion object {
    private const val VOTER_INFO_FILENAME = "voterInfo"
    private const val MASTER_KEY_ALIAS = "mivote-voterInfo"
    private val KEY_ENCRYPTION_SCHEME =
      EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV
    private val VALUE_ENCRYPTION_SCHEME =
      EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM

    private val DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE

    private const val PREF_KEY_VOTER_FIRST_NAME = "firstName"
    private const val PREF_KEY_VOTER_LAST_NAME = "lastName"
    private const val PREF_KEY_VOTER_BIRTHDATE_NAME = "birthdate"
    private const val PREF_KEY_VOTER_ZIPCODE_NAME = "zipcode"
  }

  var voterInfo: VoterInfo?
    get() {
      val prefs = sharedPreferences ?: return null
      val firstName = prefs.getString(PREF_KEY_VOTER_FIRST_NAME, null) ?: return null
      val lastName = prefs.getString(PREF_KEY_VOTER_LAST_NAME, null) ?: return null
      val birthdate = prefs.getString(PREF_KEY_VOTER_BIRTHDATE_NAME, null) ?: return null
      val zipcode = prefs.getString(PREF_KEY_VOTER_ZIPCODE_NAME, null) ?: return null
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
          .putString(PREF_KEY_VOTER_BIRTHDATE_NAME, DATE_FORMATTER.format(value.birthdate))
          .putString(PREF_KEY_VOTER_ZIPCODE_NAME, value.zipcode)
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
        // todo
        null
      } catch (e: IOException) {
        // todo
        null
      }
    }
}
