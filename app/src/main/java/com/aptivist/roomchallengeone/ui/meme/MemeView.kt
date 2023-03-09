package com.aptivist.roomchallengeone.ui.meme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.aptivist.roomchallengeone.domain.models.MemeItem
import com.aptivist.roomchallengeone.ui.core.LoadingView
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import org.koin.androidx.compose.get

@Composable
fun MemeView(viewModel: MemeViewModel = get()) {



    val viewState = remember {
        viewModel.memeViewState
    }

    val memeList = remember {
        viewModel.memeList
    }

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(true){
        viewModel.getMemes()
        viewModel.memeViewActions.collect{
            when(it) {
                is MemeViewActions.ShowError ->
                {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        it.message, "Retry", SnackbarDuration.Short
                    )
                    if(result == SnackbarResult.ActionPerformed){
                        it.action.invoke()
                    }
                }
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        MemeViewContent(modifier = Modifier.padding(it), viewState.value, memeList)
    }

}

@Composable
fun MemeViewContent(
    modifier: Modifier = Modifier,
    viewState: MemeViewState,
    memeList: List<MemeItem>
) {
    Box(modifier = modifier.fillMaxSize()){
        when(viewState){
            MemeViewState.Idle -> {
                LazyVerticalGrid(modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        start = 12.dp,
                        top = 16.dp,
                        end = 12.dp,
                        bottom = 16.dp)
                ){
                    items(memeList) {
                        MemeItemView(modifier = Modifier. fillMaxSize(), it)
                    }
                }
            }
            MemeViewState.Loading -> LoadingView(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun MemeItemView(modifier:Modifier = Modifier, item: MemeItem) {
    Card(modifier = modifier
        .padding(8.dp)) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            CoilImage(imageModel = {item.description} , imageOptions = ImageOptions(
                contentScale = ContentScale.Inside,
                alignment = Alignment.Center
            ), failure = { Text(text = "Error loading image")})
            Text(text = item.description)
        }
    }
}

sealed class MemeViewState {
    object Idle: MemeViewState()
    object Loading: MemeViewState()
}
