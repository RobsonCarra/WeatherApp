package br.com.alura.ceep.ui.kotlinstudy.domain.commands

import br.com.alura.ceep.ui.kotlinstudy.ForecastRequest
import br.com.alura.ceep.ui.kotlinstudy.domain.mappers.ForecastDataMapper
import br.com.alura.ceep.ui.kotlinstudy.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: String) :
    Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(
            forecastRequest.execute()
        )
    }
}