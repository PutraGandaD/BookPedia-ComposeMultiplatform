import androidx.compose.ui.window.ComposeUIViewController
import com.putragandad.bookpediacmp.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
