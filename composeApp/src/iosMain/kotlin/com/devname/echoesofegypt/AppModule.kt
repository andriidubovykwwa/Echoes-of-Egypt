package com.devname.echoesofegypt

import com.devname.echoesofegypt.screen.game.GameViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { GameViewModel() }
}
