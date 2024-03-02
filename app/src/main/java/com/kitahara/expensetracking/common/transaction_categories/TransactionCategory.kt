package com.kitahara.expensetracking.common.transaction_categories

//Expenses categories
enum class TransactionCategory(val category: String) {
    Groceries("groceries"),
    Taxi("taxi"),
    Restaurant("restaurant"),
    Electronics("electronics"),
    Other("other");
}