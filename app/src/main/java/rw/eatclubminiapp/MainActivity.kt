package rw.eatclubminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import rw.eatclubminiapp.feature.resturants.RestaurantsNavGraph
import rw.eatclubminiapp.feature.resturants.restaurantsNavGraph
import rw.eatclubminiapp.library.commoncompose.theme.EatClubMiniAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            EatClubMiniAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = RestaurantsNavGraph
                ) {
                    restaurantsNavGraph(navController, this@NavHost)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EatClubMiniAppTheme {

    }
}