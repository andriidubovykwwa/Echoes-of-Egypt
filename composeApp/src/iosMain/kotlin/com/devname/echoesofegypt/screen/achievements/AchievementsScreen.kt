package com.devname.echoesofegypt.screen.achievements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devname.echoesofegypt.data.game_params.Achievement
import com.devname.echoesofegypt.data.game_params.AchievementType
import com.devname.echoesofegypt.screen.Screen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AchievementsScreen(
    navController: NavController,
    viewModel: AchievementsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = WindowInsets.safeContent.asPaddingValues(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item {
                Button(
                    modifier = Modifier.align(Alignment.TopStart),
                    onClick = { navController.popBackStack() }) {
                    Text(text = "Back")
                }
            }
            items(Achievement.entries) { achievement ->
                val currentValue = when (achievement.type) {
                    AchievementType.MUMMY_KILLS -> state.mummyKills
                    AchievementType.POTION_DRINKS -> state.potionDrinks
                    AchievementType.DAMAGE_TAKEN -> state.damageTaken
                    AchievementType.DAMAGE_DEALT -> state.damageDealt
                    AchievementType.POTION_PICKUPS -> state.potionDrinks
                    AchievementType.TREASURE_PICKUPS -> state.treasurePickups
                    AchievementType.COMPLETED_LVLS -> state.completedLvls
                    AchievementType.COMPLETED_LVLS_WITHOUT_KILLS -> state.completedLvlsWithoutKills
                    AchievementType.MAX_COMPLETED_LVL -> state.maxCompletedLvl
                }
                val displayedValue =
                    if (currentValue < achievement.target) currentValue else achievement.target
                Text(text = achievement.type.getMessage(achievement.target) + "($displayedValue/${achievement.target})")
            }
        }
    }
}
