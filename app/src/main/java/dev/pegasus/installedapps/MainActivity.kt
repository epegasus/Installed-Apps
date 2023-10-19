package dev.pegasus.installedapps

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.pegasus.installedapps.adapters.AdapterApps
import dev.pegasus.installedapps.dataClasses.AppItem
import dev.pegasus.installedapps.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { AdapterApps() }

    private val arrayList = ArrayList<AppItem>()
    private val list: List<AppItem> get() = arrayList.toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
        fetchInstalledApps()
    }

    private fun initRecyclerView() {
        binding.rvList.adapter = adapter
    }

    private fun fetchInstalledApps() {
        CoroutineScope(Dispatchers.IO).launch {
            val installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            installedApps.forEachIndexed { index, appInfo ->
                if (appInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) {
                    // This is a user-installed app
                    val appName = appInfo.loadLabel(packageManager).toString()
                    val packageName = appInfo.packageName
                    val appIcon = appInfo.loadIcon(packageManager)

                    // Print the app name and package name
                    println("User-Installed App Name: $appName, Package Name: $packageName")
                    arrayList.add(AppItem(id = index, icon = appIcon, appName = appName, packageName = packageName))
                }
            }
            withContext(Dispatchers.Main) {
                adapter.submitList(list)
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}