package com.seongju.composeroom.presentation.login.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seongju.composeroom.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit
) {
    val containerColor = remember { mutableStateOf(Color.White) }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = hint,
                    color = Gray500
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    containerColor.value = if (it.isFocused) Gray50 else Color.White
                }
                .border(
                    width = 1.dp,
                    color = Gray200,
                    shape = BoxRoundShapes
                ),
            shape = BoxRoundShapes,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = containerColor.value,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            visualTransformation = visualTransformation,
            singleLine = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StandardTextFieldPreview(

) {
    val value = remember { mutableStateOf("") }
    val hint = "힌트"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceDefault)
    ) {
        StandardTextField(
            value = value.value,
            hint = hint,
            onValueChange = {
                value.value = it
            }
        )
    }
}