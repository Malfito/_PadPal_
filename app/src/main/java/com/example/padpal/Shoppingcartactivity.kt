package com.example.padpal

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.padpal.ui.theme.PadPalTheme
import com.google.firebase.Firebase
import com.google.firebase.database.database
import dev.shreyaspatil.easyupipayment.EasyUpiPayment
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener
import dev.shreyaspatil.easyupipayment.model.PaymentApp
import dev.shreyaspatil.easyupipayment.model.TransactionDetails

class Shoppingcartactivity : ComponentActivity(), PaymentStatusListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PadPalTheme {
                // on below line we are specifying background color for our application
                Surface(
                    // on below line we are specifying modifier and color for our app
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {


                    // on the below line we are specifying the theme as the scaffold.
                    ShoppingCart(mainActivity = this)

                }
            }
        }
    }

    override fun onTransactionCancelled() {
        Toast.makeText(this, "Transaction cancelled by user..", Toast.LENGTH_SHORT).show()

        //navigate to PaymentFailed

    }

    override fun onTransactionCompleted(transactionDetails: TransactionDetails) {
        Toast.makeText(this, "Transaction completed by user..", Toast.LENGTH_SHORT).show()
    }
}
@Composable
fun falsepayingref1() {
    var context = LocalContext.current
    val database = Firebase.database
    val payingref1 = database.getReference("Machine 1").child("Paying")
    payingref1.setValue(false)
}


fun makePayment(
    amount: String,
    upi: String,
    name: String,
    desc: String,
    transcId: String, ctx: Context, activity: Activity, mainActivity: Shoppingcartactivity
) {
    try {

        // START PAYMENT INITIALIZATION
        val easyUpiPayment = EasyUpiPayment(activity) {
            this.paymentApp = PaymentApp.ALL
            this.payeeVpa = upi
            this.payeeName = name
            this.transactionId = transcId
            this.transactionRefId = transcId
            this.payeeMerchantCode = transcId
            this.description = desc
            this.amount = amount
        }

        // END INITIALIZATImakePayment(
        //                                amount.value,
        //                                upiId.value,
        //                                name.value,
        //                                description.value,
        //                                transcId,
        //                                ctx,
        //                                activity!!,
        //                                mainActivity
        //                            )ON

        // Register Listener for Events
        easyUpiPayment.setPaymentStatusListener(mainActivity)

        // Start payment / transaction
        easyUpiPayment.startPayment()
    } catch (e: Exception) {
        // on below line we are handling exception.
        e.printStackTrace()
        Toast.makeText(ctx, e.message, Toast.LENGTH_SHORT).show()

    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    PadPalTheme {

    }
}