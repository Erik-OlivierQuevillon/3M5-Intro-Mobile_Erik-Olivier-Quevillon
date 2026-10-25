# Évaluation formative — Partie papier

**Automne 2026 - Introduction à la programmation mobile (3M5)**

---

## Question 1 - Types et valeurs (4 points)

Soit le code suivant :

```kotlin
val a = 5 + 2 * 2
val b = 5.5f * 2
val c = (0 % 2) == 2
val d = "Bonjour " + 9
```

Pour chaque variable, indiquez **la valeur** obtenue et **le type** inféré par Kotlin.
(1 point par ligne complète et exacte.)

| Variable | Valeur | Type |
| -------- | ------ | ---- |
| `a`      |        |      |
| `b`      |        |      |
| `c`      |        |      |
| `d`      |        |      |

---

## Question 2 - Trace d'exécution (4 points)

Le programme ci-dessous est lancé avec un seul argument : `2`.
Rédigez la **trace d'exécution** complète : les instructions réellement exécutées, dans l'ordre, avec leurs effets et la pile d'appels.

```kotlin
fun main(args: Array<String>) {
    val a = args[0].toInt()
    val b = factorielle(a)
    println("La factorielle de " + a + " est " + b + ".")
}

fun factorielle(n: Int): Int {
    var res = 1
    for (i in 1..n) {
        res *= i
    }
    return res
}
```

Critères d'évaluation :

- **2 points** - Seules les instructions réellement exécutées apparaissent, et elles sont dans le bon ordre.
- **2 points** - Les effets de chaque instruction et l'état de la pile d'appels sont correctement décrits.

Espace de réponse :

```













































```

---

## Question 3 — Plan de test (3 points)

Soit la fonction suivante :

```kotlin
fun verificationNomUtilisateur(nom: String): Boolean {
    val charSpecial = ".*[!@#\$%^&*()\\-+].*".toRegex()

    if (charSpecial.containsMatchIn(nom)) {
        throw IllegalArgumentException(
            "Le nom d'utilisateur ne peut pas contenir de caractères spéciaux"
        )
    }

    return true
}
```

Rédigez un plan de test comportant **3 cas distincts** : un cas normal, un cas limite et un cas invalide.
Pour chaque cas, précisez la valeur passée en paramètre et le résultat attendu.
(1 point par cas complet et pertinent.)

| Type de cas | Description du cas |      Entrée      | Résultat attendu |
| ----------- | ------------------ | ---------------- | ---------------- |
| Normal      |                    |                  |                  |
| Limite      |                    |                  |                  |
| Invalide    |                    |                  |                  |





