package com.openxcell.di.builder.app

import android.app.Application
import android.content.pm.PackageManager
import android.os.Build
import com.openxcell.data.datasource.AuthDataSource
import com.openxcell.data.repository.AuthRepository
import com.openxcell.di.builder.ViewModelModule
import com.openxcell.rx.RxBus
import com.openxcell.utills.zzzz
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import java.security.MessageDigest
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetModule::class, DataBaseModule::class, ViewModelModule::class])
class AppModule {


    @Provides
    @Named("db_name")
    fun provideDataBaseName(): String = "my_app_db"


    @Provides
    @Singleton
    fun provideAuthRepository(authDataSource: AuthDataSource): AuthRepository = authDataSource


    @Provides
    @Singleton
    fun provideRxBus() = RxBus()


    @Provides
    @Singleton
    @Named("EncKey")
    fun provideEncKey(application: Application): String {
        val signatureList: List<String>
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                // New signature
                val sig = application.packageManager.getPackageInfo(application.packageName, PackageManager.GET_SIGNING_CERTIFICATES).signingInfo
                signatureList = if (sig.hasMultipleSigners()) {
                    // Send all with apkContentsSigners
                    sig.apkContentsSigners.map {
                        val digest = MessageDigest.getInstance("SHA")
                        digest.update(it.toByteArray())
                        bytesToHex(digest.digest())
                    }
                } else {
                    // Send one with signingCertificateHistory
                    sig.signingCertificateHistory.map {
                        val digest = MessageDigest.getInstance("SHA")
                        digest.update(it.toByteArray())
                        bytesToHex(digest.digest())
                    }
                }
            } else {
                val sig = application.packageManager.getPackageInfo(application.packageName, PackageManager.GET_SIGNATURES).signatures
                signatureList = sig.map {
                    val digest = MessageDigest.getInstance("SHA")
                    digest.update(it.toByteArray())
                    bytesToHex(digest.digest())
                }
            }

            return signatureList.get(0)
        } catch (e: Exception) {
            // Handle error
        }
        return ""
    }



    fun bytesToHex(bytes: ByteArray): String {
        val hexArray = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
        val hexChars = CharArray(bytes.size * 2)
        var v: Int
        for (j in bytes.indices) {
            v = bytes[j].toInt() and 0xFF
            hexChars[j * 2] = hexArray[v.ushr(4)]
            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
        }
        return String(hexChars).zzzz()
    }


}