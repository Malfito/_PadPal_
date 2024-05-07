package com.example.padpal


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun EmptyCart(){
    Column(modifier= Modifier
        .padding(26.dp)
        .fillMaxSize()) {
        Spacer(modifier = Modifier.height(18.dp))
        Text(text = "Your Cart", fontSize = 30.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier=Modifier.height(6.dp))
        Divider()
        Spacer(modifier = Modifier.height(18.dp))

        Column(modifier= Modifier
            .padding(4.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally ){
            Spacer(modifier=Modifier.height(45.dp))
            Image(painter = painterResource(id = R.drawable.emptycart),contentDescription = null, modifier=Modifier.height(150.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Your Cart Looks Empty :(", fontSize = 18.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { /* Add to cart logic goes here */ },
                modifier = Modifier
                    .padding(2.dp)
                    .height(50.dp).fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFFE6A8C4))
            ) {
                Text(text = "Fill Your Cart", color = Color.Black, fontFamily = FontFamily.Serif)
            }
        }
        }
    }


@Preview(showBackground = true)
@Composable
fun EmptyCartPrev(){
    EmptyCart()
}