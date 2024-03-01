package com.kitahara.expensetracking.common.transaction_categories

enum class TransactionCategory(val category: String) {
    Groceries("groceries"),
    Taxi("taxi"),
    Restaurant("restaurant"),
    Electronics("electronics"),
    Other("other");
}