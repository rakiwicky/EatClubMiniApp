package rw.eatclubminiapp.feature.resturants.internal.presentation.detail.model

internal data class RestaurantDetailItem(
    val name: String,
    val suburb: String,
    val cuisines: String,
    val imageLink: String,
    val deals: List<DealItem>
) {
    data class DealItem(
        val discount: Int,
        val availabilityInfo: String,
        val qtyLeft: Int
    )
}