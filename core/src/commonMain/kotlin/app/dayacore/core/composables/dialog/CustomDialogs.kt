package app.dayacore.core.composables.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import app.dayacore.core.composables.CustomButton
import app.dayacore.core.composables.CustomButtonParams
import app.dayacore.core.composables.label.CustomText

@Composable
fun LoaderDialog() {
    BaseDialog {
        Surface(
            modifier = Modifier.size(128.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(all = 40.dp)
            )
        }
    }
}

@Composable
fun SimpleDialog(
    title: String,
    message: String,
    titleDismiss: String,
    onDismissed: () -> Unit = {}
) {
    val isDismissed = remember { mutableStateOf(false) }

    if (!isDismissed.value) {
        BaseDialog {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CustomText(
                    text = title,
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )

                CustomText(
                    text = message,
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    textAlign = TextAlign.Center
                )

                CustomButton(
                    customButtonParams = CustomButtonParams(
                        text = titleDismiss,
                        enabled = true,
                        colorEnable = Pair(
                            first = MaterialTheme.colorScheme.primary,
                            second = MaterialTheme.colorScheme.background
                        )
                    ),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .wrapContentWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    onDismissed.invoke()
                    isDismissed.value = true
                }
            }
        }
    }
}

@Composable
fun DialogTwoButton(
    title: @Composable () -> Unit,
    message: @Composable () -> Unit,
    titleButtonLeft: String? = null,
    titleButtonRight: String? = null,
    actionButtonLeft: (() -> Unit)? = null,
    actionButtonRight: (() -> Unit)? = null,
) {
    val isDismissed = remember { mutableStateOf(false) }

    if (!isDismissed.value) {
        BaseDialog {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // title
                title.invoke()
                // message
                message.invoke()
                // button
                Row(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    // button left
                    if (titleButtonLeft != null)
                        CustomText(
                            text = titleButtonLeft,
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentHeight()
                                .clip(shape = RoundedCornerShape(size = 8.dp))
                                .border(
                                    border = BorderStroke(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.primary
                                    ),
                                    shape = RoundedCornerShape(size = 8.dp)
                                )
                                .background(MaterialTheme.colorScheme.background)
                                .clickable {
                                    isDismissed.value = true
                                    actionButtonLeft?.invoke()
                                }
                                .padding(all = 10.dp),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )
                    // spacer
                    if (titleButtonLeft != null && titleButtonRight != null)
                        Spacer(modifier = Modifier.width(10.dp))
                    // button right
                    if (titleButtonRight != null)
                        CustomText(
                            text = titleButtonRight,
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentHeight()
                                .clip(shape = RoundedCornerShape(size = 8.dp))
                                .border(
                                    border = BorderStroke(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.primary
                                    ),
                                    shape = RoundedCornerShape(size = 8.dp)
                                )
                                .background(MaterialTheme.colorScheme.primary)
                                .clickable {
                                    isDismissed.value = true
                                    actionButtonRight?.invoke()
                                }
                                .padding(all = 10.dp),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = MaterialTheme.colorScheme.background,
                            textAlign = TextAlign.Center
                        )
                }
            }
        }
    }
}

@Composable
fun BaseDialog(
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = {}
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            content.invoke()
        }
    }
}