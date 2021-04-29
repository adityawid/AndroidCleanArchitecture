package com.adityawidayanto.moviesshow.di

import com.adityawidayanto.moviesshow.di.movie.MovieComponent
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AppModule::class,
//        MovieUseCaseModule::class,
//        RemoteDataSourceModule::class,
        RepositoryModule::class]
)
interface AppComponent {
    //    fun inject(moviesFragment: MoviesFragment)
    fun movieComponent(): MovieComponent.Factory

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun app(app: App): Builder
//        fun appModule(appModule: AppModule): Builder
//        fun build(): AppComponent
//    }
}

interface Injector {
    fun createMovieComponent(): MovieComponent

}