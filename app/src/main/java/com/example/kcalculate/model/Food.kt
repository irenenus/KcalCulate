package com.example.kcalculate.model

data class Food(
    var id: Int,
    var name: String,
    var kcal: Int
) {
    constructor() : this(0, "", 0) {}
}