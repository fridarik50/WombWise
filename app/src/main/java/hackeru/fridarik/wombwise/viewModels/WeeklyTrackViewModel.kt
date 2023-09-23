package hackeru.fridarik.wombwise.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hackeru.fridarik.wombwise.models.WeeklyPregnancyDetails
import hackeru.fridarik.wombwise.services.PregnancyDetailsService
import kotlinx.coroutines.launch
import retrofit2.HttpException

class WeeklyTrackViewModel :ViewModel() {

    private val _weeklyTrackLiveData = MutableLiveData<List<WeeklyPregnancyDetails>>()
    val weeklyTrackLiveData : LiveData<List<WeeklyPregnancyDetails>> = _weeklyTrackLiveData

    private val TAG = "Weekly_Track_View_Model"


    init {
        fetchDetails()
    }
    fun fetchDetails() {
            val service = PregnancyDetailsService.get()
            viewModelScope.launch {
                try {
                    val response = service.getPregnancyDetails()
                    _weeklyTrackLiveData.postValue(response.weeklyDetails)
                    Log.i(TAG, "$response")
                } catch (e: HttpException) {
                    Log.e(TAG, "HTTP exception", e)
                } catch(e :Exception) {
                    e.printStackTrace()
                }
            }

    }
}