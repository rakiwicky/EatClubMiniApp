package rw.eatclubminiapp.feature.resturants.internal.presentation.detail

import rw.eatclubminiapp.feature.resturants.internal.presentation.detail.model.RestaurantDetailItem

internal data class RestaurantDetailViewStateBinding(
    val onBackPressed: suspend () -> Unit,
    val layout: Layout
) {

    sealed interface Layout {
        data object Loading: Layout

        data class Content(
            val restaurantDetailItem: RestaurantDetailItem
        ): Layout

        data class Error(val message: String): Layout
    }
}