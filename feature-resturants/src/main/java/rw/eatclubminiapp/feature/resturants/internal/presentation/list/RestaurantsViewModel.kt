package rw.eatclubminiapp.feature.resturants.internal.presentation.list

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eatclubminiapp.library.navigation.Navigate
import com.eatclubminiapp.library.navigation.NavigationAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import rw.eatclubminiapp.feature.resturants.R
import rw.eatclubminiapp.feature.resturants.internal.destination.RestaurantsDestination
import rw.eatclubminiapp.feature.resturants.internal.domain.model.Restaurant
import rw.eatclubminiapp.feature.resturants.internal.domain.usecase.GetRestaurantsUseCase
import rw.eatclubminiapp.feature.resturants.internal.domain.usecase.SearchRestaurantsUseCase
import rw.eatclubminiapp.feature.resturants.internal.presentation.list.RestaurantsViewState.TargetState
import rw.eatclubminiapp.feature.resturants.internal.presentation.list.mapper.RestaurantListItemMapper
import javax.inject.Inject

@HiltViewModel
internal class RestaurantsViewModel @Inject constructor(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val searchRestaurantsUseCase: SearchRestaurantsUseCase,
    private val mapper: RestaurantListItemMapper,
    private val resources: Resources,
    viewStateFactory: RestaurantsViewState.Factory
) : ViewModel() {

    private val _navigationActions = MutableSharedFlow<NavigationAction>()
    val navigationActions: SharedFlow<NavigationAction> = _navigationActions

    private val viewState = viewStateFactory.create(
        RestaurantsViewState.Callback(
            onTextChange = ::onTextChange
        )
    )

    val binding = viewState.binding

    init {
        viewState.render(TargetState.Loading)
        viewModelScope.launch {
            try {
                val restaurants = mapper.create(
                    getRestaurantsUseCase(),
                    onItemClick = ::onRestaurantItemClicked,
                )
                viewState.render(TargetState.Content(restaurants))
            } catch (e: Exception) {
                viewState.render(TargetState.Error(e.message ?: resources.getString(R.string.feature_restaurants_list_screen_loading_error)))
            }
        }
    }

    private fun onTextChange(text: String) {
        viewState.render(TargetState.Loading)
        viewModelScope.launch {
            try {
                val restaurants = mapper.create(
                    searchRestaurantsUseCase(text),
                    onItemClick = ::onRestaurantItemClicked,
                )
                viewState.render(TargetState.Content(restaurants))
                viewState.onTextChange(text)
            } catch (e: Exception) {
                viewState.render(TargetState.Error(e.message ?: resources.getString(R.string.feature_restaurants_list_screen_loading_error)))
            }
        }
    }

    private suspend fun onRestaurantItemClicked(restaurant: Restaurant) {
        _navigationActions.emit(
            Navigate(RestaurantsDestination.DetailScreen(restaurant.id))
        )
    }
}