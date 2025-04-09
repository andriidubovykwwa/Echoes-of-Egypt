package com.zambakcahayrican01.echoesofegypt.utils

import echoesofegypt.composeapp.generated.resources.Res
import kotlinx.cinterop.ExperimentalForeignApi
import org.jetbrains.compose.resources.ExperimentalResourceApi
import platform.AVFAudio.AVAudioPlayer
import platform.Foundation.NSURL

@OptIn(ExperimentalResourceApi::class, ExperimentalForeignApi::class)
object SoundManager {
    private var musicPlayer = createPlayer("files/music.mp3", numberOfLoops = -1)
    private var attackPlayer = createPlayer("files/attack.mp3")
    private var stepPlayer = createPlayer("files/step.mp3")
    private var losePlayer = createPlayer("files/lose.mp3")
    private var winPlayer = createPlayer("files/win.mp3")
    private var potionDrinkPlayer = createPlayer("files/potion_drink.mp3")
    private var itemPickupPlayer = createPlayer("files/item_pickup.mp3")
    private var mummyDeathPlayer = createPlayer("files/mummy_death.mp3")
    private const val DEFAULT_VOLUME = 5

    private fun createPlayer(path: String, numberOfLoops: Int = 0): AVAudioPlayer? {
        try {
            val playerUrl = NSURL.URLWithString(Res.getUri(path))
            if (playerUrl != null) {
                val player = AVAudioPlayer(playerUrl, error = null)
                player.let {
                    it.numberOfLoops = numberOfLoops.toLong()
                    it.volume = DEFAULT_VOLUME * 0.1f
                    it.prepareToPlay()
                }
                return player
            }
            return null
        } catch (_: Exception) {
            return null
        }
    }

    fun playMusic(volume: Int) {
        if (volume == 0) {
            musicPlayer?.stop()
            return
        }
        musicPlayer?.let { it.volume = 0.1f * volume }
        musicPlayer?.play()
    }

    fun playAttack(volume: Int) {
        if (volume == 0) {
            attackPlayer?.stop()
            return
        }
        attackPlayer?.let { it.volume = 0.1f * volume }
        attackPlayer?.play()
    }

    fun playStep(volume: Int) {
        if (volume == 0) {
            stepPlayer?.stop()
            return
        }
        stepPlayer?.let { it.volume = 0.1f * volume }
        stepPlayer?.play()
    }

    fun playLose(volume: Int) {
        if (volume == 0) {
            losePlayer?.stop()
            return
        }
        losePlayer?.let { it.volume = 0.1f * volume }
        losePlayer?.play()
    }

    fun playWin(volume: Int) {
        if (volume == 0) {
            winPlayer?.stop()
            return
        }
        winPlayer?.let { it.volume = 0.1f * volume }
        winPlayer?.play()
    }

    fun playItemPickup(volume: Int) {
        if (volume == 0) {
            itemPickupPlayer?.stop()
            return
        }
        itemPickupPlayer?.let { it.volume = 0.1f * volume }
        itemPickupPlayer?.play()
    }

    fun playPotionDrink(volume: Int) {
        if (volume == 0) {
            potionDrinkPlayer?.stop()
            return
        }
        potionDrinkPlayer?.let { it.volume = 0.1f * volume }
        potionDrinkPlayer?.play()
    }

    fun playMummyDeath(volume: Int) {
        if (volume == 0) {
            mummyDeathPlayer?.stop()
            return
        }
        mummyDeathPlayer?.let { it.volume = 0.1f * volume }
        mummyDeathPlayer?.play()
    }

}