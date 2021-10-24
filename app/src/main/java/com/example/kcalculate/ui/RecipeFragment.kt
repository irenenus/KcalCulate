package com.example.kcalculate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kcalculate.data.entities.IngredientEntity
import com.example.kcalculate.databinding.FragmentRecipeBinding
import com.example.kcalculate.ui.MainActivity
import com.example.kcalculate.ui.adapters.IngredientsAdapter
import com.example.kcalculate.ui.listener.IngredientClickListener
import com.example.kcalculate.viewmodel.IngredientsViewModel
import kotlinx.android.synthetic.main.fragment_recipe.*
import kotlinx.android.synthetic.main.ingredients_row.*
import org.koin.android.ext.android.inject


class RecipeFragment : Fragment(), IngredientClickListener{

    private val viewModel: IngredientsViewModel by inject()
    private lateinit var ingredientsAdapter: IngredientsAdapter
    private lateinit var mViewDataBinding: FragmentRecipeBinding


    companion object {
        fun newInstance(): RecipeFragment{
            return RecipeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe, container, false)
        val mRootView = mViewDataBinding.root
        mViewDataBinding.lifecycleOwner = this
        return mRootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onCreate(savedInstanceState)
        setView()
        mViewDataBinding.viewModel = viewModel

        viewModel.getIngredientsList()
        viewModel.ingredientsList.observe(viewLifecycleOwner, Observer {
            Log.d("@@ingredients", it?.size.toString())
            if (it?.isNotEmpty() == true) {
                ingredientsAdapter.setIngredients(it)
            }
        })

    }

    private fun setView() {
        ingredientsAdapter = IngredientsAdapter(context, this)
        ingredientsRecyclerView.apply{
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = ingredientsAdapter
            isNestedScrollingEnabled = false
        }
    }

    override fun onItemClick(ingredients : IngredientEntity) {
       /*
       TODO: add a detail view to show ingredient detail
       (activity as MainActivity).replaceFragment(IngredientsDetailsFragment.newInstance(country),
            R.id.fragment_container, "Ingredientsdetails")
        */
    }
}