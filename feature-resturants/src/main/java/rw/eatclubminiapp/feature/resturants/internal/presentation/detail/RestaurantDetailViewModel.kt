package rw.eatclubminiapp.feature.resturants.internal.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rw.eatclubminiapp.feature.resturants.internal.destination.RestaurantsDestination
import rw.eatclubminiapp.feature.resturants.internal.domain.usecase.GetRestaurantDetailUseCase
import rw.eatclubminiapp.feature.resturants.internal.presentation.detail.mapper.RestaurantDetailItemMapper
import rw.eatclubminiapp.feature.resturants.internal.presentation.detail.RestaurantDetailViewState.TargetState
import javax.inject.Inject

@HiltViewModel
internal class RestaurantDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRestaurantDetailUseCase: GetRestaurantDetailUseCase,
    private val mapper: RestaurantDetailItemMapper,
    viewState: RestaurantDetailViewState
): ViewModel() {

    private val restaurantId = savedStateHandle.toRoute<RestaurantsDestination.DetailScreen>().restaurantId

    val binding = viewState.binding

    init {
        viewState.render(TargetState.Loading)
        viewModelScope.launch {
            try {
                val restaurant = mapper.create(
                    getRestaurantDetailUseCase(restaurantId),
                )
                viewState.render(TargetState.Content(restaurant))
            } catch (e: Exception) {
                viewState.render(TargetState.Error(e.message ?: "Something went wrong"))
            }
        }
    }
}