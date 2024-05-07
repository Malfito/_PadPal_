package com.example.padpal


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth


@Composable
fun PaymentFailed() {
    Column(modifier= Modifier
        .fillMaxSize()
        .background(Color.White)) {
        LazyColumn{
            item { EmptyTop1()
                SuccessBottom1()
            }

        }

    }
}


@Composable
fun EmptyTop1(){
    Box(modifier= Modifier
        .navigationBarsPadding()
        .wrapContentSize()
        .background(Color.White)) {

        Image(
            painter = painterResource(id = R.drawable.rectangle_3),
            contentDescription = null, // Provide a meaningful content description
            modifier = Modifier.scale(1.5f),
            contentScale = ContentScale.Inside // Adjust content scale as needed
        )
        Image(
            painter = painterResource(id = R.drawable.baseline_error_outline_24),
            contentDescription = null, // Provide a meaningful content description
            modifier= Modifier
                .wrapContentSize()
                .align(Alignment.TopCenter)
                .scale(2f)
                .padding(top = 55.dp),
            contentScale = ContentScale.Inside // Adjust content scale as needed
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessBottom1() {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier= Modifier
        .background(Color.White)
        .fillMaxSize()
        .padding(top = 32.dp)) {
        var textState by remember { mutableStateOf("") }
        var textState2 by remember { mutableStateOf("") }



        Text(text="Your Payment Failed",
            color=Color.DarkGray,
            fontFamily = FontFamily.Serif,
            fontSize = 38.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.ExtraBold,
            modifier= Modifier
                .padding(24.dp)
                .align(Alignment.Start)
        )
        TryAgainButton {

        }


        Spacer(modifier = Modifier.padding(13.dp))

        BackToDashboardButton(onClick = {})






        Row(modifier= Modifier
            .fillMaxWidth()
            .padding(10.dp), horizontalArrangement = Arrangement.Absolute.SpaceBetween) {

        }

    }
}








@Composable
fun TryAgainButton(onClick: () -> Unit) {
    Button(
        onClick = onClick, shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor =  Color(0xFFF44336)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Try Again",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    PaymentFailed()

}
