# Android Weather App

This project is a demo using a mix of frameworks in Android

## Technology used
* Kotlin

## Frameworks used:
* Kotlin co-routines
* Retrofit
* Dagger2
* Android Jetpack
** Room
** Navigation
** LiveData
** Design libraries
* Glide
* RxJava2

## Testing
The application is covered by a representation of unit test that verify the correct functionality of databases, network and the logic
The test are split on:
* Unit test
** 'app.network/test': Verify the network layer is created and can parser the expected jsons
** 'app.database/androidTest': Verify the database layer is created, can insert values and read them, verify that data conflicts works as expected
** 'app/test': Simple unit tests to cover the logic of the application, also verify the dependencies are created and injected correctly
** 'app/androidTest': Simple integration test using MockWebServer, verify only a happy path of the application.

## Reports
On this application has been added a serial of plugins that help to understand and identify issues on the application
We use the next plugins
* DexCount: help to identify with libraries use more methods to identify and reduce the weight of the application if get close of the 65k methods limit
* Jacoco: Run all the test on the application and generate a report with the code coverage
* CheckStyle: Help to use the same code style and keep the code clean

## TODO
* Cities list are fetch from database, this is only a small sample of the openweather bulk data, we need to update it to use google locations
* Create an error handle when the server side returns an error, currently the application is set only for a happy path, there are no negative test at the moment
* Add analytic tools as Firebase
* Add security dependency checks as Snyk or OWASP
  