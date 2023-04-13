# BeerBox

A demo app that shows a paginated list of beers fetched from the [Punk API](https://punkapi.com/documentation/v2), with search and filter capabilities.
The app let you see the beers in a paginated list with infinite scrolling, search for a specific beer name, see the latest offers and filter the beers by malt with dedicated chip buttons.

Project Structure
-----------------
The project is structured using [MVVM](https://developer.android.com/jetpack/guide) with [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html), to enforce separation of concerns and provide scalability.

The code is separated in 3 modules:
* `app` contains the UI related classes, like Activities, ViewModels and Composables.
* `domain` contains the business logic, with Use Cases that implements data flow.
* `data` contains the data Sources, Models and Repositories.

Libraries Used
--------------
* [Core](https://developer.android.com/jetpack/androidx/releases/core) for the SplashScreen API
* [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) for lifecycle-aware ViewModel classes
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection
* [Compose](https://developer.android.com/jetpack/compose) as the UI toolkit
* [Accompanist](https://google.github.io/accompanist/) to change system bars colors
* [Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) for data paging
* [Coil](https://coil-kt.github.io/coil/) for image loading
* [Retrofit](https://square.github.io/retrofit/) as the HTTP client

<a href="https://www.flaticon.com/free-icons/beer" title="beer icons">Beer icons created by Freepik - Flaticon</a>
