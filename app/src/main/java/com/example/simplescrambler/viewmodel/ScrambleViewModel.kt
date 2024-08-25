package com.example.simplescrambler.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.simplescrambler.repository.ScrambleRepository
import com.example.simplescrambler.room.Scramble
import com.example.simplescrambler.room.ScrambleDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Repository를 통해 DB와 상호작용을 하고, UI에 필요한 데이터를 제공
class ScrambleViewModel(application: Application) : AndroidViewModel(application) {
    private val scrambleRepository: ScrambleRepository
    val getAll: LiveData<List<Scramble>>

    init {
        // application context 사용
        // -> 전역적으로 필요한 DB에 안전하게 접근(앱 전체에서 동일한 DB 인스턴스 사용)
        // -> 메모리 릭 방지(앱의 생명주기와 일치하기 때문에)
        val scrambleDao = ScrambleDB.getInstance(application).scrambleDao()
        scrambleRepository = ScrambleRepository(scrambleDao)
        // UI 업데이트와 생명주기 관리를 쉽게 하기 위해 LiveData로 변환 -> UI와 데이터를 더 쉽게 연결
        getAll = scrambleRepository.getAll.asLiveData()
    }

    fun addScramble(scramble: Scramble) {
        viewModelScope.launch(Dispatchers.IO) {
            scrambleRepository.addScramble(scramble)
        }
    }

    fun deleteScramble(scramble: Scramble) {
        viewModelScope.launch(Dispatchers.IO) {
            scrambleRepository.deleteScramble(scramble)
        }
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        scrambleRepository.deleteAll()
    }
}