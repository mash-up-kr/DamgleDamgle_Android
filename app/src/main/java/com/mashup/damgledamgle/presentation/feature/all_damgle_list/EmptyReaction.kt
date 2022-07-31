package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.damgledamgle.R

@Preview
@Composable
fun EmptyReaction() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.img_no_reaction),
            contentDescription = null,
            modifier = Modifier
                .wrapContentSize()
        )
    }
}
