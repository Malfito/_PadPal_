package com.example.padpal


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults


import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.ContentAlpha
import com.google.firebase.Firebase
import com.google.firebase.database.database

@Composable
fun Dashboard(viewModel: ShoppingCartViewModel) {

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

//    val viewModel: ShoppingCartViewModel = viewModel()
    val items = remember { viewModel.items }

    var overnightbutton by remember{mutableStateOf("Add to Cart")}
    var mediumbutton by remember{mutableStateOf("Add to Cart")}
    var smallbutton by remember{mutableStateOf("Add to Cart")}

    fun addSmall(){
        if(small1>items.value[0]){
            viewModel.addSmall()
            smallbutton=items.value[0].toString()+"     +"
        }
        else{
            Toast.makeText(context, "You cant add any more items", Toast.LENGTH_SHORT).show()
        }
    }
    fun addMedium(){
        if(medium1>items.value[1]){
            viewModel.addMedium()
            mediumbutton=items.value[1].toString()+"     +"
        }
        else{
            Toast.makeText(context, "You cant add any more items", Toast.LENGTH_SHORT).show()
        }
    }
    fun addOvernight(){
        if(overnight1>items.value[2]){
            viewModel.addOvernight()
            overnightbutton=items.value[2].toString()+"     +"
        }
        else{
            Toast.makeText(context, "You cant add any more items", Toast.LENGTH_SHORT).show()
        }
    }
    fun removeSmall(){
        if(items.value[0]>0){
            viewModel.removeSmall()
            smallbutton=items.value[0].toString()+"     +"
        }
        else if(items.value[0]==0){
            smallbutton="Add to Cart"
        }
    }
    fun removeMedium(){
        if(items.value[1]>0){
            viewModel.removeMedium()
            mediumbutton=items.value[1].toString()+"     +"
        }
        else if(items.value[1]==0){
            mediumbutton="Add to Cart"
        }
    }
    fun removeOvernight(){
        if(items.value[2]>0){
            viewModel.removeOvernight()
            overnightbutton=items.value[2].toString()+"     +"
        }
        else if(items.value[2]==0){
            overnightbutton="Add to Cart"
        }
    }








    Surface(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Spacer(modifier = Modifier.height(3.dp))
            MassiveCard(text = "PadPal")
            DashboardScreen()
            val scrollState = rememberScrollState()

            Column(modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)) {


                    DashboardItem(title = "Overnight ($5)", detail = "Ultra-absorbent pads for maximum protection during long nights."
                        , id= R.drawable.overnight, buttonText = smallbutton, onAdd = { addSmall() }, onRemove = { removeSmall() })
                    DashboardItem(title = "Medium ($4)", detail = "All-day freshness with the perfect balance for absorption and discretion.", id=R.drawable.medium, buttonText = mediumbutton, onAdd = {addMedium()}, onRemove = {removeMedium()})

                    DashboardItem(title = "Small ($3)", detail = "Discreet and reliable pads for light flow days or backup protection.", id= R.drawable.small, buttonText = overnightbutton, onAdd = {addOvernight()}, onRemove = {removeOvernight()})


            }
        }
    }
}


@Composable
fun MassiveCard(text: String) {
    Card(

        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        elevation = CardDefaults.cardElevation(30.dp),
        content = {


                Image(painter = painterResource(id = R.drawable.wow), contentDescription = null)

        }
    )
}

@Composable
fun DashboardItem(title: String, detail: String, id: Int, buttonText: String, onAdd:()->Unit,onRemove: () -> Unit) {
    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(color = Color.White),
        elevation = CardDefaults.cardElevation(10.dp),
        content = {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {

                Image(painter = painterResource(id = id), contentDescription = null, modifier=Modifier)
                Spacer(modifier = Modifier.width(8.dp))
            Column(

                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W300
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = detail,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Add to cart button
                Row(horizontalArrangement = Arrangement.Center) {
                    Button(
                        onClick = onAdd,
                        modifier = Modifier
                            .padding(2.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFE6A8C4))
                    ) {
                        Text(text = buttonText, color = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    CircularButton(onRemove)
                }
                }
                
            }


        }
    )
}
@Composable
fun DashboardScreen(){

        Column(modifier= Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Spacer(modifier= Modifier.height(12.dp))
            Text(text = "Catalogue", fontSize = 25.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier= Modifier.height(6.dp))
            Divider()
            Spacer(modifier= Modifier.height(8.dp))


        }}
@Composable
fun CircularButton(onRemove:()->Unit) {
    OutlinedButton(
        onClick = onRemove,
        modifier = Modifier
            .padding(3.dp)
            .height(50.dp),
        shape = CircleShape,
        border = BorderStroke(1.dp, Color(0xFFE6A8C4)),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFE6A8C4))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_minus), // Replace with your icon
            contentDescription = "remove items",
            tint = Color.Black,
            modifier = Modifier.size(25.dp) // Adjust icon size as needed
        )
    }}



//@Composable
//fun BottomBar(navController: NavHostController) {
//    val screens = listOf(
//        BottomBarScreen.Home,
//        BottomBarScreen.Profile,
//        BottomBarScreen.Settings,
//    )
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
//    if (bottomBarDestination) {
//        NavigationBar {
//            screens.forEach { screen ->
//                AddItem(
//                    screen = screen,
//                    currentDestination = currentDestination,
//                    navController = navController
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun RowScope.AddItem(
//    screen: BottomBarScreen,
//    currentDestination: NavDestination?,
//    navController: NavHostController
//) {
//    NavigationBarItem(
//        label = {
//            Text(text = screen.title)
//        },
//        icon = {
//            Icon(
//                imageVector = screen.icon,
//                contentDescription = "Navigation Icon"
//            )
//        },
//        selected = currentDestination?.hierarchy?.any {
//            it.route == screen.route
//        } == true,
//        // unselectedContentColor koi parameter nahi hai NavigationBarItem me , to isko documentation se dekhna hoga
//       // unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
//        onClick = {
//            navController.navigate(screen.route) {
//                popUpTo(navController.graph.findStartDestination().id)
//                launchSingleTop = true
//            }
//        }
//    )
//}
@Preview(showBackground = true)
@Composable
fun PreviewDashboardScreen() {
    Dashboard(viewModel())
}
