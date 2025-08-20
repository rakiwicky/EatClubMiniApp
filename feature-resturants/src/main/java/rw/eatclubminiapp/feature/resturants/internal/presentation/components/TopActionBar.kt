package rw.eatclubminiapp.feature.resturants.internal.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.PermIdentity
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun TopActionbar() {
    TopAppBar(
        title = {},
        actions = {
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Outlined.PermIdentity,
                    contentDescription = null
                )
            }

            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Restaurant,
                    contentDescription = null
                )
            }

            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Outlined.FilterList,
                    contentDescription = null
                )
            }
        }
    )
}