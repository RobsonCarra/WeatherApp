package br.com.alura.ceep.ui.kotlinstudy

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.ceep.ui.kotlinstudy.domain.model.Forecast
import br.com.alura.ceep.ui.kotlinstudy.domain.model.ForecastList

class ViewHolder(view: View, private val itemClick: OnItemClickListener) :
    RecyclerView.ViewHolder(view) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return ViewHolder(TextView(parent.getContext()))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(weekForecast[position]) {
            holder.textView.text = "$date - $description - $high/$low"
        }

        override fun getItemCount(): Int = weekForecast.size
        class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    }

    interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }
}


