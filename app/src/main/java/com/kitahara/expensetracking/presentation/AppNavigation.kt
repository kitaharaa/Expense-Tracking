package com.kitahara.expensetracking.presentation

sealed class AppNavigation(val destination: String) {
    data object Home : AppNavigation("Home")
    data object TransactionAdding : AppNavigation("TransactionCreation")
}