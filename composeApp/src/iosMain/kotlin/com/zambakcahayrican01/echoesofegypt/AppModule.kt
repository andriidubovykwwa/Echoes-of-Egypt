package com.zambakcahayrican01.echoesofegypt

import com.zambakcahayrican01.echoesofegypt.data.repository.DefaultGameRepository
import com.zambakcahayrican01.echoesofegypt.data.repository.GameRepository
import com.zambakcahayrican01.echoesofegypt.screen.achievements.AchievementsViewModel
import com.zambakcahayrican01.echoesofegypt.screen.content.ContentViewModel
import com.zambakcahayrican01.echoesofegypt.screen.game.GameViewModel
import com.zambakcahayrican01.echoesofegypt.screen.loading.LoadingViewModel
import com.zambakcahayrican01.echoesofegypt.screen.menu.MenuViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<GameRepository> { DefaultGameRepository() }
    viewModel { GameViewModel(get()) }
    viewModel { MenuViewModel(get()) }
    viewModel { ContentViewModel(get()) }
    viewModel { LoadingViewModel(get()) }
    viewModel { AchievementsViewModel(get()) }
}
