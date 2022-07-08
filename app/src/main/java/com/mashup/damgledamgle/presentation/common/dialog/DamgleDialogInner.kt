package com.mashup.damgledamgle.presentation.common.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.ui.theme.Black
import com.mashup.damgledamgle.ui.theme.Gray600

@Composable
fun DamgleDialogTwoButtonInner(
        title: String,
        description: String,
        firstButtonText: String,
        firstButtonAction: () -> Unit,
        secondButtonText: String,
        secondButtonAction: () -> Unit,
) {
    Box(
            modifier = Modifier
                    .padding(16.dp)
    ) {
        Column(
                modifier = Modifier
                        .wrapContentWidth()
        ) {
            Text(
                    text = title,
                    color = Black,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                            .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                    text = description,
                    fontSize = 13.sp,
                    color = Black,
                    modifier = Modifier
                            .align(Alignment.CenterHorizontally),
            )
        }
    }
    Divider(thickness = 1.dp, color = Gray600)
    Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                    .wrapContentWidth()
                    .height(IntrinsicSize.Min)
    ) {
        Text(
                modifier = Modifier
                        .clickable { firstButtonAction() }
                        .padding(horizontal = 19.dp, vertical = 11.dp),
                fontSize = 17.sp,
                text = firstButtonText,
                color = Color.Blue
        )
        Divider(
                modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight(),
                color = Gray600
        )
        Text(
                modifier = Modifier
                        .clickable { secondButtonAction() }
                        .padding(horizontal = 19.dp, vertical = 11.dp),
                fontSize = 17.sp,
                text = secondButtonText,
                color = Color.Blue
        )
    }

}
