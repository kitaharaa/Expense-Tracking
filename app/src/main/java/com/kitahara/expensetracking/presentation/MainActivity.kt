package com.kitahara.expensetracking.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kitahara.expensetracking.presentation.home.HomeScreen
import com.kitahara.expensetracking.presentation.transaction.TransactionScreen
import com.kitahara.expensetracking.presentation.ui.theme.ExpenseTrackingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExpenseTrackingTheme {
                val navController = rememberNavController()

                NavHost(navController, AppNavigation.Home.destination) {
                    composable(AppNavigation.Home.destination) {
                        HomeScreen()
                    }

                    composable(AppNavigation.TransactionCreation.destination) {
                        TransactionScreen()
                    }
                }
            }
        }
    }
}