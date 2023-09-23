package hackeru.fridarik.wombwise.services

import hackeru.fridarik.wombwise.models.BabyGrowthDetailsResponse
import hackeru.fridarik.wombwise.models.WeeklyPregnancyDetailsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PregnancyDetailsService {

    @GET("WombWiseAPI.json")
    suspend fun getPregnancyDetails(): WeeklyPregnancyDetailsResponse
    @GET("WombWiseAPI.json")
    suspend fun getBabyDetails(): BabyGrowthDetailsResponse

    companion object {
        private var service : PregnancyDetailsService? = null

        fun get() : PregnancyDetailsService {
            if(service == null) {
                service = Retrofit.Builder()
                    .baseUrl("https://gist.githubusercontent.com/fridarik50/a7f679314b945218ef46a4edfa68472b/raw/c1a7303af59e34aa9e40445100377188de624833/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PregnancyDetailsService::class.java)
            }
            return service!!
        }
    }
}