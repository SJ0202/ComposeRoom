package com.seongju.composeroom.presentation.login.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seongju.composeroom.ui.theme.BoxRoundShapes
import com.seongju.composeroom.ui.theme.SkyBlue
import com.seongju.composeroom.ui.theme.SpaceDefault
import com.seongju.composeroom.ui.theme.SpaceSmall

@Composable
fun StandardButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    enabled: Boolean = true,
    containerColor: Color = SkyBlue,
    contentColor: Color = Color.White,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                disabledContentColor = Color.White,
                contentColor = contentColor
            ),
            shape = BoxRoundShapes,
            enabled = enabled,
            border = BorderStroke(
                width = 1.dp,
                color = SkyBlue
            )
        ) {
            Text(
                text = buttonText,
                modifier = Modifier
                    .padding(vertical = SpaceSmall)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StandardButtonPreview(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceDefault)
    ) {
        StandardButton(
            buttonText = "테스트"
        ) {

        }
    }
}