package com.mashup.damgledamgle.presentation.feature.home.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.damgledamgle.R

@Composable
fun PutStoryBox(
        modifier: Modifier = Modifier,
        backGroundColor: Color,
        textColor: Color
) {
    Card(
            modifier = Modifier
                    .height(320.dp)
                    .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = backGroundColor
    ) {
        var text by rememberSaveable { mutableStateOf("") }
        TextField(
                modifier = Modifier
                        .fillMaxSize(),
                value = text,
                onValueChange = {
                    text = it
                },
                colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = backGroundColor,
                        textColor = textColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                ),
                label = { Text(text = stringResource(id = R.string.home_bottomsheet_leave_your_own_story)) }
        )
    }
}
