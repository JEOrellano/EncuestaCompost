package com.example.encuestacompost.ui.survey

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.encuestacompost.entidad.enumsEntidad.EAntiguedad
import com.example.encuestacompost.entidad.enumsEntidad.EConforme
import com.example.encuestacompost.entidad.enumsEntidad.EEdad
import com.example.encuestacompost.entidad.enumsEntidad.EEstudio
import com.example.encuestacompost.entidad.enumsEntidad.EHora_Semanal
import com.example.encuestacompost.entidad.enumsEntidad.ERelacion_Contractual
import com.example.encuestacompost.entidad.enumsEntidad.ERubro
import com.example.encuestacompost.entidad.enumsEntidad.ESalario
import com.example.encuestacompost.entidad.enumsEntidad.ESexo
import com.example.encuestacompost.entidad.enumsEntidad.ETrabajo
import kotlinx.coroutines.launch

@Composable
fun SurveyScreen(viewModel: SurveyViewModel) {
    Box(Modifier.fillMaxSize()) {
        Survey(
            Modifier.align(Alignment.Center),
            viewModel,
            LocalContext.current
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SurveyScreenPreview() {
    SurveyScreen(SurveyViewModel(LocalContext.current))
}

@Composable
fun Survey(
    modifier: Modifier,
    viewModel: SurveyViewModel,
    current: Context
) {
    val textoPrueba: String by viewModel.textoPrueba.observeAsState(initial = "")
    val rbp1: Boolean by viewModel.rbp1.observeAsState(initial = true)
    val spp2: ESexo by viewModel.spp2.observeAsState(initial = ESexo.MASCULINO)
    val spp3: EEstudio by viewModel.spp3.observeAsState(initial = EEstudio.PRIMARIA)
    val spp4: EEdad by viewModel.spp4.observeAsState(initial = EEdad.MENOR_DE_18)
    val spp5: ETrabajo by viewModel.spp5.observeAsState(initial = ETrabajo.UNO)
    val rbp6: Boolean by viewModel.rbp1.observeAsState(initial = true)
    val spp7: ERelacion_Contractual by viewModel.spp7.observeAsState(initial = ERelacion_Contractual.RELACION_DE_DEPENDENCIA)
    val spp8: ERubro by viewModel.spp8.observeAsState(initial = ERubro.TECNICA)
    val spp9: EHora_Semanal by viewModel.spp9.observeAsState(initial = EHora_Semanal.MENOR_DE_10)
    val spp10: EAntiguedad by viewModel.spp10.observeAsState(initial = EAntiguedad.MENOS_DE_1_ANIO)
    val spp11: ESalario by viewModel.spp11.observeAsState(initial = ESalario.MENOS_DE_300000)
    val spp12: EConforme by viewModel.spp12.observeAsState(initial = EConforme.SI)

    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        LazyColumn(modifier = modifier) {
            item {
                TextoPruebaField(textoPrueba)
                TextoLabelP("Pregunta 1")
                RadioGroupP1(
                    rbp1 = rbp1,
                    onRbp1Change = {
                        viewModel.onSurveyChanged(
                            it,
                            spp2,
                            spp3,
                            spp4,
                            spp5,
                            rbp6,
                            spp7,
                            spp8,
                            spp9,
                            spp10,
                            spp11,
                            spp12
                        )
                    }
                )

                TextoLabelP("Pregunta 2")
                SpinnerP2(
                    spp2 = spp2,
                    onSpp2Change = {
                        viewModel.onSurveyChanged(
                            rbp1,
                            it,
                            spp3,
                            spp4,
                            spp5,
                            rbp6,
                            spp7,
                            spp8,
                            spp9,
                            spp10,
                            spp11,
                            spp12
                        )
                    }
                )

                TextoLabelP("Pregunta 3")
                SpinnerP3(
                    spp3 = spp3,
                    onSpp3Change = {
                        viewModel.onSurveyChanged(
                            rbp1,
                            spp2,
                            it,
                            spp4,
                            spp5,
                            rbp6,
                            spp7,
                            spp8,
                            spp9,
                            spp10,
                            spp11,
                            spp12
                        )
                    }
                )

                TextoLabelP("Pregunta 4")
                SpinnerP4(
                    spp4 = spp4,
                    onSpp4Change = {
                        viewModel.onSurveyChanged(
                            rbp1,
                            spp2,
                            spp3,
                            it,
                            spp5,
                            rbp6,
                            spp7,
                            spp8,
                            spp9,
                            spp10,
                            spp11,
                            spp12
                        )
                    }
                )

                TextoLabelP("Pregunta 5")
                SpinnerP5(
                    spp5 = spp5,
                    onSpp5Change = {
                        viewModel.onSurveyChanged(
                            rbp1,
                            spp2,
                            spp3,
                            spp4,
                            it,
                            rbp6,
                            spp7,
                            spp8,
                            spp9,
                            spp10,
                            spp11,
                            spp12
                        )
                    }
                )

                TextoLabelP("Pregunta 6")
                RadioGroupP6(
                    rbp6 = rbp6,
                    onRbp6Change = {
                        viewModel.onSurveyChanged(
                            rbp1,
                            spp2,
                            spp3,
                            spp4,
                            spp5,
                            it,
                            spp7,
                            spp8,
                            spp9,
                            spp10,
                            spp11,
                            spp12
                        )
                    }
                )

                TextoLabelP("Pregunta 7")
                SpinnerP7(
                    spp7 = spp7,
                    onSpp7Change = {
                        viewModel.onSurveyChanged(
                            rbp1,
                            spp2,
                            spp3,
                            spp4,
                            spp5,
                            rbp6,
                            it,
                            spp8,
                            spp9,
                            spp10,
                            spp11,
                            spp12
                        )
                    }
                )

                TextoLabelP("Pregunta 8")
                SpinnerP8(
                    spp8 = spp8,
                    onSpp8Change = {
                        viewModel.onSurveyChanged(
                            rbp1,
                            spp2,
                            spp3,
                            spp4,
                            spp5,
                            rbp6,
                            spp7,
                            it,
                            spp9,
                            spp10,
                            spp11,
                            spp12
                        )
                    }
                )

                TextoLabelP("Pregunta 9")
                SpinnerP9(
                    spp9 = spp9,
                    onSpp9Change = {
                        viewModel.onSurveyChanged(
                            rbp1,
                            spp2,
                            spp3,
                            spp4,
                            spp5,
                            rbp6,
                            spp7,
                            spp8,
                            it,
                            spp10,
                            spp11,
                            spp12
                        )
                    }
                )

                TextoLabelP("Pregunta 10")
                SpinnerP10(
                    spp10 = spp10,
                    onSpp10Change = {
                        viewModel.onSurveyChanged(
                            rbp1,
                            spp2,
                            spp3,
                            spp4,
                            spp5,
                            rbp6,
                            spp7,
                            spp8,
                            spp9,
                            it,
                            spp11,
                            spp12
                        )
                    }
                )

                TextoLabelP("Pregunta 11")
                SpinnerP11(
                    spp11 = spp11,
                    onSpp11Change = {
                        viewModel.onSurveyChanged(
                            rbp1,
                            spp2,
                            spp3,
                            spp4,
                            spp5,
                            rbp6,
                            spp7,
                            spp8,
                            spp9,
                            spp10,
                            it,
                            spp12
                        )
                    }
                )

                TextoLabelP("Pregunta 12")
                SpinnerP12(
                    spp12 = spp12,
                    onSpp12Change = {
                        viewModel.onSurveyChanged(
                            rbp1,
                            spp2,
                            spp3,
                            spp4,
                            spp5,
                            rbp6,
                            spp7,
                            spp8,
                            spp9,
                            spp10,
                            spp11,
                            it
                        )
                    }
                )

                SurveyBotton(rbp1) {
                    coroutineScope.launch {
                        viewModel.onSurveySelected(current)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerP12(spp12: EConforme, onSpp12Change: (EConforme) -> Unit) {
    val list = EConforme.entries
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(list[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "¿Cree que su salario es justo en comparación con el de sus colegas de diferente género que realizan el mismo trabajo?")
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            TextField(
                value = selectedText.descripcion,
                onValueChange = { },
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                list.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.descripcion) },
                        onClick = {
                            selectedText = item
                            isExpanded = false
                            onSpp12Change(item)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerP11(spp11: ESalario, onSpp11Change: (ESalario) -> Unit) {
    val list = ESalario.entries
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(list[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "¿Cuál es su rango de salario mensual bruto?")
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            TextField(
                value = selectedText.descripcion,
                onValueChange = { },
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                list.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.descripcion) },
                        onClick = {
                            selectedText = item
                            isExpanded = false
                            onSpp11Change(item)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerP10(spp10: EAntiguedad, onSpp10Change: (EAntiguedad) -> Unit) {
    val list = EAntiguedad.entries
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(list[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "¿Hace cuánto tiempo se desempeña en esta tarea?")
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            TextField(
                value = selectedText.descripcion,
                onValueChange = { },
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                list.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.descripcion) },
                        onClick = {
                            selectedText = item
                            isExpanded = false
                            onSpp10Change(item)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerP9(spp9: EHora_Semanal, onSpp9Change: (EHora_Semanal) -> Unit) {
    val list = EHora_Semanal.entries
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(list[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "¿Cuántas horas semanales dedica a esta tarea?")
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            TextField(
                value = selectedText.descripcion,
                onValueChange = { },
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                list.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.descripcion) },
                        onClick = {
                            selectedText = item
                            isExpanded = false
                            onSpp9Change(item)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerP8(spp8: ERubro, onSpp8Change: (ERubro) -> Unit) {
    val list = ERubro.entries
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(list[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "¿En que rubro trabaja?")
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            TextField(
                value = selectedText.descripcion,
                onValueChange = { },
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                list.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.descripcion) },
                        onClick = {
                            selectedText = item
                            isExpanded = false
                            onSpp8Change(item)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerP7(spp7: ERelacion_Contractual, onSpp7Change: (ERelacion_Contractual) -> Unit) {
    val list = ERelacion_Contractual.entries
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(list[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "¿Qué tipo de relación contractual tiene con su trabajo?")
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            TextField(
                value = selectedText.descripcion,
                onValueChange = { },
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                list.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.descripcion) },
                        onClick = {
                            selectedText = item
                            isExpanded = false
                            onSpp7Change(item)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}

@Composable
fun RadioGroupP6(rbp6: Boolean, onRbp6Change: (Boolean) -> Unit) {
    val radioOptions = listOf("Si", "No")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
// Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(Modifier.selectableGroup()) {
        Text(text = "¿Percibe remuneración por su trabajo?")
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                            onRbp6Change("Si" == text)
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null // null recommended for accessibility with screenreaders
//                    onClick = {onRbp1Change(text == "Si")}
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerP5(spp5: ETrabajo, onSpp5Change: (ETrabajo) -> Unit) {
    val list = ETrabajo.entries
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(list[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Edad")
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            TextField(
                value = selectedText.descripcion,
                onValueChange = { },
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                list.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.descripcion) },
                        onClick = {
                            selectedText = item
                            isExpanded = false
                            onSpp5Change(item)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerP4(spp4: EEdad, onSpp4Change: (EEdad) -> Unit) {
    val list = EEdad.entries
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(list[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Edad")
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            TextField(
                value = selectedText.descripcion,
                onValueChange = { },
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                list.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.descripcion) },
                        onClick = {
                            selectedText = item
                            isExpanded = false
                            onSpp4Change(item)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerP3(spp3: EEstudio, onSpp3Change: (EEstudio) -> Unit) {
    val list = EEstudio.entries
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(list[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "¿Qué nivel de estudios alcanzó?")
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            TextField(
                value = selectedText.descripcion,
                onValueChange = { },
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                list.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.descripcion) },
                        onClick = {
                            selectedText = item
                            isExpanded = false
                            onSpp3Change(item)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}

@Composable
fun TextoPruebaField(textoPrueba: String) {
    Text(
        text = textoPrueba,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TextoLabelP(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun RadioGroupP1(rbp1: Boolean, onRbp1Change: (Boolean) -> Unit) {
    val radioOptions = listOf("Si", "No")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
// Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(Modifier.selectableGroup()) {
        Text(text = "¿Esta trabajando actualmente?")
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                            onRbp1Change("Si" == text)
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null // null recommended for accessibility with screenreaders
//                    onClick = {onRbp1Change(text == "Si")}
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerP2(spp2: ESexo, onSpp2Change: (ESexo) -> Unit) {
    val list = ESexo.entries
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(list[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sexo")
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            TextField(
                value = selectedText.descripcion,
                onValueChange = { },
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                list.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.descripcion) },
                        onClick = {
                            selectedText = item
                            isExpanded = false
                            onSpp2Change(item)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}

@Composable
fun SurveyBotton(rbp1: Boolean, onSurveySelected: () -> Unit) {
    Button(
        onClick = { onSurveySelected() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        ),
        enabled = rbp1
    ) {
        Text(text = "FINALIZAR")
    }
}
