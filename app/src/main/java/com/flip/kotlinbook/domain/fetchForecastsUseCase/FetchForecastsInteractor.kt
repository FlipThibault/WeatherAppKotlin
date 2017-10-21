package com.flip.kotlinbook.domain.fetchForecastsUseCase

import com.flip.kotlinbook.data.models.ForecastModel

/**
 * Created by pthibau1 on 2017-10-21.
 */
interface FetchForecastsInteractor {

    interface Callback {
        fun onSuccess(forecasts : List<ForecastModel>)
        fun onFailure(error: Error)
    }

    fun fetchForecasts(callback: Callback)

}