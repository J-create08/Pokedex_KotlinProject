package com.project.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.project.pokedex.R
import com.project.pokedex.adapters.PokemonListAdapter
import com.project.pokedex.network.viewmodels.PokemonListviewModel
import com.project.pokedex.network.viewmodels.SavePokemonViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_pokemon_view.*
import kotlinx.android.synthetic.main.pokemon_item_view_holder.*


class PokemonViewFragment : Fragment() {
    private val viewModel: PokemonListviewModel by viewModels()
    private val saveViewModel: SavePokemonViewModel by viewModels()
    private val adapter =  PokemonListAdapter()
    private val disposables: CompositeDisposable = CompositeDisposable()
    private val args: PokemonViewFragmentArgs by navArgs()


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
            .subscribe { pokemonList ->
                adapter.pokemonList = pokemonList
            }

        trainerNameDisplay.text = args.name
        favoriteButton.setOnClickListener {
            findNavController().navigate(R.id.action_pokemonViewFragment_to_favoriteListFragment2)
        }

        disposables.add(adapter.itemClicked
            .subscribe {
                val action =
                    PokemonViewFragmentDirections.actionPokemonViewFragmentToPokemonDetailFragment(
                        it.name
                    )
                findNavController().navigate(action)
            })


        viewModel.getIsError().observe(viewLifecycleOwner) { isError ->
             Snackbar.make(parent, R.string.error_text, Snackbar.LENGTH_LONG).show()
        }


    }

    override fun onDestroyView() {
        disposables.clear()
        super.onDestroyView()
    }

}