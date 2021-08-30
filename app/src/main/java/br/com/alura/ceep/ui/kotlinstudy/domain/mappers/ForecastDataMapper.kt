package br.com.alura.ceep.ui.kotlinstudy.domain.mappers

import br.com.alura.ceep.ui.kotlinstudy.ResponseClasses
import br.com.alura.ceep.ui.kotlinstudy.domain.model.Forecast
import br.com.alura.ceep.ui.kotlinstudy.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import com.antonioleiva.weatherapp.domain.model.Forecast as ModelForecast

class ForecastDataMapper {
    fun convertFromDataModel(forecast: ResponseClasses.ForecastResult): ForecastList =
        ForecastList(
            forecast.city.name, forecast.city.country,
            convertForecastListToDomain(forecast.list)
        )

    private fun convertForecastListToDomain(list: List<Forecast>)
            : List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis +
                    TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(
            convertDate(forecast.dt),
            forecast.weather[0].description, forecast.temp.max.toInt(),
            forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon)
        )
    }

    private fun generateIconUrl(iconCode: String): String =
        "http://openweathermap.org/img/w/$iconCode.png"

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }
}