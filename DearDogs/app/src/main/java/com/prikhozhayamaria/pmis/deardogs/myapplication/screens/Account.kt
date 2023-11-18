package com.prikhozhayamaria.pmis.deardogs.myapplication.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.prikhozhayamaria.pmis.deardogs.myapplication.MainActivity
import com.prikhozhayamaria.pmis.deardogs.myapplication.R
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.api.DogAPI
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.api.viewModels.DogAPIViewModel
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.viewModels.DogsListViewModel
import com.prikhozhayamaria.pmis.deardogs.myapplication.screens.about.MainViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Account(innerPadding: PaddingValues, dogapiViewModel: DogAPIViewModel = hiltViewModel()) {
    val dogsapi by dogapiViewModel.dogsapi.collectAsState()
    DogsAPIList(dogsapi);

}


@Composable
fun DogsAPIList(dogsapi: List<DogAPI>) {
    LazyColumn {
        items(dogsapi) { dogapi ->
            DogAPIItem(dogapi)
        }
    }
}

@Composable
fun DogAPIItem(dogapi: DogAPI) {
    Card (
        modifier = Modifier
            .padding(10.dp)
            .width(300.dp)
            .height(450.dp)
            .shadow(
                elevation = 8.dp,
                ambientColor = Color.Black,
                spotColor = Color.Black,
                shape = RoundedCornerShape(20.dp)
            ),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFD6D6D6)
        )
    ) {


        Text(
            text = "This is breed ${dogapi.name}", style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .padding(10.dp)
        )
        Image(
            painter = rememberAsyncImagePainter("${dogapi.image_link}"),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
    }
}