package com.example.lemonade

import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.preference.PreferenceActivity.Header
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {

                    LemonadeApp()

            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {

    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(
                title = { Text("Lemonade")
                },

            )
        }
    ) { paddingValues -> // Capture the padding from Scaffold
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply padding to prevent overlap with the top bar
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background,
        ) {


        when (currentStep) {
            1 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {



                    LemonTextAndImage(textId = R.string.select_lemon,
                        imageId = R.drawable.lemon_tree,
                        imageContentDesc =R.string.lemon_tree_content_description ,
                        onClick = {currentStep = 2
                            squeezeCount = (2..4).random()}
                    )

                }

            }

            2 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {


                    LemonTextAndImage(textId = R.string.squeeze_lemon,
                        imageId = R.drawable.lemon_squeeze,
                        imageContentDesc =R.string.lemon_content_description ,
                        onClick = {
                            squeezeCount--
                            if (squeezeCount == 0){
                                currentStep = 3
                            }

                        } )

                }

            }

            3 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {


                    LemonTextAndImage(textId = R.string.drink_lemon,
                        imageId = R.drawable.lemon_drink,
                        imageContentDesc =R.string.glass_lemonade_content_description ,
                        onClick = {currentStep = 4 } )

                }

            }

            4 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {

                    LemonTextAndImage(textId = R.string.start_again,
                        imageId = R.drawable.lemon_restart,
                        imageContentDesc =R.string.empty_glass_content_descr ,
                        onClick = {currentStep = 1 } )

                }

            }
        }

    }
}
}


@Composable
fun LemonTextAndImage(textId: Int, imageId: Int,  imageContentDesc: Int, onClick:() -> Unit){


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

        Button(onClick =  onClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier

                .border(2.dp, color = Color.Cyan, shape = RoundedCornerShape(20.dp)
                )

        ){
            Image(painter = painterResource(imageId),

                contentDescription = stringResource(imageContentDesc),
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .padding(20.dp)


                    .clickable { onClick() }
            )

        }

        Spacer(modifier =  Modifier.height(16.dp))
        Text(text = stringResource(textId) , fontSize = 18.sp)

    }

}

@Composable
@Preview(showBackground = true)
fun LemonadePreview(){

    LemonadeTheme {
        LemonadeApp()
    }
}
