package rw.eatclubminiapp.feature.resturants.internal.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eatclubminiapp.library.navigation.Navigate
import com.eatclubminiapp.library.navigation.NavigationAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import rw.eatclubminiapp.feature.resturants.internal.destination.RestaurantsDestination
import rw.eatclubminiapp.feature.resturants.internal.domain.model.Restaurant
import rw.eatclubminiapp.feature.resturants.internal.domain.usecase.GetRestaurantsUseCase
import rw.eatclubminiapp.feature.resturants.internal.presentation.list.RestaurantsViewState.TargetState
import rw.eatclubminiapp.feature.resturants.internal.presentation.list.mapper.RestaurantListItemMapper
import javax.inject.Inject

@HiltViewModel
internal class RestaurantsViewModel @Inject constructor(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val mapper: RestaurantListItemMapper,
    viewState: RestaurantsViewState
): ViewModel() {

    private val _navigationActions = MutableSharedFlow<NavigationAction>()
    val navigationActions: SharedFlow<NavigationAction> = _navigationActions

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
                viewState.render(TargetState.Error(e.message ?: "Something went wrong"))
            }
        }
    }

    private suspend fun onRestaurantItemClicked(restaurant: Restaurant) {
        _navigationActions.emit(
            Navigate(RestaurantsDestination.DetailScreen(restaurant.objectId))
        )
    }
}