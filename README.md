# DatabasesAndroidStudio

Este repositorio contiene un proyecto desarrollado en **Android Studio** que demuestra el uso de bases de datos en aplicaciones móviles Android. Aquí se explican los detalles del proyecto, cómo configurarlo y ejecutarlo.

## Características

- Implementación de bases de datos locales en Android.
- CRUD.
- Uso de **SQLite** para almacenamiento.
- Diseño modular y fácilmente extensible.
- Interfaz de usuario simple e intuitiva.

## Creación de la base de datos
```gradle
    class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "MyDatabase.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_AGE INTEGER
            )
        """
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}

## Operaciones CRUD
Insertar datos
```gradle
    fun insertData(name: String, age: Int): Long {
    val db = this.writableDatabase
    val contentValues = ContentValues().apply {
        put(COLUMN_NAME, name)
        put(COLUMN_AGE, age)
    }
    return db.insert(TABLE_NAME, null, contentValues)
}


Leer datos

```gradle
    fun getAllData(): Cursor {
    val db = this.readableDatabase
    return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
}


Actualizar Datos
```gradle
    fun updateData(id: Int, name: String, age: Int): Int {
    val db = this.writableDatabase
    val contentValues = ContentValues().apply {
        put(COLUMN_NAME, name)
        put(COLUMN_AGE, age)
    }
    return db.update(TABLE_NAME, contentValues, "$COLUMN_ID = ?", arrayOf(id.toString()))
}


Borrar datos
```gradle
    fun deleteData(id: Int): Int {
    val db = this.writableDatabase
    return db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
}


   
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
