<h1 align="center">Cinema21</h1>

<p align="center">
   <a href="https://github.com/RuitiariGibson/Cinema21/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png"/></a>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
</p>
<p align="center">  
Cinema21 is a small demo application which consumes the api [tmdb api] (https://api.themoviedb.org) & uses it to show case the popular movies + shows as well as the trending shows & movies.<br>This project is based on modern android application tech-stacks that is, it is built using the repository & mvvm pattern as well as clean architecture components.  In addition, the project uses hilt for dependency injection.<br>
</p>
</br>
<p align="center">

<img src="https://github.com/RuitiariGibson/Cinema21/blob/master/preview/preview_4.png"/>
<br> <img src="https://github.com/RuitiariGibson/Cinema21/blob/master/preview/preview_1.png"/> </br>
<br> <img src="https://github.com/RuitiariGibson/Cinema21/blob/master/preview/preview_5.png"/> </br>
<br> <img src="https://github.com/RuitiariGibson/Cinema21/blob/master/preview/preview_6.png"/> </br>
</p>

## Build System
 [Gradle](https://gradle.org/)

## Prerequisite
```
Before running the project create a [tmdb project](https://api.themoviedb.org) 
and replace the **api-key**  with yours in the `gradle.properties` file for a successful build 
```
## Architecture

The Application is split into a two layer architecture:
- Presentation
- Data
-Di
The data layer contains the remote data sourc & the repositories. The remote data source is responsible for fetching data which then propagates the data fetched to the
repositories (The shows repo & Movie Repo). The Presentation layer on the other hand is made up of fragments, activites & view models. The view models receive
data from the repositories, which then propagate the received data to the fragments & activities. The fragments & activities then present the data to the user.
The di layer contains all the modules needed by the app.
The repositories are provided to the view models by Di (Hilt) ,then the viewmodels are lazily evaluated and made available to the activities through dependency injection.


## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- Dagger-Hilt (alpha) simple standard way to do dependency injection.
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
- [recyclerview-animators](https://github.com/wasabeef/recyclerview-animators) - Recycler View Animations
- [Picasso](https://square.github.io/picasso/) -powerful image downloading and caching library for Android. Introduction. Images add much-needed context and visual flair to Android applications. 
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - logs HTTP request and response data.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Sandwich](https://github.com/skydoves/Sandwich) - construct lightweight http API response and handling error responses (Similar to rxKotlin but lightweight -;) ). 
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Glide](https://github.com/bumptech/glide)-for loading images efficiently.
- [WhatIf](https://github.com/skydoves/whatif) - checking nullable object and empty collections.
- [Timber](https://github.com/JakeWharton/timber) - for logging.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components for making beautiful ui and animations.



## Open API


Cinema21 using the [moviedb]https://api.themoviedb.org/) for constructing RESTful API.<br>
The movideb api service is for those of you interested in using their movie, TV show or actor images and/or data in your application. Their API is a system we provide for you and your team to programmatically fetch and use our data and/or images.

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/RuitiariGibson/MoviesApp/stargazers)__ for this repository. :star: <br>
And __[follow](https://github.com/RuitiariGibson)__ me for my next creations! 🤩

# License
```xml
Designed and developed by 2020 (ruitiari Gibson)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

