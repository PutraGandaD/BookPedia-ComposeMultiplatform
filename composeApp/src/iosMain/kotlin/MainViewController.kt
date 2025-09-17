import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.putragandad.bookpediacmp.App
import io.ktor.client.engine.darwin.Darwin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController {
    App(
        engine = remember {
            Darwin.create()
        }
    )
}
