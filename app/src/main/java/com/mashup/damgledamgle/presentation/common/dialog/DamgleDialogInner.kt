package com.mashup.damgledamgle.presentation.common.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.ui.theme.*

@Composable
fun DamgleDialogTwoButtonInner(
        title: String,
        description: String,
        firstButtonText: String,
        firstButtonAction: () -> Unit,
        secondButtonText: String,
        secondButtonAction: () -> Unit,
) {
    Column(
            modifier = Modifier
                    .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
                text = title,
                color = Black,
                style = pretendardTextStyle.bodyMedium18,
                textAlign = Center,
                maxLines = 2,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
                text = description,
                style = pretendardTextStyle.bodyMedium13,
                color = Gray600,
                textAlign = Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Column(
                modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .width(288.dp),
        ) {
            Text(
                    modifier = Modifier
                            .clickable { firstButtonAction() }
                            .padding(vertical = 13.dp)
                            .fillMaxWidth(),
                    textAlign = Center,
                    style = pretendardTextStyle.bodySemibold16,
                    text = firstButtonText,
                    color = Black
            )
            Text(
                    modifier = Modifier
                            .clickable { secondButtonAction() }
                            .padding(vertical = 13.dp)
                            .fillMaxWidth(),
                    textAlign = Center,
                    style = pretendardTextStyle.bodySemibold16,
                    text = secondButtonText,
                    color = Black
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun DamgleDialogOneButtonInner(
    title: String,
    description: String,
    firstButtonText: String,
    firstButtonAction: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = title,
            color = Black,
            style = pretendardTextStyle.bodyMedium18,
            maxLines = 1,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = description,
            style = pretendardTextStyle.bodyMedium13,
            color = Gray600,
            textAlign = Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .width(288.dp),
        ) {
            Text(
                modifier = Modifier
                    .clickable { firstButtonAction() }
                    .padding(vertical = 13.dp)
                    .fillMaxWidth(),
                textAlign = Center,
                style = pretendardTextStyle.bodySemibold16,
                text = firstButtonText,
                color = Black
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

