package rw.eatclubminiapp.feature.resturants.internal.presentation.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import rw.eatclubminiapp.feature.resturants.R
import rw.eatclubminiapp.feature.resturants.internal.presentation.components.TopActionbar
import rw.eatclubminiapp.feature.resturants.internal.presentation.detail.RestaurantDetailViewStateBinding.Layout
import rw.eatclubminiapp.feature.resturants.internal.presentation.detail.model.RestaurantDetailItem
import rw.eatclubminiapp.library.commoncompose.theme.EatClubMiniAppTheme

@Composable
internal fun RestaurantDetailScreen(
    navigationController: NavController
) {

    val viewModel: RestaurantDetailViewModel = hiltViewModel()
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
private fun Screen(binding: RestaurantDetailViewStateBinding) {
    val scope = rememberCoroutineScope()

    EatClubMiniAppTheme {
        Scaffold(
            modifier = Modifier
                .background(colorScheme.background),
            topBar = { TopActionbar(
                onBackPressed =  { scope.launch { binding.onBackPressed() } }
            ) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (val state = binding.layout) {
                    is Layout.Content -> {
                        val restaurant = state.restaurantDetailItem

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            AsyncImage(
                                model = restaurant.imageLink,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                placeholder = painterResource(R.drawable.place_holder_restaurant_img),
                                error = painterResource(R.drawable.place_holder_restaurant_img)
                            )

                            HorizontalDivider(thickness = 4.dp)

                            RestaurantInfoSection(restaurant)

                            HorizontalDivider(thickness = 4.dp)

                            OtherInfoSection(restaurant)

                            HorizontalDivider(thickness = 4.dp)

                            restaurant.deals.forEach { dealItem ->
                                DealInfoSection(dealItem)
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
private fun DealInfoSection(dealItem: RestaurantDetailItem.DealItem) {
    Row {
        Column(
            Modifier.weight(1f)
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = dealItem.discount,
                style = typography.titleMedium,
                fontWeight = Bold,
                color = colorScheme.onTertiary,
                modifier = Modifier.align(Alignment.Start)
            )

            dealItem.availabilityInfo?.let {
                Text(
                    text = it,
                    style = typography.bodyMedium,
                    color = colorScheme.onSecondary,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Text(
                text = dealItem.qtyLeft,
                style = typography.bodySmall,
                color = colorScheme.onSecondary,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        OutlinedButton(
            border = BorderStroke(width = 1.dp, color = colorScheme.onTertiary),
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(
                text = "Redeem",
                color = colorScheme.onTertiary,
            )
        }
    }
}

@Composable
private fun OtherInfoSection(restaurant: RestaurantDetailItem) {
    Spacer(Modifier.height(8.dp))

    Row {
        Icon(
            imageVector = Icons.Outlined.AccessTime,
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = restaurant.openHours,
            style = typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
    }

    Spacer(Modifier.height(8.dp))

    Row {
        Icon(
            imageVector = Icons.Outlined.LocationOn,
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = restaurant.suburb,
            style = typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
    }

    Spacer(Modifier.height(8.dp))
}

@Composable
private fun RestaurantInfoSection(restaurant: RestaurantDetailItem) {
    Spacer(Modifier.height(16.dp))

    Text(
        text = restaurant.name,
        style = typography.titleLarge,
        textAlign = TextAlign.Center
    )

    Text(
        text = cuisinesText(restaurant.cuisines),
        style = typography.bodyLarge,
        textAlign = TextAlign.Center,
        color = colorScheme.onSecondary
    )

    Spacer(Modifier.height(16.dp))
}

private fun cuisinesText(cuisines: List<String>): AnnotatedString {
    return buildAnnotatedString {
        cuisines.forEachIndexed { index, cuisine ->
            append(cuisine)
            if (index != cuisines.lastIndex) {
                append(" • ")
            }
        }
    }
}

@Preview
@Composable
private fun Preview_RestaurantDetailScreen() {
    Screen(
        binding = RestaurantDetailViewStateBinding(
            onBackPressed = {},
            layout = Layout.Content(
                restaurantDetailItem = RestaurantDetailItem(
                    name = "The Meatball & Wine Bar",
                    suburb = "Richmond",
                    cuisines = listOf("Pizza, Casual Dining, Vegetarian"),
                    imageLink = "",
                    openHours = "Hours: 12:00PM - 11:00PM",
                    deals = listOf(
                        RestaurantDetailItem.DealItem(
                            discount = "30% Off",
                            availabilityInfo = "Between 12:00 pm - 3:00 pm",
                            qtyLeft = "10 Deals left"
                        ),
                        RestaurantDetailItem.DealItem(
                            discount = "30% Off",
                            availabilityInfo = "Between 5:00 pm - 9:00 pm",
                            qtyLeft = "5 Deals left"
                        )
                    )
                )
            )
        )
    )
}
