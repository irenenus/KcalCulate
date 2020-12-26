package com.example.kcalculate.model

data class Food(
    var id: Int,
    var name: String,
    var kcal: Int,
    var carbohydrate: Int,
    var fat: Int,
    var protein: Int,
    var sugar: Int,
    var salt: Int,
    var fiber: Int
) {
    constructor() : this(0, "", 0,0,0,0,0,0,0) {}
}