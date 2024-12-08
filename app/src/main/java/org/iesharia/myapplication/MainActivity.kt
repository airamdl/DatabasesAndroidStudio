package org.iesharia.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.iesharia.myapplication.ui.theme.MyApplicationTheme



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                ) { innerPadding ->
                    MainActivity (
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun MainActivity(modifier: Modifier) {
    val context = LocalContext.current
    val db = DBHelper(context)
    var lId:String by remember { mutableStateOf("ID") }
    var lName:String by remember { mutableStateOf("Nombre") }
    var lAge:String by remember { mutableStateOf("Edad") }

    Column (
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Base de Datos",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
        Text(
            text = "Muuuuuy simple\nNombre/Edad",
            fontSize = 10.sp

        )
        var idValue by remember { mutableStateOf("") }
        OutlinedTextField(
            value = idValue,
            onValueChange = {
                idValue = it
            },
            modifier = Modifier,
            textStyle = TextStyle(color = Color.DarkGray),
            label = { Text(text = "ID") },
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )
        //Nombre
        var nameValue by remember { mutableStateOf("") }
        OutlinedTextField(
            value = nameValue,
            onValueChange = {
                nameValue = it
            },
            modifier = Modifier,
            textStyle = TextStyle(color = Color.DarkGray),
            label = { Text(text = "Nombre") },
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )
        //Edad
        var ageValue by remember { mutableStateOf("") }
        OutlinedTextField(
            value = ageValue,
            onValueChange = {
                ageValue = it
            },
            modifier = Modifier,
            textStyle = TextStyle(color = Color.DarkGray),
            label = { Text(text = "Edad") },
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )
        var bModifier:Modifier = Modifier.padding(5.dp)
        Row {
            Button(
                modifier = bModifier,
                onClick = {

                    val id = idValue
                    val name = nameValue
                    val age = ageValue

                    db.addName(id, name, age)

                    Toast.makeText(
                        context,
                        name + " adjuntado a la base de datos",
                        Toast.LENGTH_LONG
                    )
                        .show()

                    nameValue = ""
                    ageValue = ""
                }
            ) {
                Text(text = "Añadir")
            }
            Button(
                modifier = bModifier,
                onClick = {
                    lId = "ID"
                    lName = "Nombre"
                    lAge = "Edad"
                    val db = DBHelper(context, null)

                    val cursor = db.getName()

                    cursor!!.moveToFirst()
                    lId += "\n" + cursor.getString(cursor.getColumnIndex(DBHelper.ID_COL))
                    lName += "\n" + cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl))
                    lAge += "\n" + cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL))

                    while (cursor.moveToNext()) {
                        lId += "\n" + cursor.getString(cursor.getColumnIndex(DBHelper.ID_COL))
                        lName += "\n" + cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl))
                        lAge += "\n" + cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL))
                    }

                    cursor.close()
                }
            ) {
                Text(text = "Mostrar")
            }
        }
        Row {
            Button(
                modifier = bModifier,
                onClick = {

                    val id = idValue
                    val name = nameValue


                    db.deleteName(id)

                    Toast.makeText(
                        context,
                        name + " eliminado de la base de datos",
                        Toast.LENGTH_LONG)
                        .show()

                    nameValue = ""
                    ageValue = ""
                }
            ) {
                Text(text = "Eliminar")
            }
            Button(
                modifier = bModifier,
                onClick = {
                    val id = idValue
                    val newName = nameValue
                    val newAge = ageValue

                    val updatedRows = db.updateName(id, newName, newAge)

                    if (updatedRows > 0) {
                        Toast.makeText(
                            context,
                            "Nombre actualizado correctamente",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Modifica algun campo a actualizar",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    nameValue = ""
                    ageValue = ""
                }
            ) {
                Text(text = "Actualizar")
            }
        }
        Row {
            Text(
                modifier = bModifier,
                text = lId
            )
            Text(
                modifier = bModifier,
                text = lName
            )
            Text(
                modifier = bModifier,
                text = lAge
            )
        }

    }
}
