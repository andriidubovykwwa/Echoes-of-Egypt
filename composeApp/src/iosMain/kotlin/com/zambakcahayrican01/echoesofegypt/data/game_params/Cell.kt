package com.zambakcahayrican01.echoesofegypt.data.game_params

sealed interface Cell {
    data object Empty : Cell
    data object Wall : Cell
    data object Exit : Cell
    data object Treasure : Cell
    data object Potion : Cell
    data class MummyOccupied(val mummy: Mummy = Mummy()) : Cell
    data class HeroOccupied(val hero: Hero = Hero()) : Cell
}