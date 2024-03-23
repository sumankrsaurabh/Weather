package com.coderon.weather.screen.components
import com.coderon.weather.R

fun weatherIcon(int: Int): Int {
    return when (int) {
        1 -> R.drawable.icon1
        2 -> R.drawable.icon2
        3 -> R.drawable.icon3
        4 -> R.drawable.icon4
        5 -> R.drawable.icon4
        6 -> R.drawable.icon4
        7 -> R.drawable.icon7
//        8 -> R.drawable.icon8
//        9 -> R.drawable.icon9
//        10 -> R.drawable.icon10
//        11 -> R.drawable.icon11
//        12 -> R.drawable.icon12
        13 -> R.drawable.icon13
//        14 -> R.drawable.icon14
        15 -> R.drawable.icon15
        16 -> R.drawable.icon16
        17 -> R.drawable.icon17
        18 -> R.drawable.icon18
        19 -> R.drawable.icon19
//        20 -> R.drawable.icon20
//        21 -> R.drawable.icon21
        22 -> R.drawable.icon22
//        23 -> R.drawable.icon23
//        24 -> R.drawable.icon24
//        25 -> R.drawable.icon25
//        26 -> R.drawable.icon26
//        27 -> R.drawable.icon27
//        28 -> R.drawable.icon28
//        29 -> R.drawable.icon29
//        30 -> R.drawable.icon30
//        31 -> R.drawable.icon31
//        32 -> R.drawable.icon32
//        33 -> R.drawable.icon33
//        34 -> R.drawable.icon34
//        35 -> R.drawable.icon35
//        36 -> R.drawable.icon36
//        37 -> R.drawable.icon37
//        38 -> R.drawable.icon38
//        39 -> R.drawable.icon39

        else -> R.drawable.error
    }
}
