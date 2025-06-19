// ===================== OnboardingViewModel.kt =====================
package com.molks.ecommerce.presentation.screens.on_boarding_screen.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OnboardingViewModel : ViewModel() {
    private val _stage = MutableStateFlow(0) // 0: branding, 1: onboarding
    val stage: StateFlow<Int> = _stage

    private val _currentPosition = MutableStateFlow(0)
    val currentPosition: StateFlow<Int> = _currentPosition

    private val _animate = MutableStateFlow(true)
    val animate: StateFlow<Boolean> = _animate

    fun moveToOnboarding() {
        _stage.value = 1
    }

    fun goToNextSlide(totalSlides: Int, onDone: () -> Unit) {
        if (_currentPosition.value < totalSlides - 1) {
            _currentPosition.value++
            _animate.value = !_animate.value
        } else {
            onDone()
        }
    }
    val currentPage = mutableStateOf(0)

}
