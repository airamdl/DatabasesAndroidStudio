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
        class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory? = null) :
            SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
        
            override fun onCreate(db: SQLiteDatabase) {
        
                val query = ("CREATE TABLE " + TABLE_NAME + " ("
                        + ID_COL + " INTEGER PRIMARY KEY, " +
                        NAME_COl + " TEXT," +
                        AGE_COL + " TEXT" + ")")
        
                db.execSQL(query)
            }
        
            override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
                onCreate(db)
            }
               companion object{
                private val DATABASE_NAME = "nombres"
        
                private val DATABASE_VERSION = 1
        
                val TABLE_NAME = "name_table"
        
                val ID_COL = "id"
        
                val NAME_COl = "nombre"
        
                val AGE_COL = "edad"
            }
```
## Operaciones CRUD
Insertar datos
```gradle
         fun addName(id: String, name: String, age: String){

        val values = ContentValues()

        values.put(ID_COL, id)
        values.put(NAME_COl, name)
        values.put(AGE_COL, age)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)

        db.close()
    }
```

Leer datos

```gradle
         fun getName(): Cursor? {

        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
            }
    
```

Actualizar Datos

```gradle
        fun updateName(id: String, newName: String, newAge: String): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME_COl, newName)
        values.put(AGE_COL, newAge)

        val selection = "$ID_COL = ?"
        val selectionArgs = arrayOf(id)

        val updatedRows = db.update(TABLE_NAME, values, selection, selectionArgs)
        db.close()
        return updatedRows
    }
```

Borrar datos
```gradle
         fun deleteName(id: String): Int {
        val db = this.writableDatabase
        val selection = "$ID_COL = ?"
        val selectionArgs = arrayOf(id)

        val deletedRows = db.delete(TABLE_NAME, selection, selectionArgs)
        db.close()
        return deletedRows

    }
```

   
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
```
