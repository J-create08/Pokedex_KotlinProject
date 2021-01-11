package com.project.pokedex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.pokedex.R
import com.bumptech.glide.Glide
import com.project.pokedex.network.models.PokemonDetailsResponse
import com.project.pokedex.network.models.PokemonInfo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.pokemon_item_view_holder.view.*
import java.util.*

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {
    private val clicksAcceptor: PublishSubject<PokemonInfo> = PublishSubject.create()
    val itemClicked: Observable<PokemonInfo> = clicksAcceptor.hide()

    var pokemonList: List<PokemonInfo> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(pokemon: PokemonInfo) {
            itemView.pokemonName.text = pokemon.name.capitalize()
            val pokeUrl = pokemon.url
            val pokemonID = pokeUrl.replace("https://pokeapi.co/api/v2/pokemon/", "").replace("/", "")
            itemView.textViewPokemonID.text = "ID: ${pokemonID}"
            val baseUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/132.png"
            val formattedUrl = baseUrl.replace("132", pokemonID)
            Glide.with(itemView.context)
                .load(formattedUrl)
                .circleCrop().into(itemView.PokemonThumbnail)

            itemView.setOnClickListener {
                clicksAcceptor.onNext(pokemon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item_view_holder, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount() = pokemonList.size
}