package com.example.test.ui.theme.home

import kotlin.random.Random

public fun makeCatText(level: Int, catType: String): String {
    if (level == 1 && catType == "sleep") {
        val text = if (Random.nextBoolean()) "よくねたにゃぁ" else "・・・。"
        return text
    } else if (level == 2 && catType == "sleep") {
        val text = if (Random.nextBoolean()) "お前働きすぎにゃ" else "zzz..."
        return text
    }

    if (level == 1 && catType == "work") {
        val text = if (Random.nextBoolean()) "にゃんご！" else "おつかれにゃあ！"
        return text
    } else if (level == 2 && catType == "work") {
        val text = if (Random.nextBoolean()) "寝すぎじゃないかえ" else "もっと働きたもれ"
        return text
    }
    return "にゃんご！"
}
