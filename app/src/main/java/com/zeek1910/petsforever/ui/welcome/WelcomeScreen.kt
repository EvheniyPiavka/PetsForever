package com.zeek1910.petsforever.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zeek1910.petsforever.R
import com.zeek1910.petsforever.ui.common.BackButton
import com.zeek1910.petsforever.ui.common.PrimaryButton
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    items: List<WelcomeItem> = listOf(
        WelcomeItem(
            title = R.string.welcome_title_1,
            description = R.string.welcome_description_1,
            image = R.drawable.welcome_1
        ),
        WelcomeItem(
            title = R.string.welcome_title_2,
            description = R.string.welcome_description_2,
            image = R.drawable.welcome_2
        ),
        WelcomeItem(
            title = R.string.welcome_title_3,
            description = R.string.welcome_description_3,
            image = R.drawable.welcome_3
        ),
        WelcomeItem(
            title = R.string.welcome_title_4,
            description = R.string.welcome_description_4,
            image = R.drawable.welcome_4
        )
    ),
    onGetStartedClick: () -> Unit = {},
) {
    val pagerState = rememberPagerState(pageCount = { items.size })
    val scrollScope = rememberCoroutineScope()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            modifier = Modifier.size(120.dp, 70.dp),
            painter = painterResource(R.drawable.logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(40.dp))
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            WelcomePageContent(items[it])
        }
        ButtonsContainer(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(20.dp),
            pagerState = when (pagerState.currentPage) {
                0 -> PagerState.START
                items.lastIndex -> PagerState.END
                else -> PagerState.MIDDLE
            },
            onBackClick = {
                if (pagerState.currentPage > 0) {
                    scrollScope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                }
            },
            onNextClick = {
                if (pagerState.currentPage < items.lastIndex) {
                    scrollScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                } else {
                    onGetStartedClick.invoke()
                }
            }
        )
    }
}

@Composable
fun WelcomePageContent(item: WelcomeItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            text = stringResource(item.title)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .alpha(0.5f),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(item.description)
        )
        Image(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            painter = painterResource(item.image),
            contentDescription = null
        )
    }
}

@Composable
fun ButtonsContainer(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Row(modifier = modifier) {
        when (pagerState) {
            PagerState.START -> {
                Spacer(modifier = Modifier.weight(1f))
                PrimaryButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f),
                    onClick = onNextClick,
                    text = stringResource(R.string.next)
                )
            }

            PagerState.MIDDLE -> {
                BackButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f),
                    onClick = onBackClick
                )
                PrimaryButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f),
                    onClick = onNextClick,
                    text = stringResource(R.string.next)
                )
            }

            PagerState.END -> {
                PrimaryButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f),
                    onClick = onNextClick,
                    text = stringResource(R.string.get_started)
                )
            }
        }
    }
}

enum class PagerState {
    START,
    MIDDLE,
    END,
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}

@Preview(showBackground = true)
@Composable
fun ButtonsContainerPreview() {
    Column {
        ButtonsContainer(Modifier, PagerState.START, {}, {})
        ButtonsContainer(Modifier, PagerState.MIDDLE, {}, {})
        ButtonsContainer(Modifier, PagerState.END, {}, {})
    }
}