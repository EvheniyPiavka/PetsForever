package com.zeek1910.petsforever.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeek1910.petsforever.R
import com.zeek1910.petsforever.ui.theme.Dark1
import com.zeek1910.petsforever.ui.theme.Indigo
import com.zeek1910.petsforever.ui.theme.PetsForeverTheme
import com.zeek1910.petsforever.ui.theme.White

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, onClick: () -> Unit = {}, text: String? = null) {
    Button(
        modifier = modifier.height(56.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Indigo,
            contentColor = White
        )
    ) {
        text?.let {
            Text(
                text = it,
                style = TextStyle(
                    color = White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun BackButton(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    TextButton(
        modifier = modifier.height(56.dp),
        onClick = onClick,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                tint = Dark1,
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(R.string.back),
                style = TextStyle(
                    color = Dark1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonPreview() {
    PetsForeverTheme {
        PrimaryButton(text = "Test")
    }
}