package com.mashup.damgledamgle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mashup.damgledamgle.presentation.navigation.DamgleDamgleNavGraph
import com.mashup.damgledamgle.ui.theme.DamgleDamgleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DamgleDamgleTheme {
                navController = rememberNavController()
                DamgleDamgleNavGraph(navController = navController)
            }
        }
    }
}
