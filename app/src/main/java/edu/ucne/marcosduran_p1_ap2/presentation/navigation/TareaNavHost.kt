package edu.ucne.marcosduran_p1_ap2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import edu.ucne.marcosduran_p1_ap2.presentation.tarea.TareaScreen

@Composable
fun SystemNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.SystemList
    ) {
        composable<Screen.SystemList> {
            TareaListScreen (
                goToTarea = {
                    navHostController.navigate(Screen.tarea(it))
                },
                createTarea = {
                    navHostController.navigate(Screen.tarea(0))
                }
            )
        }

        composable<Screen.System> {
            val args = it.toRoute<Screen.System>()
            TareaScreen  (
                tareaId = args.tareaId,
                goBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}
