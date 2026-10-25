package com.example.composetapelelapin

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetapelelapin.ui.theme.ComposeTapeLeLapinTheme
import kotlin.random.Random
import kotlin.random.nextInt


class MainActivity : ComponentActivity() { // On déclare une classe MainActivity qui hérite de la classe ComponentActivity.
    // MainActivity représente l'activité (ou la page) principale affichée au démarrage de l'application.

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) { // On redéfinit la méthode onCreate() héritée ComponentActivity.
        // onCreate : méthode appelée par Android lorsque l’activité est créée. C’est l’endroit habituel pour préparer l’interface et les données initiales.

        super.onCreate(savedInstanceState) // Appelle la version de onCreate de la classe parente, ComponentActivity.

        enableEdgeToEdge() // Méthode définie dans ComponentActivity qui s'occupe de l'affichage par défaut de notre interface.
        // Active l’affichage edge-to-edge : ton interface peut s’étendre derrière la barre d’état en haut et la barre de navigation en bas.

        setContent { // Contient les éléments à afficher dans l'interface de l'application. Remplace le fichier XML traditionnel.

            ComposeTapeLeLapinTheme {
                // TODO : À quoi sert ComposeTapeLeLapinTheme{} ?
                // Applique la palette de couleurs définie à tous les éléments placés à l’intérieur (voir Theme.kt).

                Scaffold(
                    // TODO : À quoi sert Scaffold() ?
                    // Scaffold : Fonction qui permet de hiérarchiser les différents composants de l'interface graphique de l'application
                    // Scaffold peut organiser une barre du haut, une barre du bas, un bouton flottant et
                    // le contenu principal. Ici, seuls la barre du haut et le contenu principal sont utilisés.

                    modifier = Modifier.fillMaxSize(),
                    // TODO : Qu'est-ce qu'un Modifier ?
                    // Modifier : Objet qui sert à configurer l'apparence ou la disposition d'un "composable" (élément Compose)
                    // fillMaxSize() demande à Scaffold d’occuper toute la largeur et toute la hauteur disponibles à l’écran.

                    topBar = {
                        TopAppBar(
                            // Une TopAppBar peut contenir un titre, une icône de navigation et des actions;
                            // ici, elle contient seulement un titre.
                            title = {
                                Text(
                                    text = "Tape le lapin \uD83D\uDC07 \uD83D\uDD28",
                                    color = Color(0xFF571703),
                                    fontSize = 30.sp,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Bold,
                                )
                                    },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                // primaryContainer est une couleur définie par le thème de l’application,
                                // souvent une variante plus douce de la couleur principale
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                                // onPrimaryContainer est choisie pour être lisible sur primaryContainer

                                // TODO : Où changer le thème de mon application ? > ui.theme > Theme.kt
                                // TODO : Avec IA, produire une nouvelle palette de couleurs (Light theme et Dark theme)
                                // TODO : Tester le dark theme (Settings > Dark theme)
                                // TODO : PRENDRE L'HABITUDE D'EXÉCUTER APRÈS CHAQUE MODIFICATION !!
                                // Color.kt : les couleurs nommées et leurs codes hexadécimaux
                                // Theme.kt : les palettes claires et sombres


                            )
                        )
                    }
                ) {
                    // Fonction lambda de contenu principal
                    innerPadding ->
                    // TODO : À quoi sert innerPadding ?
                    // innerPadding est une valeur de padding fournie automatiquement par Scaffold().
                    // Elle représente l’espace à éviter, dans notre cas la taille de TopAppBar
                    // Android recommande de l’appliquer au contenu principal pour éviter qu’il soit caché sous la barre.

                    EcranPrincipal(
                        modifier = Modifier.padding(innerPadding)
                        // Initialise un nouveau Modifier (plutôt que de prendre celui du Scaffold)
                        // On applique un padding. L'Écran principal va occuper [espace disponible - innerPadding]
                        // Ce Modifier sera utilisé dans Column

                        // TODO : Supprimer l'utilisation du padding afin de mieux comprendre son importance
                        // Résultat : l’écran principal commence sous la TopAppBar plutôt que derrière elle.
                    )
                }
            }
        }
    }
}

@Composable
fun EcranPrincipal(modifier: Modifier) { // TODO : Le "= Modifier" est-il nécessaire ?

    // Déclaration de deux variables. Utilisées dans AffichageScore() et GrilleTuiles()
    // Déclarer nbPafs comme un état modifiable. Permet de rafraîchir l'application à chaque fois que sa valeur est modifiée.
    // TODO : Qu'est-ce qu'un état ? Variable qui provoque la MÀJ de l'interface lorsque sa valeur est modifiée. **SERA APPRONFONDI PLUS TARD**
    // TODO : Pourquoi utiliser un état plutôt qu'une variable globale ? Parce que la variable globale n'entraînera pas la MÀJ de l'interface graphique en temps réel. Le score restera donc à 0.
    var nbPafs by remember{ mutableIntStateOf(0) } // TODO : remember : Le programme doit se rappeler en permanence de l'état de nbPafs. Sans remember{} l'état de nbPafs serait remis à 0 après chaque modification
    var nbFlops by remember{ mutableIntStateOf(0) }


    // TODO : Où déclarer ses états ? Dans la fonction "la plus haute dans la hiérarchie" qui a besoin de la variable.
    // TODO **Défi pour les étudiants** : Afficher la position du lapin sous le titre
    var positionLapin by remember{ mutableIntStateOf(Random.nextInt(9) ) } // .nextInt(9) : les index de la grille vont de 0 à 8


    Column(
        // TODO : Modifier vs modifier. Tester la différence ici.
        // modifier : fait appel au paramètre modifier de la fonction dans laquelle on se trouve. Ici, modifier a été défini plus haut : modifier = Modifier.padding(innerPadding)
        // Modifier : Crée un nouveau Modifier
        modifier = modifier.fillMaxSize()

        // TODO : À quoi sert Column() ?
        // Permet d'organiser le contenu selon une séquence verticale (haut vers bas) : Score, Titre, Grille de boutons

    ) {
        AffichageScore(
            nbPafs = nbPafs,
            nbFlops = nbFlops,
            modifier=Modifier.fillMaxWidth().weight(.2f) // le poids doit être spécifié en Float
        )
        TitreApplication(
            modifier=Modifier.fillMaxWidth().weight(.2f), // occupe 0.2f/1.4f de l'interface
            positionLapin = positionLapin
        )
        GrilleTuiles(
            incrementePafs = {nbPafs++}, // TODO : On utilise des fonctions lambdas pour modifier nbPafs, car le passage par valeur de la variable nbPafs ne modifierait que la copie créée et non la variable nbPafs définie plus haut
            incrementeFlops = {nbFlops++},
            positionLapin = positionLapin,
            reInitPositionLapin = {positionLapin = Random.nextInt(9)},
            modifier = Modifier.fillMaxSize().weight(1f) // occupe 1.0f/1.4f de l'interface
        )
    }





}

@Composable
private fun AffichageScore(
    nbPafs: Int,
    nbFlops: Int,
    modifier: Modifier // AffichageScore possède son propre modifier. Il est défini par : modifier=Modifier.fillMaxWidth().weight(.2f)
    // On veut que l'affichage occupe toute la largeur de l'écran et une fraction (0.2/1.4) de la hauteur.
    // TODO : Pourquoi 0.2f ? Obtenu par essai-erreur. Pas de règle universelle.
    // TODO : Pourquoi 1.4f ? Correspond à la fraction totale : 0.2 (Score) + 0.2 (Titre) + 1.0 (Grille)

) {
    // TODO : À quoi sert Row() ?
    // Permet d'organiser le contenu selon une séquence horizontale (gauche vers droite) : Flaps, Flops
    Row(
        modifier = modifier, // On utilise modifier=Modifier.fillMaxWidth().weight(.2f)
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        // TODO : Les composables Box() sont-elles nécessaires ici ?
        // Alternative :
        // 1) Voir les paramètres de Text() pour en savoir plus.
        // 2) Rechercher TextStyle.textAlign dans la doc. Android
        // 3) Définir le modifier & l'alignement des composable Text()
        Text(
                text = "$nbPafs Pafs",
                modifier = Modifier.fillMaxWidth().weight(1f), // ratio : 1f/2f
                color = Color(0xFF2D5B0A), // Personnaliser sa couleur : Mettre un hex code connu et changer manuellement avec l'outil de couleur
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
        )

        Text(
                text = "$nbFlops Flops",
                modifier = Modifier.fillMaxWidth().weight(1f), // ratio 1f/2f
                color = Color(0xFFA80404),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
        )


    }
}



@Composable
private fun TitreApplication(modifier: Modifier, positionLapin: Int) {

    // TODO : Le composable Box() est-il nécessaire ici ? (bis)

    // TODO : Ai-je besoin d'un Row() si je n'ai qu'un seul élément ?

    // TODO : ** quoi faire si on rencontre un black screen ? **
    // Device Manager > Medium Phone > Wipe Data
    Column(
        modifier = modifier
    ){
        Text(
            text = "Tape le lapin",
            modifier = modifier, // On utilise modifier=Modifier.fillMaxWidth().weight(.2f)
            color = Color(0xFF571703),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Postion du lapin : L: ${positionLapin/3} C: ${positionLapin%3}",
            modifier = modifier, // On utilise modifier=Modifier.fillMaxWidth().weight(.2f)
            color = Color(0xFFFF3700),
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center

            )
    }




}


@Composable
private fun GrilleTuiles(
    incrementePafs: ()->Unit,
    incrementeFlops: ()->Unit,
    positionLapin: Int,
    reInitPositionLapin:()->Unit,
    modifier: Modifier) {

    val context = LocalContext.current

    Column(
        modifier = modifier // correspond à Modifier.fillMaxSize().weight(1f). Donc Column() occupe 1f/1.4f de l'écran
        // TODO : Tester modifier = Modifier

        // TODO : Quelle est l'utilité de la Column() ? Testez sans Column().
    ) {
        for (i in 0..2) {
            Row(
                modifier = modifier // correspond à Modifier.fillMaxSize().weight(1f). Donc chaque Row() a un poids de 1f/3f de la Column(), qui elle occupe 1f/1.4f de l'écran
            )
            {
                for (j in 0..2) {
                    var indexTuile = i*3 + j // les index de la grille vont de 0 à 8
                    Tuile(
                        estLapin = indexTuile == positionLapin,
                        quandOnCliqueSurBouton = {estLapin ->
                            effetCliqueTuile(
                                estLapin = estLapin,
                                incrementePafs = incrementePafs,
                                incrementeFlops = incrementeFlops,
                                reInitPositionLapin = reInitPositionLapin,
                                context = context
                            )
                        },
                        modifier = Modifier.padding(6.dp).fillMaxSize().weight(1f)
                    )
                }
            }
        }


    }
}

@Composable
private fun Tuile( // Tuile a 3 paramètres : un booléen, une fonction qui prend un booléen et retourne rien, un modifier
    estLapin: Boolean,
    quandOnCliqueSurBouton: (Boolean)-> Unit,
    modifier: Modifier
) {

    // TODO : Types de boutons (https://developer.android.com/develop/ui/compose/components/button?hl=fr)
    ElevatedButton(
        //
        onClick = {
            quandOnCliqueSurBouton(estLapin)
        },
        modifier = modifier,
    ) {
        // TODO : Afficher "Lapin" d'une autre couleur
        if(estLapin){
            Text(
                text = "\uD83D\uDC30", // Emoji lapin
                // color = Color(0xFFFF3700),
                fontSize = 60.sp
            )
        }else{
            Text(
                text = "\uD83D\uDD73\uFE0F", // Emoji trou
                // color = Color(0xFF521F0F),
                fontSize = 60.sp
            )

        }

    }
}



fun effetCliqueTuile(
    estLapin: Boolean,
    incrementePafs: () -> Unit,
    incrementeFlops: () -> Unit,
    reInitPositionLapin: () -> Unit,
    context : Context
){
    if(estLapin){
        incrementePafs()
        reInitPositionLapin()
        Toast.makeText(context, "PAF", Toast.LENGTH_SHORT).show() // https://developer.android.com/guide/topics/ui/notifiers/toasts?hl=fr
    }else{
        incrementeFlops()
    }

}




