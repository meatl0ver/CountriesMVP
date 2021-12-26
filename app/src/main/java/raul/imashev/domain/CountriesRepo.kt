package raul.imashev.domain

import android.util.Log
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import raul.imashev.model.Country
import raul.imashev.model.Flags
import raul.imashev.model.Name
import raul.imashev.remote.IDataSource
import raul.imashev.room.database.Database
import raul.imashev.room.model.RoomCountry
import raul.imashev.utils.networkStatus.INetworkStatus
import raul.imashev.utils.textUtils.ITextUtil


class CountriesRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: Database,
    private val textUtil: ITextUtil
) : ICountriesRepo {

    override fun getCountries(): Single<List<Country>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getCountries()
                    .flatMap { countries ->
                        Single.fromCallable {
                            val roomCountries = countries.map { country ->
                                RoomCountry(
                                    id = null,
                                    area = country.area ?: 0.0,
                                    capital = country.capital?.let { textUtil.getItems(it) } ?: "",
                                    continents = country.continents?.let { textUtil.getItems(it) }
                                        ?: "",
                                    population = country.population ?: 0,
                                    region = country.region ?: "",
                                    fifa = country.fifa ?: "",
                                    flag = country.flags?.png ?: "",
                                    commonName = country.name?.common ?: "",
                                    officialName = country.name?.official ?: ""
                                )
                            }
                            db.countryDao.insert(roomCountries)
                            countries
                        }
                    }
            } else {
                Single.fromCallable {
                    val countries = db.countryDao.getAll().map { roomCountry ->
                        Country(
                            Name(roomCountry.commonName, roomCountry.officialName),
                            Flags(roomCountry.flag),
                            roomCountry.area,
                            roomCountry.capital.split(","),
                            roomCountry.continents.split(","),
                            roomCountry.population,
                            roomCountry.region,
                            roomCountry.fifa
                        )
                    }
                    Log.d("ssssss", countries.first().toString())
                    countries
                }
            }
        }.subscribeOn(Schedulers.io())
}

