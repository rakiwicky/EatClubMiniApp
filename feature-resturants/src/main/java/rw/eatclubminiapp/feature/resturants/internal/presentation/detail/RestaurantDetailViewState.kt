package rw.eatclubminiapp.feature.resturants.internal.presentation.detail

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import rw.eatclubminiapp.feature.resturants.internal.presentation.detail.model.RestaurantDetailItem
import rw.eatclubminiapp.feature.resturants.internal.presentation.detail.RestaurantDetailViewStateBinding.Layout

internal class RestaurantDetailViewState @AssistedInject constructor(
    @Assisted callback: Callback
) {

    private val _binding: MutableStateFlow<RestaurantDetailViewStateBinding> = MutableStateFlow(
        RestaurantDetailViewStateBinding(
            onBackPressed = callback.onBackPressed,
            layout = Layout.Loading
        )
    )
    val binding: StateFlow<RestaurantDetailViewStateBinding> = _binding

    // Render a new state
    fun render(targetState: TargetState) {
        _binding.update {
            when (targetState) {
                is TargetState.Content -> it.copy(layout = Layout.Content(targetState.restaurant))
                is TargetState.Error -> it.copy(layout = Layout.Error(targetState.message))
                is TargetState.Loading -> it.copy(layout = Layout.Loading)
            }
        }
    }

    data class Callback(
        val onBackPressed: suspend () -> Unit
    )

    sealed interface TargetState {
        data object Loading : TargetState

        data class Content(
            val restaurant: RestaurantDetailItem
        ) : TargetState

        data class Error(val message: String) : TargetState
    }

    @AssistedFactory
    interface Factory {
        fun create(callback: Callback): RestaurantDetailViewState
    }
}