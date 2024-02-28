package com.example.an16.mynotesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.an16.mynotesapp.R
import com.example.an16.mynotesapp.databinding.FragmentSplashscreenBinding
import com.example.an16.mynotesapp.repository.SharedPreferencesRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    private var binding: FragmentSplashscreenBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashscreenBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when {
            sharedPreferencesRepository.isFirstLaunch() -> {
                sharedPreferencesRepository.setIsFirstLaunch()
                lifecycleScope.launch(Dispatchers.IO) {
                    delay(2000)
                    binding?.root?.post {
                        findNavController().navigate(R.id.action_splashScreenFragment_to_firstPageFragment)
                    }
                }
            }

            sharedPreferencesRepository.getUserEmail() == null -> {
                lifecycleScope.launch(Dispatchers.IO) {
                    delay(2000)
                    binding?.root?.post {
                        findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
                    }
                }
            }

            else -> {

                lifecycleScope.launch(Dispatchers.IO) {
                    delay(2000)
                    binding?.root?.post {
                        findNavController().navigate(R.id.action_splashScreenFragment_to_mainFragment)
                    }
                }
            }
        }
    }
}