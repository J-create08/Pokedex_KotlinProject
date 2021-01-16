package com.project.pokedex.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.project.pokedex.R
import com.project.pokedex.db.Pokemon
import com.project.pokedex.network.viewmodels.DeletePokemonViewModel
import com.project.pokedex.network.viewmodels.PokemonDetailViewModel
import com.project.pokedex.network.viewmodels.SavePokemonViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*

class PokemonDetailFragment : Fragment() {
    val viewModel: PokemonDetailViewModel by viewModels()
    val args: PokemonDetailFragmentArgs by navArgs()
    val saveViewModel: SavePokemonViewModel by viewModels()
    val deleteViewModel: DeletePokemonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getPokemonDetail(args.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ pokemon ->
                pokemonDisplayName.text = pokemon.name.capitalize()
                idContent.text = pokemon.id.toString()
                weightContent.text =pokemon.weight.toString()
                heightContent.text = pokemon.height.toString()
                expContent.text = pokemon.baseExp.toString()
                val baseUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/132.png"
                val formattedUrl = baseUrl.replace("132", pokemon.id.toString())
                Glide.with(this)
                    .load(formattedUrl)
                    .into(this.pokemonImage)
            }

        favoriteIcon.setOnClickListener {
//            val isFavorite = deleteViewModel.pokemonExists(idContent.text.toString().toInt())
//            Log.d("IsFav", isFavorite.toString())
//            if (isFavorite){
//                deleteViewModel.deletePokemon(Pokemon(idContent.text.toString(), pokemonDisplayName.text.toString(), weightContent.text.toString(), heightContent.text.toString(), expContent.text.toString()))
//                val unMarkFavorite = R.drawable.ic_baseline_star_border_24
//                Glide.with(it.context)
//                    .load(unMarkFavorite)
//                    .into(favoriteIcon)
//            } else {
//                
//            }
            saveViewModel.insertPokemon(Pokemon(idContent.text.toString(), pokemonDisplayName.text.toString(), weightContent.text.toString(), heightContent.text.toString(), expContent.text.toString()))
            val markFavorite = R.drawable.ic_baseline_star_fill
            Glide.with(it.context)
                .load(markFavorite)
                .into(favoriteIcon)

        }

    }

}