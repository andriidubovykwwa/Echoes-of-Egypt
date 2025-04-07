package com.devname.echoesofegypt

import com.devname.echoesofegypt.data.repository.DefaultGameRepository
import com.devname.echoesofegypt.data.repository.GameRepository
import com.devname.echoesofegypt.screen.achievements.AchievementsViewModel
import com.devname.echoesofegypt.screen.game.GameViewModel
import com.devname.echoesofegypt.screen.menu.MenuViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<GameRepository> { DefaultGameRepository() }
    viewModel { GameViewModel(get()) }
    viewModel { MenuViewModel(get()) }
    viewModel { AchievementsViewModel(get()) }
}
