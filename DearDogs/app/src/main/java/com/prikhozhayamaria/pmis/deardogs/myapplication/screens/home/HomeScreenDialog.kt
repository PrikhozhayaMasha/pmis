package com.prikhozhayamaria.pmis.deardogs.myapplication.screens.home


import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.google.android.material.color.utilities.MaterialDynamicColors.background
import com.prikhozhayamaria.pmis.deardogs.myapplication.R
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.viewModels.AddEditDogViewModel
import com.prikhozhayamaria.pmis.deardogs.myapplication.ui.theme.dimGrey

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AddEditDogScreen(
    it: PaddingValues,
    onDogUpdate: () -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    viewModel: AddEditDogViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    System.out.println("ui memory: "+ uiState.nickname);

    Scaffold(
        modifier = modifier
            .absolutePadding(0.dp, 0.dp, 0.dp, 60.dp)
            .fillMaxSize()/*fillMaxHeight(0.5f).fillMaxWidth().padding(top= 400.dp).background(Brush.linearGradient(
            listOf(Color.Transparent, Color.Transparent)
        ), alpha=0f)*/,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            BottomAppBar(
                actions = {
                    androidx.compose.material.IconButton(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .width(32.dp)
                            .height(32.dp),
                        onClick = { viewModel.deleteDog() }
                    ) {
                        androidx.compose.material.Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete"
                        )
                    }
                },
                containerColor = Color.Transparent
            )
        }

    ) { paddingValues ->
        AddEditDogContent(
            loading = uiState.isLoading,
            saving = uiState.isDogSaving,
            nickname = uiState.nickname,
            breed = uiState.breed,
            age = uiState.age,
            color = uiState.color,
            image = uiState.image,
            obedience = uiState.obedience,

            onNicknameChanged = { newNickname -> viewModel.setDogNickname(newNickname) },
            onBreedChanged = { newBreed -> viewModel.setDogBreed(newBreed)},
            onAgeChanged = { newAge -> viewModel.setDogAge(newAge) },
            onColorChanged = { newColor -> viewModel.setDogColor(newColor)},
            onImageChanged = { newImage -> viewModel.setDogImage(newImage) },
            onObedienceChanged = { newObedience -> viewModel.setDogObedience(newObedience)},
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding() + paddingValues.calculateTopPadding()
                )
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFB0D0E2),
                            Color(0xFF569BAA),
                            Color(0xFF829797)
                        )
                    )
                )
        )

        // Check if the planet is saved and call onPlanetUpdate event
        LaunchedEffect(uiState.isDogSaved) {
            if (uiState.isDogSaved) {
                onDogUpdate()
            }
        }
    }

    val navController = rememberNavController()
    //Lets define bottomSheetScaffoldState which will hold the state of Scaffold
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()




    //Show the error message as a toast if we've just had an error
    val context = LocalContext.current
    val errorText = uiState.dogSavingError?.let { stringResource(it) }
    LaunchedEffect(errorText) {
        if (errorText != null) {
            Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddEditDogContent(
    loading: Boolean,
    saving: Boolean,
    nickname: String,
    breed: String,
    age: String,
    color: String,
    image: String,
    obedience: Float,
    onNicknameChanged: (String) -> Unit,
    onBreedChanged: (String) -> Unit,
    onAgeChanged: (String) -> Unit,
    onColorChanged: (String) -> Unit,
    onImageChanged: (String) -> Unit,
    onObedienceChanged: (Float) -> Unit,

    modifier: Modifier = Modifier,
    viewModel: AddEditDogViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    System.out.println("ui dog: "+ uiState.nickname);

    if (loading) {
        LoadingContent()
    }
    else {
        Column(
            modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(all = 30.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            /*.border(
                1.dp, shape = RectangleShape, brush = Brush.linearGradient(
                    colors = listOf
                        (
                        Color.Yellow,
                        Color.White,
                        Color.Cyan
                    )
                )
            )*/
        ) {

            var sliderPosition by remember{mutableStateOf(0f)}

            //com.prikhozhayamaria.pmis.deardogs.myapplication.screens.PhotoPickerDemoScreen()

            Text(
                text = "Your doggy",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.padding(16.dp))

            OutlinedTextField(
                value = nickname,
                onValueChange = onNicknameChanged,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text),
                label = { Text(text = "Nickname") },
                colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF58BBFD),
                    cursorColor = Color(0xFF58BBFD)))

            Spacer(modifier = Modifier.padding(3.dp))

            OutlinedTextField(
                value = breed,
                onValueChange = onBreedChanged,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text),
                label = { Text(text = "Breed of dog") },
                colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF58BBFD),
                    cursorColor = Color(0xFF58BBFD)))

            Spacer(modifier = Modifier.padding(3.dp))

            OutlinedTextField(
                value = age,
                onValueChange = onAgeChanged,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text),
                label = { Text(text = "Dog age") },
                colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF58BBFD),
                    cursorColor = Color(0xFF58BBFD)))

            Spacer(modifier = Modifier.padding(3.dp))

            OutlinedTextField(
                value = color,
                onValueChange = onColorChanged,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text),
                label = { Text(text = "Dog color") },
                colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF58BBFD),
                    cursorColor = Color(0xFF58BBFD)))

            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = "Obedience rating: ${sliderPosition}", fontSize = 16.sp)

            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it},
                onValueChangeFinished = { viewModel.setDogObedience(sliderPosition) },
                valueRange = 0f..10f,
                steps = 9,
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF384F92),
                    activeTrackColor = Color(0xFF146F97),
                    inactiveTrackColor = Color(0xFFFFEBEE),
                    inactiveTickColor = Color(0xFF2479E8),
                    activeTickColor = Color(0xFF002835)
                ),
                modifier = Modifier.width(300.dp)
            )

            Spacer(modifier = Modifier.padding(10.dp))

            ExtendedFloatingActionButton(
                onClick = { if (!uiState.isDogSaving) viewModel.saveDog() },
                text = {Text(text = "Save")},
                icon = {Icon(Icons.Filled.Done, stringResource(R.string.save_dog_description))},
                backgroundColor = colorResource(R.color.blue_grey_500)  )

        }

        if (saving) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
private fun LoadingContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = Color.DarkGray)
    }
}




@Preview
@Composable
fun AddEditContentPreview() {
    AddEditDogContent(
        loading = true,
        saving = true,
        nickname = "aaa",
        breed = "bbb",
        age = "10",
        color = "ccc",
        image = "yhgf",
        obedience = 5.0f,
        onNicknameChanged = {},
        onBreedChanged = {},
        onAgeChanged = { },
        onColorChanged = { },
        onImageChanged = {},
        onObedienceChanged = { }
    )
}



//@Composable
//fun AsyncImage(
//    modifier: Modifier = Modifier,
//    model: Any?,
//    contentDescription: String?,
//    placeholderMemoryCacheKey: String? = null,
//    loadingIndicatorSize: Dp = 40.dp,
//    contentScale: ContentScale = ContentScale.Fit,
//) {
//
//    var placeholderBitmap by remember(placeholderMemoryCacheKey) { mutableStateOf<Bitmap?>(null) }
//    var isLoading by rememberSaveable(model) { mutableStateOf(true) }
//
//
//    Box(
//        modifier = modifier,
//        contentAlignment = Alignment.Center,
//    ) {
//        AsyncImage(
//            modifier = Modifier.fillMaxSize(),
//            model = model,
//            contentDescription = contentDescription,
//            contentScale = contentScale,
//        )
//
//        AnimatedVisibility(
//            visible = isLoading,
//            enter = fadeIn(),
//            exit = fadeOut(),
//        ) {
//
//                Image(
//                    modifier = Modifier.fillMaxSize(),
//                    bitmap = (placeholderBitmap as Bitmap).asImageBitmap(),
//                    contentDescription = contentDescription,
//                    contentScale = contentScale,
//                )
//        }
//    }
//}
//
//
//@Composable
//fun PhotoPickerDemoScreen() {
//    //The URI of the photo that the user has picked
//    var selectedImageUri by remember {
//        mutableStateOf<Uri?>(null)
//    }
//
//    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickVisualMedia(),
//        onResult = { uri -> selectedImageUri = uri }
//    )
//
//
//    Column {
//        AsyncImage(
//            model = selectedImageUri,
//            contentDescription = null,
//            modifier = Modifier.fillMaxWidth(),
//            contentScale = ContentScale.Crop
//        )
//
//        Button(
//            onClick = {
//                singlePhotoPickerLauncher.launch(
//                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
//                )
//            }
//        ) {
//            Text(text = "Pick photo")
//        }
//    }
//}


