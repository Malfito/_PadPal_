package com.example.padpal

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database


@Composable
fun PaymentSuccess() {
    Column(modifier= Modifier
        .fillMaxSize()
        .background(Color.White)) {
        LazyColumn{
            item { EmptyTop()
                SuccessBottom()
            }

        }

    }
}


@Composable
fun EmptyTop(){
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
            painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
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
fun SuccessBottom() {
    val viewModel: ShoppingCartViewModel = viewModel()
    val items = remember { viewModel.items }

    var context = LocalContext.current
    val database = Firebase.database

    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier= Modifier
        .background(Color.White)
        .fillMaxSize()
        .padding(top = 32.dp)) {
        var textState by remember { mutableStateOf("") }
        var textState2 by remember { mutableStateOf("") }



        Text(text="Your Payment is Successful",
            color=Color.DarkGray,
            fontFamily = FontFamily.Serif,
            fontSize = 38.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.ExtraBold,
            modifier= Modifier
                .padding(24.dp)
                .align(Alignment.Start)
        )
        VendNowButton {
            var error=false;
            val payingref = database.getReference("Machine 1").child("Paying").get().addOnSuccessListener { dataSnapshot ->
                val value = dataSnapshot.getValue(Boolean::class.java)
                if (value!=null) {
                    if(value==true){

            val vendingref1 = database.getReference("Machine 1").child("Vending?")
            val vendingref = database.getReference("Machine 1").child("Vending?").get().addOnSuccessListener { dataSnapshot ->
                val value = dataSnapshot.getValue(Boolean::class.java)
                if (value ==true) {
                    Toast.makeText(context, "The machine is busy vending right now. Try again in a few seconds", Toast.LENGTH_SHORT).show()
                }
                else if(value==false){

                    //REF VAR FROM FIREBASE
                    val overnightref1 = database.getReference("Machine 1").child("Overnight")
                    val mediumref1 = database.getReference("Machine 1").child("Medium")
                    val smallref1 = database.getReference("Machine 1").child("Small")






                    val overnightref = database.getReference("Machine 1").child("Overnight").get().addOnSuccessListener { dataSnapshot ->
                        val value = dataSnapshot.getValue(Int::class.java)
                        if (value != null) {
                            if(value<items.value[2]){
                                error=error||true
                            }
                            else{
                                error=error||false
                                items.value[5]= value
                            }
                        }
                    }
                    val mediumref = database.getReference("Machine 1").child("Medium").get().addOnSuccessListener { dataSnapshot ->
                        val value = dataSnapshot.getValue(Int::class.java)
                        if (value != null) {
                            if(value<items.value[1]){
                                error=error||true
                            }
                            else{
                                error=error||false
                                items.value[4]= value
                            }
                        }
                    }
                    val smallref = database.getReference("Machine 1").child("Small").get().addOnSuccessListener { dataSnapshot ->
                        val value = dataSnapshot.getValue(Int::class.java)
                        if (value != null) {
                            if(value<items.value[0]){
                                error=error||true
                            }
                            else{
                                error=error||false
                                items.value[3]= value
                            }
                        }
                    }

                    if(error==true){
                        Toast.makeText(context,"ERROR 05: BAD VEND COMMAND! QUANTITIES HAVE BEEN MODIFIED", Toast.LENGTH_SHORT).show()
                    }
                    else if(error==false){
                        vendingref1.setValue(true)
                        overnightref1.setValue((items.value[5]-items.value[2]))
                        mediumref1.setValue((items.value[4]-items.value[1]))
                        smallref1.setValue((items.value[3]-items.value[0]))
                        Toast.makeText(context,"SUCCESSFULLY VENDED THE ITEMS :)", Toast.LENGTH_SHORT).show()

                        //LOGIC FOR VENDING (USE BLOCKING LOGIC IN THE MCU)
                    }
                    else{
                        Toast.makeText(context,"ERROR 02: UNKNOWN ERROR OCCURED", Toast.LENGTH_SHORT).show()
                    }

                }
                else{
                    Toast.makeText(context,"ERROR 01: NULL ERROR FOR PAYING VARIABLE", Toast.LENGTH_SHORT).show()
                }
            }}
                    else{
                        Toast.makeText(context,"ERROR 03:MACHINE SYNCING ERROR. PAYING VARIABLE: FALSE", Toast.LENGTH_SHORT).show()
                    }
                }
            else{
                    Toast.makeText(context,"ERROR 01:NULL ERROR FOR PAYING VARIABLE", Toast.LENGTH_SHORT).show()
                }
            }
        }


        Spacer(modifier = Modifier.padding(13.dp))

        BackToDashboardButton(onClick = {/*NAVIGATION LOGIC TO GET BACK TO THE DASHBOARD*/})






        Row(modifier= Modifier
            .fillMaxWidth()
            .padding(10.dp), horizontalArrangement = Arrangement.Absolute.SpaceBetween) {

        }

    }
}






@Composable
fun BackToDashboardButton(onClick: () -> Unit) {
    Button(
        onClick = onClick, shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor =  Color(0xCCFA537C)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Back to The Dashboard",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun VendNowButton(onClick: () -> Unit) {
    Button(
        onClick = onClick, shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor =  Color(0xFF4CAF50)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Vend Now",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    PaymentSuccess()

}
