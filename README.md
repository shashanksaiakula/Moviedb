# TMDB Popular People Android App

## Overview
This Android application fetches and displays popular people from **The Movie Database (TMDB) API** using **MVVM architecture** and **Jetpack Compose**. It shows actor details like profile picture, name, popularity, and known movies.

## Features
- Fetches a list of popular people from TMDB.
- Displays actor details including name, profile picture, popularity, and known movies.
- Uses **Jetpack Compose** for UI.
- Implements **Retrofit** for API calls.
- Uses **Coil** for image loading.
- Follows **MVVM architecture**.

## Technologies Used
- **Kotlin**
- **Jetpack Compose**
- **Retrofit (API calls)**
- **Coil (Image Loading)**
- **LiveData & ViewModel (MVVM Architecture)**

## Setup Instructions
### 1. Clone the Repository
```sh
git clone https://github.com/shashanksaiakula/Moviedb
cd Moviedb
```

### 2. Get an API Key from TMDB
- Create an account at [TMDB](https://www.themoviedb.org/)
- Navigate to **API settings** and generate an API key.
- Add the API key in your project.

### 3. Add Dependencies in `build.gradle`
```gradle
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
implementation 'io.coil-kt:coil-compose:2.0.0'
implementation 'androidx.paging:paging-compose:3.3.5'
```

### 4. Configure API Service
Modify `ApiService.kt`:
```kotlin
interface ApiService {
    @GET("person/popular")
    suspend fun getPopularPeople(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): PopularPeopleResponse
}
```

### 5. Run the App
```sh
./gradlew build
```
or click **Run ▶** in Android Studio.

## Screenshots
![list of popular people (1)](https://github.com/user-attachments/assets/05b4ae6f-195f-4b29-b333-cfe0f9ed2f01)
![details of a person](https://github.com/user-attachments/assets/47d3370f-a102-4dd1-ab54-03f3a06ef7e9)
![list of popular people (2)](https://github.com/user-attachments/assets/0b82e58b-f024-41f4-adb4-9f4f0236deb0)




