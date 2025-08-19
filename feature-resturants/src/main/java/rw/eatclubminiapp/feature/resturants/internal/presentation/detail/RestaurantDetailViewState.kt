package rw.eatclubminiapp.feature.resturants.internal.presentation.detail

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import rw.eatclubminiapp.feature.resturants.internal.presentation.detail.model.RestaurantDetailItem
import rw.eatclubminiapp.feature.resturants.internal.presentation.detail.RestaurantDetailViewStateBinding.Layout
import javax.inject.Inject

internal class RestaurantDetailViewState @Inject constructor() {

    private val _binding = MutableStateFlow<Layout>(Layout.Loading)
    val binding: StateFlow<Layout> = _binding

    // Render a new state
    fun render(targetState: TargetState) {
        _binding.update {
            when (targetState) {
                is TargetState.Content -> Layout.Content(targetState.restaurant)
                is TargetState.Error -> Layout.Error(targetState.message)
                is TargetState.Loading -> Layout.Loading
            }
        }
    }

    sealed interface TargetState {
        data object Loading : TargetState

        data class Content(
            val restaurant: RestaurantDetailItem
        ) : TargetState

        data class Error(val message: String) : TargetState
    }
}