package br.com.okayamafilho.tcomposealcoolougaso

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.okayamafilho.tcomposealcoolougaso.ui.theme.TComposeAlcoolouGasoTheme
import java.security.Principal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TComposeAlcoolouGasoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.padding(
                top = 100.dp, start = 10.dp, end = 10.dp
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .width(400.dp),
            )
        }
        Row {
            Text(
                text = "Saiba a melhor opção para abastecimento do seu carro",
                fontSize = 14.sp,
            )
        }

        // Campos de texto
        var editAlcool by remember { mutableStateOf("") }
        var editGasolina by remember { mutableStateOf("") }
        var mostrarErroAlcool by remember { mutableStateOf(false) }
        var mostrarErroGasolina by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = editAlcool,
            onValueChange = { valorAlcool ->
                editAlcool = valorAlcool
                mostrarErroAlcool = valorAlcool.isNullOrBlank()
            },
            label = { Text("Álcool") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            isError = mostrarErroAlcool,
            singleLine = true,
            placeholder = { Text(text = ("Digite o preço do álcool ex: 1.90")) }
        )
        if (mostrarErroAlcool) {
            Text(
                text = "Digite o preço do álcool",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        OutlinedTextField(
            value = editGasolina,
            onValueChange = { valorGasolina ->
                editGasolina = valorGasolina
                mostrarErroGasolina = valorGasolina.isNullOrBlank()
            },
            label = { Text("Gasolina") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            isError = mostrarErroGasolina,
            singleLine = true,
            placeholder = { Text(text = ("Digite o preço da gasolina ex: 3.00")) }
        )
        if (mostrarErroGasolina) {
            Text(
                text = "Digite o preço do gasolina",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        // Botão
        var valorResultado by remember { mutableStateOf("") }

        Button(
            enabled = !mostrarErroAlcool && !mostrarErroGasolina,
            onClick = {
                val valorAcool = editAlcool.toDoubleOrNull() ?: 0
                val valorGasolina = editGasolina.toDoubleOrNull() ?: 0

                var precoAlcoolNumero = valorAcool.toDouble()
                var precoGasolinaNumero = valorGasolina.toDouble()

                val resultado = precoAlcoolNumero / precoGasolinaNumero

                if (resultado >= 0.7) {
                    valorResultado = "Melhor utilizar Gasolina"
                } else {
                    valorResultado = "Melhor utilizar Álcool"
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF0000A5), // Cor verde escura

            )
        ) {
            Text("Calcular")
        }
        // Campo de texto para exibir o resultado
        if (valorResultado.isBlank()) {
            1
            Text(
                text = "Resultado",
                color = Color.Blue,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        } else {
            Text(
                text = valorResultado,
                color = Color.Red,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TComposeAlcoolouGasoTheme {
        HomeScreen()
    }
}