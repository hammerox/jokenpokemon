package com.mcustodio.jokenpokemon.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mcustodio.jokenpokemon.R
import com.mcustodio.jokenpokemon.data.Score
import kotlinx.android.synthetic.main.item_ranking.view.*

class RankingRecyclerAdapter() : RecyclerView.Adapter<RankingRecyclerAdapter.RankingViewHolder>() {

    var scoreList: List<Score> = ArrayList()
        set (list) {
            field = list
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int = scoreList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ranking, parent, false)
        return RankingViewHolder(view)
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        val score = scoreList[position]
        holder.setValues(score)
    }


    inner class RankingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nicknameView = view.text_rankingitem_nickname
        private val scoreView = view.text_rankingitem_score

        fun setValues(score: Score) {
            nicknameView.text = score.nome
            scoreView.text = score.pontos.toString()
        }
    }

}