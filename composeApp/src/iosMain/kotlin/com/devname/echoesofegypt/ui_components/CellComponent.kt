package com.devname.echoesofegypt.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.devname.echoesofegypt.data.game_params.Cell
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.empty_cell
import echoesofegypt.composeapp.generated.resources.hero
import echoesofegypt.composeapp.generated.resources.mummy
import echoesofegypt.composeapp.generated.resources.potion
import echoesofegypt.composeapp.generated.resources.stairs
import echoesofegypt.composeapp.generated.resources.treasure
import echoesofegypt.composeapp.generated.resources.wall
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CellComponent(
    modifier: Modifier = Modifier,
    cell: Cell
) {
    Box(
        modifier.paint(
            painter = painterResource(if (cell == Cell.Wall) Res.drawable.wall else Res.drawable.empty_cell),
            contentScale = ContentScale.FillBounds
        ),
        contentAlignment = Alignment.Center
    ) {
        when (cell) {
            Cell.Exit -> Image(
                modifier = Modifier.fillMaxWidth(0.6f),
                painter = painterResource(Res.drawable.stairs),
                contentDescription = stringResource(Res.string.stairs),
                contentScale = ContentScale.FillWidth
            )

            is Cell.HeroOccupied -> Box(Modifier.fillMaxSize(0.76f)) {
                Image(
                    modifier = Modifier.fillMaxHeight().align(Alignment.BottomCenter),
                    painter = painterResource(Res.drawable.hero),
                    contentDescription = stringResource(Res.string.hero),
                    contentScale = ContentScale.FillHeight
                )
            }

            is Cell.MummyOccupied -> {
                val mummy = (cell as Cell.MummyOccupied).mummy
                Box(Modifier.fillMaxSize(0.76f)) {
                    Image(
                        modifier = Modifier.fillMaxHeight().align(Alignment.BottomCenter),
                        painter = painterResource(Res.drawable.mummy),
                        contentDescription = stringResource(Res.string.mummy),
                        contentScale = ContentScale.FillHeight
                    )
                }
                Box(
                    Modifier.align(Alignment.TopCenter).fillMaxWidth().fillMaxHeight(0.14f)
                        .background(Color(0xffFFC7C8), RoundedCornerShape(50))
                ) {
                    Box(
                        Modifier.fillMaxWidth(mummy.health / mummy.maxHealth.toFloat())
                            .fillMaxHeight()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xffFF0000), Color(0xff4A0000)),
                                    start = Offset(0f, 0f),
                                    end = Offset(0f, Float.POSITIVE_INFINITY)
                                ), RoundedCornerShape(50)
                            )
                    )
                }
            }

            Cell.Potion -> Image(
                modifier = Modifier.fillMaxHeight(0.6f),
                painter = painterResource(Res.drawable.potion),
                contentDescription = stringResource(Res.string.potion),
                contentScale = ContentScale.FillHeight
            )

            Cell.Treasure -> Image(
                modifier = Modifier.fillMaxWidth(0.6f),
                painter = painterResource(Res.drawable.treasure),
                contentDescription = stringResource(Res.string.treasure),
                contentScale = ContentScale.FillWidth
            )

            else -> {}
        }
    }
}