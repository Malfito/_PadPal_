package com.example.padpal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Icon
import com.example.padpal.ui.theme.PadPalTheme

class MainActivity : ComponentActivity() {
    private val viewModel: ShoppingCartViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val viewModel: ShoppingCartViewModel = viewModel()
            nav()
        }
    }
}
//@Composable
//fun MyBottomAppBar(viewModel: ShoppingCartViewModel){
//    val navigationController = rememberNavController()
//    val context = LocalContext.current.applicationContext
//    val selected = remember{
//        mutableStateOf(Icons.Default.Home)
//    }
//
//    Scaffold (
//        bottomBar = {
//            BottomAppBar(containerColor = Color(0xFFE6A8C4)) {
//                IconButton(onClick = {
//                         selected.value = Icons.Default.Home
//                    navigationController.navigate(Screens.Dashboard.screen){
//                        popUpTo(0)
//                    }
//
//                },modifier = Modifier.weight(1f)) {
//                    Icon(Icons.Default.Home, contentDescription = null,
//                        modifier = Modifier.size(26.dp), tint =
//                            if(selected.value == Icons.Default.Home) Color.White
//                        else
//                        Color.DarkGray
//                        )
//
//                    }
//                IconButton(onClick = {
//                    selected.value = Icons.Default.ShoppingCart
//                    navigationController.navigate(Screens.ShoppingCart.screen){
//                        popUpTo(0)
//                    }
//
//                },modifier = Modifier.weight(1f)) {
//                    Icon(Icons.Default.ShoppingCart, contentDescription = null,
//                        modifier = Modifier.size(26.dp), tint =
//                        if(selected.value == Icons.Default.ShoppingCart) Color.White
//                        else
//                            Color.DarkGray
//                    )
//
//                }
//                IconButton(onClick = {
//                    selected.value = Icons.Default.Settings
//                    navigationController.navigate(Screens.Settings.screen){
//                        popUpTo(0)
//                    }
//
//                },modifier = Modifier.weight(1f)) {
//                    Icon(Icons.Default.Settings, contentDescription = null,
//                        modifier = Modifier.size(26.dp), tint =
//                        if(selected.value == Icons.Default.Settings) Color.White
//                        else
//                            Color.DarkGray
//                    )
//
//                }
//
//                }
//
//
//        }
//    ){
//    paddingValues ->
//        NavHost(navController = navigationController,
//            startDestination = Screens.Dashboard.screen,
//            modifier = Modifier.padding(paddingValues)){
//
//            composable(Screens.Dashboard.screen){
//
//                Dashboard(viewModel)
//                            }
//            composable(Screens.ShoppingCart.screen){
//
//                ShoppingCart(mainActivity = Shoppingcartactivity())
//            }
//            composable(Screens.Settings.screen){
//                SettingsScreen(
//                    username = "Ayush",
//                    onEditProfileClick = { /*TODO*/ },
//                    onViewPastOrdersClick = { /*TODO*/ }) {
//
//                }
//            }
//
//        }
//    }
//}
