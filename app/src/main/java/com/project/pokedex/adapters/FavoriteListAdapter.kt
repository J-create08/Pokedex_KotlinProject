package com.project.pokedex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.pokedex.R
import com.project.pokedex.db.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_detail.view.*
import kotlinx.android.synthetic.main.pokemon_item_view_holder.view.*

class FavoriteListAdapter : RecyclerView.Adapter<FavoriteListAdapter.PokemonViewHolder>() {

    var favoriteList: List<Pokemon> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(pokemon: Pokemon) {
            itemView.pokemonName.text = pokemon.name.capitalize()
            itemView.textViewPokemonID.text = pokemon.id
//            val markFavorite = R.drawable.ic_baseline_star_fill
//            Glide.with(itemView.context)
//                .load(markFavorite)
//                .into(itemView.favoriteIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListAdapter.PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item_view_holder, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteListAdapter.PokemonViewHolder, position: Int) {
        holder.bind(favoriteList[position])
    }

    override fun getItemCount() = favoriteList.size


}