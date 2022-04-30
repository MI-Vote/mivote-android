package com.fueledbycaffeine.mivote.ui.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
  CircularProgressIndicator(
    modifier = modifier
      .padding(dimensionResource(id = R.dimen.loading_indicator_top_margin))
  )
}

@Preview
@Composable
fun PreviewLoadingIndicator() {
  MIVoteTheme {
    LoadingIndicator()
  }
}
