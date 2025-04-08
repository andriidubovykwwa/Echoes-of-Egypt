package com.devname.echoesofegypt.screen.achievements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devname.echoesofegypt.data.game_params.Achievement
import com.devname.echoesofegypt.data.game_params.AchievementType
import com.devname.echoesofegypt.ui_components.AchievementComponent
import com.devname.echoesofegypt.ui_components.MenuButton
import com.devname.echoesofegypt.utils.UiConfig
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.achievements
import echoesofegypt.composeapp.generated.resources.back
import echoesofegypt.composeapp.generated.resources.back_button
import echoesofegypt.composeapp.generated.resources.bg2
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AchievementsScreen(
    navController: NavController,
    viewModel: AchievementsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    Box(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.bg2),
            contentScale = ContentScale.FillBounds
        )
    ) {
        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = WindowInsets.safeContent.asPaddingValues(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item {
                Box(Modifier.fillMaxWidth()) {
                    MenuButton(
                        Modifier.size(UiConfig.smallMenuButtonSize).align(Alignment.CenterStart),
                        description = stringResource(Res.string.back),
                        painter = painterResource(Res.drawable.back_button),
                        onClick = { navController.popBackStack() }
                    )
                    Image(
                        modifier = Modifier.align(Alignment.Center)
                            .height(UiConfig.headerTextHeight),
                        painter = painterResource(Res.drawable.achievements),
                        contentDescription = stringResource(Res.string.achievements),
                        contentScale = ContentScale.FillHeight
                    )
                }
            }
            val achievements = Achievement.entries
            val achievementsToValues = achievements.associateWith {
                when (it.type) {
                    AchievementType.MUMMY_KILLS -> state.mummyKills
                    AchievementType.POTION_DRINKS -> state.potionDrinks
                    AchievementType.DAMAGE_TAKEN -> state.damageTaken
                    AchievementType.DAMAGE_DEALT -> state.damageDealt
                    AchievementType.POTION_PICKUPS -> state.potionPickups
                    AchievementType.TREASURE_PICKUPS -> state.treasurePickups
                    AchievementType.COMPLETED_LVLS -> state.completedLvls
                    AchievementType.COMPLETED_LVLS_WITHOUT_KILLS -> state.completedLvlsWithoutKills
                    AchievementType.MAX_COMPLETED_LVL -> state.maxCompletedLvl
                }
            }
            // Completed
            items(achievementsToValues.filter { it.value >= it.key.target }
                .toList()) { (achievement, currentValue) ->
                AchievementComponent(
                    Modifier.fillMaxWidth(),
                    achievement = achievement,
                    currentValue = currentValue
                )
            }
            // Uncompleted
            items(achievementsToValues.filter { it.value < it.key.target }
                .toList()) { (achievement, currentValue) ->
                AchievementComponent(
                    Modifier.fillMaxWidth(),
                    achievement = achievement,
                    currentValue = currentValue
                )
            }
        }
    }
}
