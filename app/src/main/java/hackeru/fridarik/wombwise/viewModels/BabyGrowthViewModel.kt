package hackeru.fridarik.wombwise.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hackeru.fridarik.wombwise.models.BabyGrowthDetails
import hackeru.fridarik.wombwise.services.PregnancyDetailsService
import kotlinx.coroutines.launch
import retrofit2.HttpException

class BabyGrowthViewModel: ViewModel(){

    private val _babyGrowthLiveData = MutableLiveData<List<BabyGrowthDetails>>()
    val babyGrowthLiveData : LiveData<List<BabyGrowthDetails>> = _babyGrowthLiveData

    private val TAG = "Baby_Growth_View_Model"

    init {
        fetchDetails()
    }

    fun fetchDetails() {
        val service = PregnancyDetailsService.get()
        viewModelScope.launch {
            try {
                val response = service.getBabyDetails()
                _babyGrowthLiveData.postValue(response.babyGrowthDetails)
                Log.i(TAG, "$response")
            } catch (e: HttpException) {
                Log.e(TAG, "HTTP exception", e)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}