package com.jetpack.mvvm_rooom.di.koin_modules

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.jetpack.mvvm_rooom.repositories.MainActivityRepository
import com.jetpack.mvvm_rooom.repositories.room.PersonDAO
import com.jetpack.mvvm_rooom.repositories.room.PersonTable
import com.jetpack.mvvm_rooom.repositories.room.PersonsDatabase
import com.jetpack.mvvm_rooom.view.adapters.PersonAdapter
import com.jetpack.mvvm_rooom.viewmodel.MainActivityViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

//Module is the Component where we have to include the all dependency injections
//
private  val TAG = "KoinModules==>"

val mainActivityRepositoryModule = module {
    Log.d(TAG, ":mainActivityRepositoryModule ")

    fun provideRepository(personDAO: PersonDAO): MainActivityRepository {
        return MainActivityRepository(personDAO)
    }

    single {
        provideRepository(get())
    }
    single {
        PersonAdapter()
    }
}
val databaseModule = module {
    Log.d(TAG, ":databaseModule ")
    fun provideDatabase(application: Application): PersonsDatabase {
        return Room.databaseBuilder(application, PersonsDatabase::class.java, "selflearn.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun personDao(database: PersonsDatabase): PersonDAO {
        Log.d(TAG, "personDao: ")
        return database.personDao()
    }


    single { provideDatabase(androidApplication()) }
    single { personDao(get()) }
}

val viewModelModule = module {
    Log.d(TAG, ":viewModelModule ")
    fun provideMainViewModel(repository: MainActivityRepository):MainActivityViewModel{
        return MainActivityViewModel(repository)
    }
    viewModel {
        provideMainViewModel(get())
    }
}