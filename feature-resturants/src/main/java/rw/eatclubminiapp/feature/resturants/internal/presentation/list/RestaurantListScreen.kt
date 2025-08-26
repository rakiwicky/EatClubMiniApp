package rw.eatclubminiapp.feature.resturants.internal.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import rw.eatclubminiapp.feature.resturants.R
import rw.eatclubminiapp.feature.resturants.internal.presentation.components.TopActionbar
import rw.eatclubminiapp.feature.resturants.internal.presentation.list.RestaurantsViewStateBinding.Layout
import rw.eatclubminiapp.feature.resturants.internal.presentation.list.model.RestaurantListItem
import rw.eatclubminiapp.library.commoncompose.theme.EatClubMiniAppTheme

@Composable
internal fun RestaurantListScreen(
    navigationController: NavController
) {
    val viewModel: RestaurantsViewModel = hiltViewModel()
    val binding by viewModel.binding.collectAsState()

    Screen(binding)

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
    private fun Screen(binding: RestaurantsViewStateBinding) {
    EatClubMiniAppTheme {
        Scaffold(
            modifier = Modifier
                .background(colorScheme.background),
            topBar = { TopActionbar() }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalDivider(thickness = 4.dp)

                TextField(
                    value = binding.searchTextFieldState.text,
                    onValueChange = binding.searchTextFieldState.onTextChange,
                    placeholder = { Text(binding.searchTextFieldState.placeHolder) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null
                        )
                    },
                    colors = TextFieldDefaults.colors (
                        focusedTextColor = colorScheme.onPrimary,
                        unfocusedTextColor = colorScheme.onPrimary,
                        focusedContainerColor = colorScheme.surface,
                        unfocusedContainerColor = colorScheme.surface,
                        cursorColor = colorScheme.onPrimary,
                    )
                )

                when (val state = binding.layout) {
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
}

@Composable
private fun RestaurantListItem(
    restaurantListItem: RestaurantListItem,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .padding(16.dp)
            .clickable(
                onClick = { scope.launch { restaurantListItem.itemOnClick() } }
            ),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AsyncImage(
            model = restaurantListItem.imageLink,
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.place_holder_restaurant_img),
            error = painterResource(R.drawable.place_holder_restaurant_img)
        )

        Row {
            Text(
                modifier = modifier.weight(1f),
                text = restaurantListItem.name,
                style = typography.titleMedium
            )

            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = null
                )
            }
        }

        Text(
            text = restaurantListItem.suburb,
            style = typography.bodyMedium,
            color = colorScheme.onSecondary
        )

        Text(
            text = restaurantListItem.cuisines,
            style = typography.labelSmall,
            color = colorScheme.onSecondary
        )

        Spacer(Modifier.height(8.dp))
    }
}

@Preview
@Composable
fun Preview_RestaurantListScreen() {
    Screen(
        binding = RestaurantsViewStateBinding(
            searchTextFieldState = RestaurantsViewStateBinding.SearchTextFieldState(
                placeHolder = stringResource(R.string.feature_restaurants_search_field_placeholder),
                text = "",
                onTextChange = {}
            ),
            layout = Layout.Content(
                listOf(
                    RestaurantListItem(
                        name = "Ferdinand",
                        suburb = "Lower east",
                        cuisines = "Chinese, Japanese",
                        imageLink = "https://demo.eccdn.com.au/images/D80263E8-FD89-2C70-FF6B-D854ADB8DB00/eatclub_1634706351211.jpg",
                        deals = emptyList(),
                        itemOnClick = {}
                    ),
                    RestaurantListItem(
                        name = "Restaurant Name",
                        suburb = "Glen Waverer",
                        cuisines = "Chinese",
                        imageLink = "https://picsum.photos/200",
                        deals = emptyList(),
                        itemOnClick = {}
                    ),
                    RestaurantListItem(
                        name = "Restaurant Name",
                        suburb = "Tarneit",
                        cuisines = "Chinese, Japanese, Italian",
                        imageLink = "https://picsum.photos/200",
                        deals = emptyList(),
                        itemOnClick = {}
                    )
                )
            )
        )
    )
}
