package ru.razrabs

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import ru.razrabs.design.theming.background
import ru.razrabs.ui.theme.RazrabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController().apply {
                addOnDestinationChangedListener { controller, destination, arguments ->
                    Toast.makeText(
                        context,
                        destination.route ?: "unknown",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
            RazrabsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = background()
                ) {
                    SetupMainNavigation(
                        navController = navController,
                        finishRootScreenAction = {
                            finish()
                        })
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RazrabsTheme {
        Greeting("Android")
    }
}