# Gallery-App

### Structure of the code ###
Simple Android Application written in Kotlin.
This project follows Clean Architecture with MVVM with Clean Architecture Design


# Main libraries used


- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Rxjava](https://github.com/ReactiveX/RxJava) - For asynchronous and more..
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding) - Used for data binding 
  - [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) - Used it for the navigation from one fragment to another fragments
  - [Paging 3.0](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) -to load large amount of data in chunks instead to load all the records in one api call. Paging 3.0 will automatically call the api to laod more data when user reached to end of the screen.
  - [Room](https://developer.android.com/reference/androidx/room/package-summary) -Used for local data storage
- [Dependency Injection](https://developer.android.com/training/dependency-injection)
  - [Dagger2](https://dagger.dev/) - Standard library to incorporate Dagger dependency injection into an Android application. 
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Glide](https://bumptech.github.io/glide/) - Used for loading images
- [Sdp Library](https://github.com/intuit/sdp) -Used for auto Layout for different layouts
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
- [Timber](https://github.com/JakeWharton/timber) -Used for loggging 
- [Junit](https://junit.org/) - For Unit Testing
- [Mockito](https://github.com/mockito/mockito) - For mocking in Unit Testing



# Architecture Design
![alt text](https://github.com/mookyjan/GalleryApp/blob/main/diagram/android_clean_architecture_onion.png)

![alt text](https://github.com/mookyjan/GalleryApp/blob/main/diagram/clean-mvvm.png)

![alt text](https://github.com/mookyjan/GalleryApp/blob/main/diagram/architecure.png)

![alt text](https://github.com/mookyjan/GalleryApp/blob/main/diagram/mvvm%20architecture.png)



# Modules


* `data/` : contains the code to access to the data (repository pattern)
* `domain/` : contains the business logic and the usecases
* `app` : Presentation layer, contains the UI 
* `buildSrc/`: Common module for the libraries dependencies 

this project consist of Two screen. on the first screen showing the Images list and on clicking the image
go to the details of the image.
When user scroll more images are loaded from server 
Swipe to Refresh can be used to force refresh data.
Screen rotation has been handle
and for the simplicity of this project many things have been kept simple
like 
* ErrorHandling, 
* Internet connectivity and 
* Design of the app is also kept sample and can be improved much more

comments are written with the function that what it will do.

also TODO are given in the area which we can improve more.


# Screenshots
![alt text](https://github.com/mookyjan/GalleryApp/blob/main/screenshots/screenshot%20(1).png)

![alt text](https://github.com/mookyjan/GalleryApp/blob/main/screenshots/screenshot%20(2).png)

![alt text](https://github.com/mookyjan/GalleryApp/blob/main/screenshots/screenshot%20(5).png)

![alt text](https://github.com/mookyjan/GalleryApp/blob/main/screenshots/screenshot%20(7).png)




# TODO
adding more unit test cases
improving the design
clean up the code 
setup for ktlint s
working on to Implement the same project in coroutine





