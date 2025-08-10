# Guide de dépannage - API Bibliothèque

## FAQ & Solutions aux problèmes courants

### ❌ Problème 1 : Les validations ne sont pas déclenchées

**Symptômes :**

- Les données invalides sont acceptées sans erreur
- Aucun message de validation n'apparaît
- Status HTTP 200/201 au lieu de 400

**Solutions :**

1. **Vérifier la présence de @Valid**

   ```java
   @PostMapping
   public ResponseEntity<Livre> createLivre(@Valid @RequestBody Livre livre) {
       // ...
   }
   ```

2. **S'assurer que spring-boot-starter-validation est dans pom.xml**

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-validation</artifactId>
   </dependency>
   ```

3. **Vérifier les imports des annotations**

   ```java
   import jakarta.validation.Valid;
   import jakarta.validation.constraints.*;
   ```

4. **Redémarrer l'application** après ajout de dépendances

### ❌ Problème 2 : Messages d'erreur non localisés

**Symptômes :**

- Messages d'erreur en anglais
- Messages par défaut peu informatifs
- Pas de personnalisation des messages

#### Solution : Créer un fichier messages.properties

**Localisation :** `/src/main/resources/messages.properties`

**Exemples de messages :**

```properties
NotBlank.livre.titre=Le champ 'titre' est requis
Size.livre.titre=Le titre doit contenir entre {min} et {max} caractères
Pattern.livre.isbn=L'ISBN doit respecter le format XXX-XXXXXXXXXX
```

**Utilisation dans les entités :**

```java
@NotBlank(message = "{NotBlank.livre.titre}")
private String titre;
```

### ❌ Problème 3 : Port 8080 déjà utilisé

**Symptômes :**

```text
Web server failed to start. Port 8080 was already in use.
```

**Solutions :**

```bash
# Tuer le processus sur le port 8080
lsof -ti:8080 | xargs kill -9

# Ou changer le port dans application.properties
server.port=8081
```

### ❌ Problème 4 : Erreur de connexion à la base de données

**Symptômes :**

```text
Access denied for user 'root'@'localhost'
```

**Solutions :**

1. Vérifier les credentials dans `application.properties`
2. S'assurer que MySQL est démarré
3. Créer la base de données si elle n'existe pas

### ❌ Problème 5 : JSON mal formaté dans les tests

**Symptômes :**

- Status HTTP 400 avec "JSON parse error"
- Impossible de parser le body de la requête

**Solutions :**

1. **Vérifier le Content-Type**

   ```bash
   curl -H "Content-Type: application/json"
   ```

2. **Valider le JSON** (utiliser un validateur JSON en ligne)

3. **Exemple de JSON valide pour Emprunt :**

   ```json
   {
     "dateEmprunt": "2025-08-10",
     "dateRetour": "2025-08-20",
     "statut": "EN_COURS",
     "livre": {"id": 1},
     "emprunteur": "Jean Dupont"
   }
   ```

### ❌ Problème 6 : Validation ne fonctionne que sur certains champs

**Symptômes :**

- Certains champs sont validés, d'autres non
- Validation incohérente

**Solutions :**

1. **Vérifier que toutes les annotations sont présentes**
2. **S'assurer que les imports sont corrects**
3. **Vérifier que @Valid est sur le bon paramètre**

### ✅ Tests de validation recommandés

**Checklist pour valider le fonctionnement :**

1. **Test validation réussie**

   ```bash
   curl -X POST http://localhost:8080/api/emprunts \
        -H "Content-Type: application/json" \
        -d '{"dateEmprunt":"2025-08-10","dateRetour":"2025-08-20","statut":"EN_COURS","livre":{"id":1},"emprunteur":"Jean"}' \
        -w "\nStatus: %{http_code}\n"
   ```

   **Résultat attendu :** Status 201

2. **Test validation échouée**

   ```bash
   curl -X POST http://localhost:8080/api/emprunts \
        -H "Content-Type: application/json" \
        -d '{"dateEmprunt":"2025-12-01","dateRetour":"2025-08-20","statut":"INVALIDE","livre":{"id":1},"emprunteur":""}' \
        -w "\nStatus: %{http_code}\n"
   ```

   **Résultat attendu :** Status 400 avec messages d'erreur

### 🔧 Commandes utiles

```bash
# Redémarrer l'application
mvn spring-boot:run

# Compiler sans démarrer
mvn compile

# Nettoyer et recompiler
mvn clean compile

# Vérifier les processus sur le port 8080
lsof -i :8080

# Tester les endpoints
curl -X GET http://localhost:8080/api/livres
curl -X GET http://localhost:8080/api/emprunts
```

### 📝 Logs utiles

**Activer les logs de validation en ajoutant dans application.properties :**

```properties
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
```
