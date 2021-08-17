package com.jetpack.mvvm_rooom.di.koin_modules

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.jetpack.mvvm_rooom.repositories.MainActivityRepository
import com.jetpack.mvvm_rooom.repositories.room.PersonDAO
import com.jetpack.mvvm_rooom.repositories.room.PersonsDatabase
import com.jetpack.mvvm_rooom.viewmodel.MainActivityViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

//Module is the Component where we have to include the all dependency injections
//

val viewModelModule = module {
    viewModel {
        MainActivityViewModel(get())
    }
}

val mainActivityRepositoryModule = module {
    fun provideRepository(personDAO: PersonDAO): MainActivityRepository {
        return MainActivityRepository(personDAO)
    }
    single {
        provideRepository(get())
    }
}


val databaseModule = module {

    fun provideDatabase(application: Application): PersonsDatabase {
        return Room.databaseBuilder(application, PersonsDatabase::class.java, "selflearn.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun personDao(database: PersonsDatabase): PersonDAO {
        return database.personDao()
    }

    single { provideDatabase(androidApplication()) }
    single { personDao(get()) }
}