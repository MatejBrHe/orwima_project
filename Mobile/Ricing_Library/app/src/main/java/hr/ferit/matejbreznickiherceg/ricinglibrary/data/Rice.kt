package hr.ferit.matejbreznickiherceg.ricinglibrary.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.ImageBitmap

data class Rice(
    var internalId: Int,
    var externalId: String,
    var title: String,
    var creator: User,
    var githubRepository: String,
    var image: ImageBitmap,
)
