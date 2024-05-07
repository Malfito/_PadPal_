package com.example.padpal

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SettingsScreen(username: String, onEditProfileClick: () -> Unit, onViewPastOrdersClick: () -> Unit, onLogoutClick: () -> Unit) {
    Column(modifier= Modifier
        .padding(26.dp)
        .fillMaxSize()) {
        Spacer(modifier = Modifier.height(18.dp))
        Text(text = "Settings", fontSize = 30.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier= Modifier.height(6.dp))
        Divider()
        Spacer(modifier = Modifier.height(18.dp))

        Avatar(username = username)

        Spacer(modifier = Modifier.height(10.dp))
        Divider()

        // Options
        Spacer(modifier = Modifier.height(16.dp))

        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            OptionButton(text = "About us",{})



            Spacer(modifier = Modifier.height(12.dp))
            Divider()
            Spacer(modifier = Modifier.height(12.dp))
            ViewPastOrdersButton(onClick = { /*TODO*/ })
            Spacer(modifier = Modifier.height(22.dp))



            // Logout button
            LogoutButton({}, modifier=Modifier.weight(0.7f))
        }
        
}}

@Composable
fun Avatar(username: String) {

    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        Column {
            Text(
                text = username,
                style = MaterialTheme.typography.headlineMedium, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold,
                color = Color.Black, modifier=Modifier.padding(start=16.dp)
            )

            Text(
                text = "Edit your Profile",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray,
                modifier = Modifier
                    .clickable(onClick = { /*navigate to edit profile*/ })
                    .padding(start = 16.dp)
            )

        }


    Image(
        painter= painterResource(id = R.drawable.girlava),
        contentDescription = "User Avatar",
        modifier = Modifier
            .size(130.dp)
            .padding(end = 20.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )



    }
}

@Composable
fun OptionButton(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        fontStyle = FontStyle(0), fontSize = 19.sp,
        color = Color.Black, fontFamily = FontFamily.Serif,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .fillMaxWidth()
    )
}
@Composable
fun LogoutButton(onClick: () -> Unit, modifier: Modifier=Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor =  Color.Gray
        ), shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)

    ) {
        Text(
            text = "Logout",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
    }
}
@Composable
fun ViewPastOrdersButton(onClick: () -> Unit, modifier: Modifier=Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor =  Color.Gray
        ), shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)

    ) {
        Text(
            text = "View Past Orders",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
    }
}


@Preview(showBackground=true)
@Composable
fun SettingsScreenPrev(){
    SettingsScreen(
        username = "John Doe",
        onEditProfileClick = {},
        onViewPastOrdersClick = {},
        onLogoutClick = {}
    )
}