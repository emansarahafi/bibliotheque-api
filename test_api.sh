#!/bin/bash

echo "=== Test de l'API Emprunt - Validation ==="
echo ""

# Configuration
BASE_URL="http://localhost:8080/api"

echo "1. Test d'abord avec l'API Livres pour avoir des données..."
echo "   Création d'un livre de test..."

# Créer un livre pour les tests d'emprunt
curl -X POST "${BASE_URL}/livres" \
     -H "Content-Type: application/json" \
     -d '{
       "titre": "Livre de Test",
       "auteur": "Auteur Test",
       "isbn": "123-4567890123",
       "anneePublication": 2020
     }' \
     -w "\nStatus Code: %{http_code}\n\n"

echo "2. Test Emprunt VALIDE..."
curl -X POST "${BASE_URL}/emprunts" \
     -H "Content-Type: application/json" \
     -d '{
       "dateEmprunt": "2025-08-10",
       "dateRetour": "2025-08-20",
       "statut": "EN_COURS",
       "livre": {"id": 1},
       "emprunteur": "Jean Dupont"
     }' \
     -w "\nStatus Code: %{http_code}\n\n"

echo "3. Test Emprunt INVALIDE - Date d'emprunt dans le futur..."
curl -X POST "${BASE_URL}/emprunts" \
     -H "Content-Type: application/json" \
     -d '{
       "dateEmprunt": "2025-12-01",
       "dateRetour": "2025-12-15",
       "statut": "EN_COURS",
       "livre": {"id": 1},
       "emprunteur": "Jean Dupont"
     }' \
     -w "\nStatus Code: %{http_code}\n\n"

echo "4. Test Emprunt INVALIDE - Statut incorrect..."
curl -X POST "${BASE_URL}/emprunts" \
     -H "Content-Type: application/json" \
     -d '{
       "dateEmprunt": "2025-08-10",
       "dateRetour": "2025-08-20",
       "statut": "INVALIDE",
       "livre": {"id": 1},
       "emprunteur": "Jean Dupont"
     }' \
     -w "\nStatus Code: %{http_code}\n\n"

echo "5. Test Emprunt INVALIDE - Champs manquants..."
curl -X POST "${BASE_URL}/emprunts" \
     -H "Content-Type: application/json" \
     -d '{
       "dateEmprunt": "2025-08-10",
       "statut": "EN_COURS"
     }' \
     -w "\nStatus Code: %{http_code}\n\n"

echo "6. Récupération de tous les emprunts..."
curl -X GET "${BASE_URL}/emprunts" \
     -H "Content-Type: application/json" \
     -w "\nStatus Code: %{http_code}\n\n"

echo "=== Tests terminés ==="
