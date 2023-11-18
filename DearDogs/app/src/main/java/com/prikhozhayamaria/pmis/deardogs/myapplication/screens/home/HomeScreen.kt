package com.prikhozhayamaria.pmis.deardogs.myapplication.screens.home

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.prikhozhayamaria.pmis.deardogs.myapplication.R
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.Dog
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.viewModels.DogsListUiState
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.viewModels.DogsListViewModel
import com.prikhozhayamaria.pmis.deardogs.myapplication.ui.superstructures.home.HomeSuperstructure
import java.util.UUID

@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    controller: NavHostController,
    addDog: () -> Unit,
    editDog: (UUID) -> Unit) {
    HomeSuperstructure(innerPadding, controller, addDog,editDog);
}
@Composable
fun HomeScreenContent(
    it: PaddingValues,
    innerPadding: PaddingValues,
    controller: NavHostController,
    addDog: () -> Unit,
    editDog: (UUID) -> Unit,
    uiState: DogsListUiState
) {
    HomeContentList(controller = controller, it, innerPadding, uiState, addDog, editDog)
    if (uiState.isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}


@Composable
fun HomeContentList(
    controller: NavHostController,
    it: PaddingValues,
    innerPadding: PaddingValues,
    uiState: DogsListUiState,
    addDog: () -> Unit,
    editDog: (UUID) -> Unit,
    viewModel: DogsListViewModel = hiltViewModel()
){
//
//    val dataList by viewModel.dataFlow.collectAsState(emptyList())

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .absolutePadding(20.dp, 16.dp, 20.dp, 0.dp)
            .verticalScroll(rememberScrollState())
            .background(
                Color.White,
                shape = RectangleShape
            )) {
        Row() {

            Text(
                text = stringResource(R.string.home),
                color = Color.Black,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
                fontSize = 20.sp

            )
            Image(bitmap = ImageBitmap.imageResource(R.drawable.dog1),
                contentDescription = null, Modifier.width(23.dp))
        }

        if (uiState.dogs.isEmpty()) {
            Spacer(modifier = Modifier.padding(90.dp))
            NoDogsInfo(it, viewModel)
        }
        else {
            Spacer(modifier = Modifier.padding(10.dp))
            HomeCommonContent(
                editDog = editDog,
                deleteDog = viewModel::deleteDog,
                dataList = uiState.dogs,
                it = it,
                viewModel = viewModel
            )
        }
    }

}


@Composable
private fun DogItem(
    item: Dog,
    onEditDog: (UUID) -> Unit,
    onRemoveDog: (UUID) -> Unit,
    // onAdd: () -> Unit,
){

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
        Column(
//            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Row() {
                androidx.compose.material3.IconButton(onClick = {item.id?.let { onRemoveDog(it) }}
                ) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Gray,
                    )
                }
                androidx.compose.material3.IconButton(onClick = {item.id?.let { onEditDog(it) }}) {
                    androidx.compose.material3.Icon(
                        Icons.Filled.Edit,
                        contentDescription = "Edit",
                        tint = Color.Gray,
                    )
                }
                androidx.compose.material3.IconButton(onClick = { /* do something */ }) {
                    androidx.compose.material3.Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Like it",
                        tint = Color.Gray,
                    )
                }
            }

//            AsyncImage(
//                model = image,
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .height(200.dp)
//                    .fillMaxWidth()
//            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Nickname: " + item.nickname,
                    color = Color.Black,
                )
                Text(
                    text = "Breed: " + item.breed,
                    color = Color.Black,
                )
                Text(
                    text = "Age: " + item.age,
                    color = Color.Black,
                )
                Text(
                    text = "Color: " + item.color,
                    color = Color.Black,
                )
                Text(
                    text = "Obedience: " + item.obedience,
                    color = Color.Black,
                )
            }

            Spacer(modifier = Modifier.size(30.dp))

            Image(bitmap = ImageBitmap.imageResource(R.drawable.lapa),
                contentDescription = null,
                Modifier
                    .width(25.dp)
                    .align(Alignment.CenterHorizontally))

        }
    }
}


@Composable
private fun HomeCommonContent(
    dataList: List<Dog>,
    it: PaddingValues,
    viewModel: DogsListViewModel,
    editDog: (UUID) -> Unit,
    deleteDog: (UUID) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .padding(it)
            .fillMaxSize()
    ){
        items(dataList) { item ->
            DogItem(
                item = item,
                onEditDog = editDog,
                onRemoveDog = deleteDog
            )
        }
    }
}
@Composable
fun NoDogsInfo(it:PaddingValues, viewModel: DogsListViewModel) {
    Column(modifier = Modifier
        .padding(it)
        .fillMaxSize()
    ) {
        Box(
            modifier=Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(bitmap = ImageBitmap.imageResource(R.drawable.nodogs),
                contentDescription = null,
                Modifier
                    .width(150.dp))
        }
        Box(
            modifier=Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(R.string.no_dogs), color = Color.Gray, fontSize = 16.sp)
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    val padding = PaddingValues(0.dp);
    val controller = rememberNavController()
    HomeScreen(innerPadding = padding, controller = controller,)
}*/

