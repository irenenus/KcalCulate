package com.example.kcalculate.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kcalculate.R
import com.example.kcalculate.data.entities.IngredientEntity
import com.example.kcalculate.databinding.IngredientsRowBinding
import com.example.kcalculate.ui.listener.IngredientClickListener

class IngredientsAdapter(val context: Context?, val clickListener: IngredientClickListener)
    : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    var ingredientsList: List<IngredientEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val viewBinding: IngredientsRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.ingredients_row, parent, false)
        return IngredientsViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun setIngredients(ingredients: List<IngredientEntity>) {
        this.ingredientsList = ingredients
        notifyDataSetChanged()
    }

    inner class IngredientsViewHolder(private val viewBinding: IngredientsRowBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            val row = ingredientsList[position]
            viewBinding.ingredients = row
            viewBinding.ingredientClickInterface = clickListener
        }
    }
}