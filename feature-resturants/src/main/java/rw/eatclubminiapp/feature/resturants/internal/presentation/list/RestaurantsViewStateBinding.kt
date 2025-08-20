package rw.eatclubminiapp.feature.resturants.internal.presentation.list

import rw.eatclubminiapp.feature.resturants.internal.presentation.list.model.RestaurantListItem

internal data class RestaurantsViewStateBinding(
    val searchTextFieldState: SearchTextFieldState,
    val layout: Layout
) {

    sealed interface Layout {
        data object Loading: Layout

        data class Content(
            val restaurants: List<RestaurantListItem>
        ): Layout

        data class Error(val message: String): Layout
    }

    data class SearchTextFieldState(
        val placeHolder: String,
        val text: String,
        val onTextChange: (String) -> Unit
    )
}