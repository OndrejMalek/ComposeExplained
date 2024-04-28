package eu.malek.composesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import eu.malek.composesample.ui.theme.ComposeSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    var dpSDFSDF4658 by remember { mutableStateOf(testDataClassOf()) }

                    Column {
                        Button(onClick = {
                            dpSDFSDF4658 = dpSDFSDF4658.copy(text8798SDF = "${dpSDFSDF4658.text8798SDF}?")
                        }) {
                            Text(dpSDFSDF4658.text8798SDF)
                        }

                        Greeting("${dpSDFSDF4658.text8798SDF}_0", dp5xcv = dpSDFSDF4658)
                        Greeting("${dpSDFSDF4658.text8798SDF}_1", dp5xcv = dpSDFSDF4658)
                        Greeting("${dpSDFSDF4658.text8798SDF}_2", dp5xcv = dpSDFSDF4658)
                        Greeting("${dpSDFSDF4658.text8798SDF}_3", dp5xcv = dpSDFSDF4658)

                        Greetings(dpSDFSDF4658)

                        GreetingsGreetings(dpSDFSDF4658)
                    }

                }
            }
        }
    }


}

data class TestDataClass(
    val text8798SDF: String,
    val redundant1: String,
    val redundant2: String,
    val redundant3: String,
    val redundant4: String,
    val dp54jh45: TestDataClass?
)

fun testDataClassOf() = TestDataClass(
        "Greeting", "", "", "", "", TestDataClass("sub", "", "", "", "", null)
)

@Composable
private fun GreetingsGreetings(dpGH896: TestDataClass?) {
    Greetings(dpGH896)
    Greetings(dpGH896)
    Greetings(dpGH896?.dp54jh45)
}

@Composable
private fun Greetings(dpGHJ546: TestDataClass?) {
    Greeting("${dpGHJ546?.text8798SDF}0", dp5xcv = dpGHJ546)
    Greeting("${dpGHJ546?.text8798SDF}1", dp5xcv = dpGHJ546)
    Greeting("${dpGHJ546?.text8798SDF}2", dp5xcv = dpGHJ546)
    Greeting("${dpGHJ546?.text8798SDF}3", dp5xcv = dpGHJ546)
}

@Composable
fun Greeting(textFhfg78: String, modifier: Modifier = Modifier, dp5xcv: TestDataClass?) {
    Text(
        text = "$textFhfg78!",
        modifier = modifier
    )

    Text(
        text = "dp=${dp5xcv?.text8798SDF}}!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeSampleTheme {
        Greeting("Android", dp5xcv = testDataClassOf())
    }
}