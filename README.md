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
