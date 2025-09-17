import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.putragandad.bookpediacmp.App
import com.putragandad.bookpediacmp.di.initKoin
import io.ktor.client.engine.darwin.Darwin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}
