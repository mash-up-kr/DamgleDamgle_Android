package com.mashup.damgledamgle.presentation.feature.mypage.model

/**
 *  MyPageTab.kt
 *
 *  Created by Minji Jeong on 2022/08/20
 *  Copyright © 2022 MashUp All rights reserved.
 */

enum class MyPageTab(
    val index: Int,
    val title: String
) {
    MyDamgle(0, "내 담글"), Setting(1, "설정")
}
