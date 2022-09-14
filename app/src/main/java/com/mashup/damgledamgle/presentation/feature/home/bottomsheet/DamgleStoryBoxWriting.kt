package com.mashup.damgledamgle.presentation.feature.home.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.damgledamgle.R
import com.mashup.damgledamgle.presentation.component.ErrorText
import com.mashup.damgledamgle.ui.theme.*

@Preview
@Composable
fun DamgleStoryBoxPreview() {
    DamgleStoryBoxWriting(textColor = Gray900, text = "textgdsgdfgdfbdfbdfbdfbdfbfdbdfbdfbdfbdfbdfbdfbdf", onTextChange = { })
}

@Composable
fun DamgleStoryBoxWriting(
    modifier: Modifier = Modifier,
    textColor: Color,
    text: String,
    onTextChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .height(340.dp)
            .width(328.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_damgle),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(340.dp)
                .width(328.dp),
        )
        var textErrorState by rememberSaveable { mutableStateOf(false) }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = SpaceBetween
        ) {
            val maxTextFieldChar = 100
            val maxLine = 6
            Column {
                BasicTextField(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(22.dp, 24.dp),
                    cursorBrush = SolidColor(textColor),
                    value = text,
                    onValueChange = {
                        when {
                            it.lines().count() > maxLine -> {
                                return@BasicTextField
                            }
                            it.length <= maxTextFieldChar -> {
                                onTextChange(it)
                                if (!textErrorState || it.length != maxTextFieldChar)
                                    textErrorState = false
                            }
                            else -> {
                                textErrorState = true
                            }
                        }
                    },
                    textStyle = pretendardTextStyle.bodyMedium16,
                    decorationBox = { innerTextField ->
                        Row(modifier = Modifier.fillMaxWidth()) {
                            if (text.isEmpty()) {
                                Text(
                                    text = stringResource(id = R.string.home_bottomsheet_leave_your_own_story),
                                    color = Gray600
                                )
                            }
                        }
                        innerTextField()
                    }
                )
                if (textErrorState)
                    ErrorText(
                        modifier = Modifier.padding(horizontal = 22.dp),
                        text = stringResource(id = R.string.home_bottomsheet_you_can_enter_up_to_100_characters)
                    )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_doodle1),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .scale(1f)
                        .offset(x = 43.dp, y = (-67).dp)

                )
                Image(
                    painter = painterResource(R.drawable.ic_doodle2),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .scale(1f)
                        .offset(x = (20).dp, y = (-35).dp)
                )
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .wrapContentWidth()
                        .align(Alignment.BottomEnd),
                    color = Gray800,
                    style = pretendardTextStyle.bodyMedium13,
                    text = "${text.count()}/100 자"
                )
            }
        }
    }
}
