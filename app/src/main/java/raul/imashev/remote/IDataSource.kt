package raul.imashev.remote

import raul.imashev.model.Country
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET


interface IDataSource {
    @GET("/v3.1/all")
    fun getCountries(): Single<List<Country>>
}