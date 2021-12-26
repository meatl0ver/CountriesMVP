package raul.imashev.domain

import io.reactivex.rxjava3.core.Single
import raul.imashev.model.Country


interface ICountriesRepo {
    fun getCountries(): Single<List<Country>>
}