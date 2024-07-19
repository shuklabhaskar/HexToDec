package com.example.mhawk

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import coil.compose.rememberAsyncImagePainter
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.mhawk.ui.theme.HexToDecTheme


object MainScreen : Screen {

    private fun readResolve(): Any = MainScreen


    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel {
            MainScreenViewModel()
        }
        MainView(viewModel = viewModel)
    }

    @Composable
    fun MainView(viewModel: MainScreenViewModel) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            // LOGO
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(150.dp)
                    .padding(32.dp),
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = null
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.input,
                onValueChange = {
                    viewModel.input = it
                    viewModel.output = it.text.replace(":", "")
                },
                label = {
                    Text(text = "ENTER OR TAP CARD")
                }
            )

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = viewModel.output, modifier = Modifier)

                    Image(
                        modifier = Modifier.clickable {
                            viewModel.copy()
                        },
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Copy"
                    )
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .shadow(5.dp, shape = RoundedCornerShape(10.dp)),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    viewModel.hexToDecimal()
                }
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "CONVERT IT",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyLarge
                )
            }


        }
    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    HexToDecTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()

        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                MainScreen.MainView(viewModel = MainScreenViewModel())
            }

        }
    }
}