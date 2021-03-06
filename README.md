AndroidDemo
===========

Demo app to show how to start an Android App. This demo shows:

1. How to create a Fragment and associate with Activity.
2. Make Asynchronous call using SafeAsyncTask.
3. Decouple SafeAsyncTask with Otto Bus.
4. Replace Roboguice for Guice 3.0.
5. Connect to Foursquare's API using Retrofit with OkHttp and parse Json with Gson.
6. Material Design with latest AppCompat library (version 21) and animations like Google+ and Google Play.
7. Side Bar implementation.
8. RecyclerView implementation with onClickListener, View Types and OnScrollListener.
9. Use of GPS.
10. Persists data with Qachee.
11. Use of Square Otto to use an event bus.
12. Use of Proguard to reduce apk size.
13. Two ways to handle release keystore and it values to create a Release APK (ready for Google Play).

It also handle HTTP errors in a generic way (see FoursquareAsyncTask.java) and use GPSTracker to handle Geo locations.

**UPDATED TO WORK WITH MATERIAL DESIGN**

Instructions
============

1. Just Clone the git repo
2. Import the "AndroidDemoApp" project into your Android Studio IDE.
4. DONE


System Requirements 
============

1. Android Studio 0.9.0
2. Gradle 2.1+
3. Android Gradle plugin version 0.14.0
4. Minimun Android SDK 14
5. Target Android SDK 21


Developed By
================

* Nicolas Jafelle - <nicolasjafelle@gmail.com>


License
================

    Copyright 2013 Nicolas Jafelle

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
