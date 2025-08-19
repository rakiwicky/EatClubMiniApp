package rw.eatclubminiapp.feature.resturants.internal.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import rw.eatclubminiapp.feature.resturants.internal.presentation.list.RestaurantsViewStateBinding.Layout
import rw.eatclubminiapp.feature.resturants.internal.presentation.list.model.RestaurantListItem
import rw.eatclubminiapp.library.commoncompose.theme.EatClubMiniAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RestaurantListScreen(
    navigationController: NavController
) {
    val viewModel: RestaurantsViewModel = hiltViewModel()
    val layout by viewModel.binding.collectAsState()

    EatClubMiniAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
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
                            items(state.restaurants) { restaurantListItem ->
                                RestaurantListItem(restaurantListItem)
                            }
                        }
                    }

                    is Layout.Error -> Text(state.message)
                    Layout.Loading -> CircularProgressIndicator()
                }
            }
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.navigationActions.collect { navigationAction ->
                navigationAction.performAction(navigationController)
            }
        }
    }
}

@Composable
private fun RestaurantListItem(
    restaurantListItem: RestaurantListItem,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier.padding(16.dp)
            .background(colorScheme.primary)
            .clickable(
                onClick = { scope.launch { restaurantListItem.itemOnClick() }}
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = restaurantListItem.imageLink,
            modifier = modifier.fillMaxWidth().height(180.dp),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Text(
            text = restaurantListItem.name,
            style = typography.titleLarge
        )

        Text(
            text = restaurantListItem.suburb,
            style = typography.bodyMedium
        )

        Text(
            text = restaurantListItem.cuisines,
            style = typography.bodySmall
        )
    }
}