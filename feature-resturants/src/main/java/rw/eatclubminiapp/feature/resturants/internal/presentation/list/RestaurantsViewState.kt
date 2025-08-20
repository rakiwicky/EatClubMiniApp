package rw.eatclubminiapp.feature.resturants.internal.presentation.list

import android.content.res.Resources
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import rw.eatclubminiapp.feature.resturants.R
import rw.eatclubminiapp.feature.resturants.internal.presentation.list.RestaurantsViewStateBinding.Layout
import rw.eatclubminiapp.feature.resturants.internal.presentation.list.model.RestaurantListItem

internal class RestaurantsViewState @AssistedInject constructor(
    resources: Resources,
    @Assisted callback: Callback
) {

    private val _binding: MutableStateFlow<RestaurantsViewStateBinding> = MutableStateFlow(
        RestaurantsViewStateBinding(
            searchTextFieldState = RestaurantsViewStateBinding.SearchTextFieldState(
                placeHolder = resources.getString(R.string.feature_restaurants_search_field_placeholder),
                text = "",
                onTextChange = callback.onTextChange
            ),
            layout = Layout.Loading
        )
    )
    val binding: StateFlow<RestaurantsViewStateBinding> = _binding

    // Render a new state
    fun render(targetState: TargetState) {
        _binding.update {
            when (targetState) {
                is TargetState.Content -> it.copy(layout = Layout.Content(targetState.restaurants))
                is TargetState.Error -> it.copy(layout = Layout.Error(targetState.message))
                is TargetState.Loading -> it.copy(layout = Layout.Loading)
            }
        }
    }

    fun onTextChange(text: String) {
        _binding.update {
            it.copy(searchTextFieldState = it.searchTextFieldState.copy(text = text))
        }
    }

    data class Callback(
        val onTextChange: (String) -> Unit
    )

    sealed interface TargetState {
        data object Loading : TargetState

        data class Content(
            val restaurants: List<RestaurantListItem>
        ) : TargetState

        data class Error(val message: String) : TargetState
    }

    @AssistedFactory
    interface Factory {
        fun create(callback: Callback): RestaurantsViewState
    }
}