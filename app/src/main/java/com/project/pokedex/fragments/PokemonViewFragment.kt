package com.project.pokedex.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.project.pokedex.R
import com.project.pokedex.adapters.PokemonListAdapter
import com.project.pokedex.network.viewmodels.PokemonListviewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pokemon_view.*
import kotlinx.android.synthetic.main.pokemon_item_view_holder.*


class PokemonViewFragment : Fragment() {
    private val viewModel: PokemonListviewModel by viewModels()
    private val adapter =  PokemonListAdapter()
    private val disposables: CompositeDisposable = CompositeDisposable()


//    private val adapter = PokemonListAdapter { pokemon ->
//        findNavController().navigate(R.id.action_pokemonViewFragment_to_pokemonDetailFragment)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel.getPokemonList()
        return inflater.inflate(R.layout.fragment_pokemon_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonRecyclerView.adapter = adapter
//        ESTO DEL VIDEO DE RX
        viewModel.getPokemonList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {pokemonList ->
                adapter.pokemonList = pokemonList
            }

        disposables.add(adapter.itemClicked
            .subscribe {
                val action = PokemonViewFragmentDirections.actionPokemonViewFragmentToPokemonDetailFragment(textViewPokemonID.text.toString().replace("ID: ", ""))
                findNavController().navigate(action)
            })

        // Esto es cuando se usa LIVEDATA
//        ViewModel.getPokemonListResponse().observe(viewLifecycleOwner) { pokemonList ->
//            adapter.pokemonList = pokemonList
//        }

        viewModel.getIsError().observe(viewLifecycleOwner) { isError ->
             Snackbar.make(parent, R.string.error_text, Snackbar.LENGTH_LONG).show()
        }


    }

    override fun onDestroyView() {
        disposables.clear()
        super.onDestroyView()
    }

}