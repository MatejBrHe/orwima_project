package hr.ferit.matejbreznickiherceg.ricinglibrary.data

import androidx.annotation.DrawableRes

data class Rice(
    var id: Int,
    var title: String,
    var creator: User,
    var githubRepository: String,
    @DrawableRes var images: List<Int>,
    var rating: Float,
    var comments: List<Comment>,
)
