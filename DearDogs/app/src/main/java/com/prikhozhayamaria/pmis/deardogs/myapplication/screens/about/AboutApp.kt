package com.prikhozhayamaria.pmis.deardogs.myapplication.screens.about

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.prikhozhayamaria.pmis.deardogs.myapplication.MainActivity
import com.prikhozhayamaria.pmis.deardogs.myapplication.R
import com.prikhozhayamaria.pmis.deardogs.myapplication.ui.theme.MyApplicationTheme

class MainViewModel : ViewModel() {
    // MutableState to handle our UI state
    var counterState = mutableStateOf(0)

    // Function to increment the counter
    fun incrementCounter() {
        counterState.value++
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutApp(innerPadding: PaddingValues, mainViewModel: MainViewModel = viewModel()) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .absolutePadding(20.dp, 60.dp, 20.dp, 0.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.size(20.dp))

        Row() {

            Text(
                text = stringResource(R.string.about),
                color = Color.Black,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
                fontSize = 20.sp

            )
            Image(bitmap = ImageBitmap.imageResource(R.drawable.dog1),
                contentDescription = null, Modifier.width(23.dp))
        }

        Spacer(modifier = Modifier.size(20.dp))

        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(6.dp))
                .background(Color.Transparent)
                .width(300.dp)
        ) {

            Image(bitmap = ImageBitmap.imageResource(R.drawable.dog3),
                contentDescription = null)

        }

        Spacer(modifier = Modifier.size(20.dp))

        Box(modifier = Modifier
            .clip(shape = RoundedCornerShape(6.dp))
            .background(Color.LightGray)
            .fillMaxSize()) {

            Text(stringResource(R.string.app_about),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily= FontFamily.Monospace,
                textAlign = TextAlign.Justify
            )
        }

        Spacer(modifier = Modifier.size(20.dp))

        MainActivity.aboutMeStrings.forEach{
            Card(modifier = Modifier
                .clip(shape = RoundedCornerShape(6.dp))
                .background(Color.Gray)
                .fillMaxSize()
                .width(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray
                )) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Image(bitmap = ImageBitmap.imageResource(it.pict),
                        contentDescription = null, Modifier.width(20.dp).padding(2.dp))
                    Text(stringResource(it.line), fontSize = 12.sp,
                        color = Color.Black
                    )
                }

            }
            Spacer(modifier = Modifier.size(10.dp))
        }

        Spacer(modifier = Modifier.size(20.dp))

    }

}



@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    val padding = PaddingValues(0.dp);
    val navController = rememberNavController();
    MyApplicationTheme {
        AboutApp(padding);
    }
}