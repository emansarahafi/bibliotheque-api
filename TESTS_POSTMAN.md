# Tests Postman pour l'API Emprunt

## Configuration

- Base URL: `http://localhost:8080`
- Content-Type: `application/json`

## Endpoints disponibles

### 1. Créer un emprunt (POST /api/emprunts)

**URL:** `POST http://localhost:8080/api/emprunts`

**Body (JSON):**

```json
{
    "dateEmprunt": "2025-08-10",
    "dateRetour": "2025-08-20",
    "statut": "EN_COURS",
    "livre": {
        "id": 1
    },
    "emprunteur": "Jean Dupont"
}
```

**Tests de validation à effectuer:**

#### Test 1: Emprunt valide

```json
{
    "dateEmprunt": "2025-08-10",
    "dateRetour": "2025-08-20",
    "statut": "EN_COURS",
    "livre": {
        "id": 1
    },
    "emprunteur": "Jean Dupont"
}
```

**Résultat attendu:** HTTP 201 Created

#### Test 2: Date d'emprunt dans le futur (INVALIDE)

```json
{
    "dateEmprunt": "2025-12-01",
    "dateRetour": "2025-12-15",
    "statut": "EN_COURS",
    "livre": {
        "id": 1
    },
    "emprunteur": "Jean Dupont"
}
```

**Résultat attendu:** HTTP 400 Bad Request avec message d'erreur de validation

#### Test 3: Date de retour dans le passé (INVALIDE)

```json
{
    "dateEmprunt": "2025-08-10",
    "dateRetour": "2025-08-05",
    "statut": "EN_COURS",
    "livre": {
        "id": 1
    },
    "emprunteur": "Jean Dupont"
}
```

**Résultat attendu:** HTTP 400 Bad Request

#### Test 4: Statut invalide (INVALIDE)

```json
{
    "dateEmprunt": "2025-08-10",
    "dateRetour": "2025-08-20",
    "statut": "INVALIDE",
    "livre": {
        "id": 1
    },
    "emprunteur": "Jean Dupont"
}
```

**Résultat attendu:** HTTP 400 Bad Request

#### Test 5: Champs obligatoires manquants (INVALIDE)

```json
{
    "dateEmprunt": "2025-08-10",
    "statut": "EN_COURS"
}
```

**Résultat attendu:** HTTP 400 Bad Request

### 2. Récupérer tous les emprunts (GET /api/emprunts)

**URL:** `GET http://localhost:8080/api/emprunts`

### 3. Récupérer un emprunt par ID (GET /api/emprunts/{id})

**URL:** `GET http://localhost:8080/api/emprunts/1`

### 4. Récupérer les emprunts en retard (GET /api/emprunts/retard)

**URL:** `GET http://localhost:8080/api/emprunts/retard`

### 5. Mettre à jour un emprunt (PUT /api/emprunts/{id})

**URL:** `PUT http://localhost:8080/api/emprunts/1`

### 6. Supprimer un emprunt (DELETE /api/emprunts/{id})

**URL:** `DELETE http://localhost:8080/api/emprunts/1`

## Validation des contraintes

L'API valide automatiquement :

- ✅ `dateEmprunt` : doit être dans le passé ou aujourd'hui (`@PastOrPresent`)
- ✅ `dateRetour` : doit être dans le futur (`@Future`)
- ✅ `statut` : doit être "EN_COURS", "TERMINE" ou "RETARD" (`@Pattern`)
- ✅ `livre` : ne peut pas être null (`@NotNull`)
- ✅ `emprunteur` : ne peut pas être vide (`@NotBlank`)

## Réponses d'erreur attendues

En cas d'erreur de validation, l'API retourne une réponse JSON structurée :

```json
{
    "timestamp": "2025-08-10T16:30:00",
    "status": 400,
    "error": "Validation Failed",
    "message": "dateEmprunt: La date d'emprunt doit être dans le passé ou aujourd'hui | statut: Le statut doit être EN_COURS, TERMINE ou RETARD",
    "path": "/api/emprunts"
}
```
