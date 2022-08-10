package com.mashup.damgledamgle.presentation.feature.all_damgle_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.R

@Preview
@Composable
fun EmptyReaction() {
    Column {
        Box {
            Image(
                painter = painterResource(id = R.drawable.img_no_reaction),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
    }
}
