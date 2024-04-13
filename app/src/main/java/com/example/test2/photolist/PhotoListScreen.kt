package com.example.test2.photolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import nl.move.domain.photos.model.Photo
import com.example.test2.generic.compose.ErrorComposable
import com.example.test2.generic.compose.LoadingComposable
import com.example.test2.generic.compose.TopAppBarComposable
import nl.move.generic.spacing.Spacing.x0_5
import nl.move.generic.spacing.Spacing.x1_25
import nl.move.generic.spacing.Spacing.x2_5
import nl.move.presentation.photolist.PhotoListEvent
import nl.move.presentation.photolist.PhotoListViewModel
import nl.move.presentation.util.TypedUIState
import org.koin.androidx.compose.koinViewModel

@Destination<RootGraph>(start = true)
@Composable
fun PhotoListScreen(
    destinationsNavigator: DestinationsNavigator,
    viewModel: PhotoListViewModel = koinViewModel()
) {
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val navigator = remember(destinationsNavigator) { PhotoListNavigator(destinationsNavigator) }

    LaunchedEffect(Unit) {
        viewModel.navigation.retrieveEach { event ->
            when (event) {
                is PhotoListEvent.PhotoClicked -> navigator.openDetails(event.id)
                null -> Unit
            }
        }
    }

    when (val currentUiState = uiState) {
        TypedUIState.Loading -> LoadingComposable(modifier = Modifier.fillMaxSize())
        is TypedUIState.Normal -> {
            Content(
                photos = currentUiState.data,
                isRefreshing = isRefreshing,
                onRefresh = viewModel::onRefresh,
                onPhotoClicked = viewModel::onPhotoClicked,
            )
        }
        is TypedUIState.Error -> {
            ErrorComposable(
                onRetry = viewModel::onRetryClicked,
                modifier = Modifier.fillMaxSize(),
            )
        }
        else -> Unit
    }
}

@Composable
fun Content(
    photos: List<Photo>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onPhotoClicked: (Photo) -> Unit,
    modifier: Modifier = Modifier,
) {
    //val refreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = onRefresh)
    val listState = rememberLazyListState()

    Column(
        modifier = modifier
    ) {
        TopAppBarComposable()

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {

            LazyColumn(
                //modifier = Modifier.pullRefresh(refreshState),
                state = listState,
            ) {
                items(photos) { photo ->
                    PhotoRow(
                        photo = photo,
                        modifier = Modifier.padding(x1_25)
                            .fillMaxWidth()
                            .clickable { onPhotoClicked(photo) }
                    )
                }
            }

            //PullRefreshIndicator(refreshing = isRefreshing, state = refreshState)
        }
    }
}

@Composable
private fun PhotoRow(
    photo: Photo,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier.size(148.dp, 148.dp)
                .clip(RoundedCornerShape(10.dp)),
            model = photo.thumbnailUrl,
            contentDescription = photo.title,
        )

        Column(
            modifier = Modifier.padding(start = x2_5)
                .fillMaxWidth()
        ) {
            Text(
                text = photo.title,
                modifier = Modifier.padding(x0_5)
            )

            Text(
                text = photo.url,
                modifier = Modifier.padding(x0_5)
            )
        }
    }
}