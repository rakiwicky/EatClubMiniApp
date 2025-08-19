package rw.eatclubminiapp.feature.resturants.internal.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import rw.eatclubminiapp.feature.resturants.internal.presentation.detail.RestaurantDetailViewStateBinding.Layout
import rw.eatclubminiapp.library.commoncompose.theme.EatClubMiniAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RestaurantDetailScreen() {

    val viewModel: RestaurantDetailViewModel = hiltViewModel()
    val layout by viewModel.binding.collectAsState()

    EatClubMiniAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = { Text("Restaurant Details") })
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                when (val state = layout) {
                    is Layout.Content -> {
                        LazyColumn {
                            val restaurant = state.restaurant

                            item {
                                AsyncImage(
                                    model = restaurant.imageLink,
                                    modifier = Modifier.fillMaxWidth().height(180.dp),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }

                    is Layout.Error -> Text(state.message)
                    Layout.Loading -> CircularProgressIndicator()
                }
            }
        }
    }
}