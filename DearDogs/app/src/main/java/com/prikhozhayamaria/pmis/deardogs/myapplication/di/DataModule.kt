package com.prikhozhayamaria.pmis.deardogs.myapplication.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton
import androidx.room.Room
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.DefaultDogsRepository
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.DogsRepository
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.data.LocalDataSource
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.data.RemoteDataSource
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.data.source.ApiRemoteDataSource
import com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.data.source.RoomLocalDataSource
import com.prikhozhayamaria.pmis.deardogs.myapplication.domain.AddDogUseCase

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideAddDogUseCase(
        repository: DogsRepository
    ): AddDogUseCase {
        return AddDogUseCase(repository)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDogsRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): DogsRepository {
        return DefaultDogsRepository(localDataSource, remoteDataSource, ioDispatcher)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        return ApiRemoteDataSource()
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        database: DogsDatabase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): LocalDataSource {
        return RoomLocalDataSource(database.dogsDao(), ioDispatcher)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): DogsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            DogsDatabase::class.java,
            "Dog.db"
        ).build()
    }
}
