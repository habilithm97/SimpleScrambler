package com.example.cubescrambler2404

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Repository 클래스를 통해 DB와 상호작용하고, UI에 필요한 데이터를 제공하는 ViewModel 클래스
class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val myRepo: MyRepository
    val getAll: LiveData<List<Scramble>>

    init {
        /**
         * application context 사용
        -메모리 릭 방지(애플리케이션의 수명과 일치하기 때문에)
        -DB가 애플리케이션의 전역 context에서 안전하게 접근함
         */
        val roomDao = RoomDB.getInstance(application).roomDao()
        myRepo = MyRepository(roomDao)
        getAll = myRepo.getAll.asLiveData()
    }

    fun addScramble(scramble: Scramble) {
        /**
         * viewModelScope : ViewModel의 생명주기에 맞춰 자동으로 취소되는 코루틴 스코프
        -> ViewModel이 파괴되면 코루틴이 자동으로 취소되어 메모리 릭을 방지함
         */
        viewModelScope.launch(Dispatchers.IO) {
            myRepo.addScramble(scramble)
        }
    }

    fun deleteScramble(scramble: Scramble) {
        viewModelScope.launch(Dispatchers.IO) {
            myRepo.deleteScramble(scramble)
        }
    }
}