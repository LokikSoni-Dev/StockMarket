# StockMarket Application shows the list of stocks and stock info with chart.
- User Interface built with [Jetpack Compose.](https://developer.android.com/jetpack/compose)
- A single-activity architecture, using [Navigation Compose.](https://developer.android.com/jetpack/compose/navigation)
- A **presentation layer** that contains a Compose screen (View) and a **ViewModel** per screen (or feature).
- Reactive UIs using [Flow](https://developer.android.com/kotlin/flow) and [coroutines](https://kotlinlang.org/docs/coroutines-overview.html) for asynchronous operations.
- A **data layer** with a repository and two data sources (local using [Room](https://developer.android.com/jetpack/androidx/releases/room?gclid=CjwKCAjwkYGVBhArEiwA4sZLuH7gOfhjBTBR4Ovqhhjg7Haet66ErgjAWjMutx9iLNRpHnaXU4jIIRoCi_YQAvD_BwE&gclsrc=aw.ds) and a [Remote](https://www.alphavantage.co/documentation/)).
- Dependency injection using [Hilt.](https://developer.android.com/training/dependency-injection/hilt-android)
- A **Domain layer** for business logic.
- **CSV parsing** to download and show stock data.
- [Chart Library](https://github.com/Madrapps/plot) build for compose to show stock data.

# Screenshots
![List of Stocks](https://user-images.githubusercontent.com/107148566/172708507-63b06eae-05b7-46a8-89e3-357a525cec42.jpg)
![Searching Stock](https://user-images.githubusercontent.com/107148566/172709164-30f32078-69f6-4963-bd39-2f2ad05988c9.jpg)
![Stock Info](https://user-images.githubusercontent.com/107148566/172709178-fd7d9c80-a929-4c33-beee-047237b9b246.jpg)
