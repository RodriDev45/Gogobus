package com.example.gogobus.di

import android.content.Context
import com.example.gogobus.data.local.TokenDataStore
import com.example.gogobus.data.local.UserDataStore
import com.example.gogobus.data.remote.interceptors.AuthInterceptor
import com.example.gogobus.data.remote.GogobusApi
import com.example.gogobus.data.remote.api.BookingApi
import com.example.gogobus.data.remote.api.LocationApi
import com.example.gogobus.data.remote.api.MapsApi
import com.example.gogobus.data.remote.api.PaymentApi
import com.example.gogobus.data.remote.api.TripApi
import com.example.gogobus.data.remote.api.UserApi
import com.example.gogobus.data.repository.AuthRepositoryImpl
import com.example.gogobus.data.repository.BookingRepositoryImpl
import com.example.gogobus.data.repository.LocationRepositoryImpl
import com.example.gogobus.data.repository.MapsRepositoryImpl
import com.example.gogobus.data.repository.MyRepositoryImpl
import com.example.gogobus.data.repository.PaymentRepositoryImpl
import com.example.gogobus.data.repository.TripRepositoryImpl
import com.example.gogobus.domain.repository.AuthRepository
import com.example.gogobus.domain.repository.BaseRepository
import com.example.gogobus.domain.repository.BookingRepository
import com.example.gogobus.domain.repository.LocationRepository
import com.example.gogobus.domain.repository.MapsRepository
import com.example.gogobus.domain.repository.MyRepository
import com.example.gogobus.domain.repository.PaymentRepository
import com.example.gogobus.domain.repository.TripRepository
import com.example.gogobus.domain.session.SessionManager
import com.example.gogobus.domain.usecase.CreateBookingUseCase
import com.example.gogobus.domain.usecase.CreatePaymentUseCase
import com.example.gogobus.domain.usecase.GetBookingDetailUseCase
import com.example.gogobus.domain.usecase.GetTravelRouteUseCase
import com.example.gogobus.domain.usecase.GetTripDetailUseCase
import com.example.gogobus.domain.usecase.LoginUseCase
import com.example.gogobus.domain.usecase.LogoutUseCase
import com.example.gogobus.domain.usecase.RefreshTokenUseCase
import com.example.gogobus.domain.usecase.RegisterUseCase
import com.mercadopago.sdk.android.coremethods.domain.interactor.CoreMethods
import com.mercadopago.sdk.android.coremethods.domain.interactor.coreMethods
import com.mercadopago.sdk.android.initializer.MercadoPagoSDK
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY // ← muestra headers + cuerpo
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(tokenDataStore: TokenDataStore, logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenDataStore))
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    @Named("Gogobus")
    fun provideApi(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://gogobusbackend.onrender.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    @Named("GoogleMaps")
    fun provideGoogleMapsRetrofit(logging: HttpLoggingInterceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/") // base de Google
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(logging) // no uses AuthInterceptor aquí
                    .build()
            )
            .build()
    }


    // Aquí provees tus APIs
    @Provides @Singleton
    fun provideUserApi(@Named("Gogobus") retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideMapsApi(@Named("GoogleMaps")googleRetrofit: Retrofit): MapsApi =
        googleRetrofit.create(MapsApi::class.java)

    @Provides @Singleton
    fun provideLocationApi(@Named("Gogobus") retrofit: Retrofit): LocationApi =
        retrofit.create(LocationApi::class.java)

    @Provides @Singleton
    fun provideTripApi(@Named("Gogobus") retrofit: Retrofit): TripApi =
        retrofit.create(TripApi::class.java)

    @Provides @Singleton
    fun provideBookingApi(@Named("Gogobus") retrofit: Retrofit): BookingApi =
        retrofit.create(BookingApi::class.java)

    @Provides @Singleton
    fun providePaymentApi(@Named("Gogobus") retrofit: Retrofit): PaymentApi =
        retrofit.create(PaymentApi::class.java)

    @Provides
    fun provideCoreMethods(): CoreMethods {
        return MercadoPagoSDK.getInstance().coreMethods
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: UserApi, dataStore: TokenDataStore, userDataStore: UserDataStore): AuthRepository =
        AuthRepositoryImpl(api, dataStore, userDataStore)

    @Provides
    @Singleton
    fun provideLocationRepository(api: LocationApi): LocationRepository =
        LocationRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideTripRepository(api: TripApi): TripRepository =
        TripRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideBookingRepository(api: BookingApi): BookingRepository =
        BookingRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePaymentRepository(api: PaymentApi): PaymentRepository =
        PaymentRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideMapsRepository(api: MapsApi): MapsRepository =
        MapsRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGetTripDetailUseCase(
        repository: TripRepository
    ): GetTripDetailUseCase = GetTripDetailUseCase(repository)

    @Provides
    @Singleton
    fun provideGetTravelRouteUseCase(
        repository: MapsRepository
    ): GetTravelRouteUseCase = GetTravelRouteUseCase(repository)

    @Provides
    @Singleton
    fun provideTokenDataStore(@ApplicationContext context: Context): TokenDataStore = TokenDataStore(context)

    @Provides
    @Singleton
    fun provideLoginUseCase(
        repository: AuthRepository
    ): LoginUseCase = LoginUseCase(repository)

    @Provides
    @Singleton
    fun provideRegisterUseCase(
        repository: AuthRepository
    ): RegisterUseCase = RegisterUseCase(repository)


    @Provides
    @Singleton
    fun provideRefreshTokenUseCase(
        repository: AuthRepository
    ): RefreshTokenUseCase = RefreshTokenUseCase(repository)

    @Provides
    @Singleton
    fun provideLogoutUseCase(
        repository: AuthRepository
    ): LogoutUseCase = LogoutUseCase(repository)

    @Provides
    @Singleton
    fun provideCreateBookingUseCase(
        repository: BookingRepository
    ): CreateBookingUseCase = CreateBookingUseCase(repository)

    @Provides
    @Singleton
    fun provideGetBookingDetailUseCase(
        repository: BookingRepository
    ): GetBookingDetailUseCase = GetBookingDetailUseCase(repository)

    @Provides
    @Singleton
    fun provideCreatePaymentUseCase(
        repository: PaymentRepository
    ): CreatePaymentUseCase = CreatePaymentUseCase(repository)

}