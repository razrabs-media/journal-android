package ru.razrabs

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import ru.razrabs.design.theming.background
import ru.razrabs.feature_auth.domain.AuthHandler
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

        val githubAuthText: AuthHandler = get()
        CoroutineScope(Dispatchers.IO).launch {
            val result = githubAuthText.auth(this@MainActivity)
            println(result)
            println(result)
            println(result)
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