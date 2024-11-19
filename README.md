# DatabasesAndroidStudio

Este repositorio contiene un proyecto desarrollado en **Android Studio** que demuestra el uso de bases de datos en aplicaciones móviles Android. Aquí se explican los detalles del proyecto, cómo configurarlo y ejecutarlo.

## Características

- Implementación de bases de datos locales en Android.
- CRUD.
- Uso de **SQLite** para almacenamiento.
- Diseño modular y fácilmente extensible.
- Interfaz de usuario simple e intuitiva.

## Requisitos

- **Android Studio** 
- JDK 8 o superior

## Dependencias
```gradle
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
