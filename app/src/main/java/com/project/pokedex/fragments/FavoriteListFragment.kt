package com.project.pokedex.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.project.pokedex.R
import com.project.pokedex.adapters.FavoriteListAdapter
import com.project.pokedex.adapters.PokemonListAdapter
import com.project.pokedex.network.viewmodels.FavoriteListViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_pokemon_view.*


class FavoriteListFragment : Fragment() {
    private val viewModel: FavoriteListViewModel by viewModels()
    private val adapter =  FavoriteListAdapter()
//    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonRecyclerView.adapter = adapter

        viewModel.getallPokemon().observe(viewLifecycleOwner) { pokemonList ->
            adapter.favoriteList = pokemonList
        }
    }
}