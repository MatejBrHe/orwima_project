package hr.ferit.matejbreznickiherceg.ricinglibrary.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hr.ferit.matejbreznickiherceg.ricinglibrary.R
import hr.ferit.matejbreznickiherceg.ricinglibrary.data.Rice

@Composable
fun ListRiceScreen(viewModel: List<Rice>, navigation: NavController) {
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TitleBar(title = "My Rices")
        Row (
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            IconButton(iconResource = R.drawable.ic_arrow_back)
        }
        RiceList(rices = viewModel, navigation = navigation)
    }
}