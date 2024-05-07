package com.example.padpal

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import java.util.*
import androidx.compose.material3.Card
import java.text.SimpleDateFormat
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Firebase
import com.google.firebase.database.database
import dev.shreyaspatil.easyupipayment.EasyUpiPayment
import dev.shreyaspatil.easyupipayment.model.PaymentApp
import kotlin.math.absoluteValue

@Composable
fun ShoppingCart(mainActivity: Shoppingcartactivity){

    //Authentication krte hue jo email id use hui h usme se @gmail.com hata krke name ki value me daal dena.
    //vaha pr name.value= (authentication se aane vala naam).toString() ye sab niche payment logic vale function ke upr daal dena

    val ctx = LocalContext.current
    val activity = (LocalContext.current as? Activity)

    val amount = remember {
        mutableStateOf("0")
    }
    val upiId = remember {
        mutableStateOf("8810202797@ybl")
    }
    val name = remember {
        mutableStateOf("abc")
    }
    val description = remember {
        mutableStateOf("0-0-0")
    }



    val viewModel: ShoppingCartViewModel = viewModel()
    val items = remember { viewModel.items }

    var context = LocalContext.current
    val database = Firebase.database

    var overnight1 by remember {
        mutableStateOf(0)
    }
    var medium1 by remember {
        mutableStateOf(0)
    }
    var small1 by remember {
        mutableStateOf(0)
    }


    val overnightref = database.getReference("Machine 1").child("Overnight").get().addOnSuccessListener { dataSnapshot ->
        val value = dataSnapshot.getValue(Int::class.java)
        if (value != null) {
            overnight1 = value
        }
    }
    val mediumref = database.getReference("Machine 1").child("Medium").get().addOnSuccessListener { dataSnapshot ->
        val value = dataSnapshot.getValue(Int::class.java)
        if (value != null) {
            medium1 = value
        }
    }
    val smallref = database.getReference("Machine 1").child("Small").get().addOnSuccessListener { dataSnapshot ->
        val value = dataSnapshot.getValue(Int::class.java)
        if (value != null) {
            small1 = value
        }
    }




    Column(modifier= Modifier
        .padding(26.dp)
        .fillMaxSize()) {
        Spacer(modifier = Modifier.height(18.dp))
        Text(text = "Your Cart", fontSize = 30.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier= Modifier.height(6.dp))
        Divider()
        Spacer(modifier = Modifier.height(18.dp))
        Card(
            // Set the background color here
            elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()// Optionally, you can set the elevation
            // Add any other modifiers as needed
        ) {
            // Card content goes here
            Column(modifier=Modifier.padding(8.dp)) {
                Spacer(modifier= Modifier.height(10.dp))
                Row(modifier=Modifier.padding(4.dp), horizontalArrangement= Arrangement.SpaceEvenly){
                    Text(modifier=Modifier.weight(0.5f),text = "Overnight pads", fontSize = 21.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light
                    )
                    if(items.value[2]<overnight1){
                    Text(text = "x"+items.value[2].toString(), fontSize = 21.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light
                    )}
                    else{
                        Text(text = "Not enough", fontSize = 21.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light
                        )
                    }
                }
                Divider(modifier=Modifier.padding(4.dp))
                Row(modifier=Modifier.padding(4.dp), horizontalArrangement= Arrangement.SpaceEvenly){

                    Text(modifier=Modifier.weight(0.5f),text = "Medium pads", fontSize = 21.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light
                    )

                    if(items.value[1]<medium1){
                    Text(text = "x"+items.value[1].toString(), fontSize = 21.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light
                    )}
                    else{
                        Text(text = "Not enough", fontSize = 21.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light
                        )

                    }

                }
                Divider(modifier=Modifier.padding(4.dp))
                Row(modifier=Modifier.padding(4.dp), horizontalArrangement= Arrangement.SpaceEvenly){
                    Text(modifier=Modifier.weight(0.5f),text = "Small pads", fontSize = 21.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light
                    )
                    if(items.value[0]<small1){
                    Text(text = "x"+items.value[0].toString(), fontSize = 21.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light
                    )}
                    else{
                        Text(text = "Not enough", fontSize = 21.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(0.5f))
                Divider(modifier=Modifier.padding(4.dp))
                Row(modifier=Modifier.padding(4.dp), horizontalArrangement= Arrangement.SpaceEvenly){
                    Text(modifier=Modifier.weight(0.5f),text = "Net Total", fontSize = 21.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light
                    )
                    Text(text = "$"+(items.value[0]*3+items.value[1]*4+items.value[2]*5).toString(), fontSize = 21.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Light
                    )

                }
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
        Button(
            onClick = {

                var error=false;
                val payingref1 = database.getReference("Machine 1").child("Paying")
                val payingref = database.getReference("Machine 1").child("Paying").get().addOnSuccessListener { dataSnapshot ->
                    val value = dataSnapshot.getValue(Boolean::class.java)
                    if (value ==true) {
                        Toast.makeText(context, "The machine is busy right now", Toast.LENGTH_SHORT).show()
                    }
                    else if(value==false){
                        val overnightref = database.getReference("Machine 1").child("Overnight").get().addOnSuccessListener { dataSnapshot ->
                            val value = dataSnapshot.getValue(Int::class.java)
                            if (value != null) {
                                if(value<items.value[2]){
                                    error=error||true
                                }
                                else{
                                    error=error||false
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
                                }
                            }
                        }

                        if(error==true){
                            Toast.makeText(context,"The quantities have been changed, refill your cart", Toast.LENGTH_SHORT).show()
                        }
                        else if(error==false){
                            payingref1.setValue(true)

                            amount.value=(items.value[0]*3+items.value[1]*4+items.value[2]*5).toString()
                            description.value=items.value[0].toString()+"-"+items.value[1].toString()+"-"+items.value[2].toString()
                            val c: Date = Calendar.getInstance().getTime()
                            val df = SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault())
                            val transcId: String = df.format(c)

                            //Payment Logic goes here


                            try {

                                // START PAYMENT INITIALIZATION
                                val easyUpiPayment = EasyUpiPayment(activity!!) {
                                    this.paymentApp = PaymentApp.ALL
                                    this.payeeVpa = upiId.value
                                    this.payeeName = name.value
                                    this.transactionId = transcId
                                    this.transactionRefId = transcId
                                    this.payeeMerchantCode = transcId
                                    this.description = description.value
                                    this.amount = amount.value
                                }
                                // END INITIALIZATION

                                // Register Listener for Events
                                easyUpiPayment.setPaymentStatusListener(mainActivity)

                                // Start payment / transaction
                                easyUpiPayment.startPayment()
                            } catch (e: Exception) {
                                // on below line we are handling exception.
                                e.printStackTrace()
                                Toast.makeText(ctx, e.message, Toast.LENGTH_SHORT).show()
                                payingref1.setValue(false)

                            }
                        }
                        else{
                            Toast.makeText(context,"ERROR 02: UNKNOWN ERROR OCCURED", Toast.LENGTH_SHORT).show()
                        }

                    }
                    else{
                        Toast.makeText(context,"ERROR 01: NULL ERROR FOR PAYING VARIABLE", Toast.LENGTH_SHORT).show()
                    }
                }


                      },
            modifier = Modifier
                .padding(2.dp)
                .height(50.dp).fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFFE6A8C4))
        ) {
            Text(text = "Pay Now", color = Color.Black, fontFamily = FontFamily.Serif)
        }
}}





@Preview(showBackground = true)
@Composable
fun ShoppingCartPrev(){

}