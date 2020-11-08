#!/bin/bash
echo "----------------Démarrage tests Back----------------"
echo "Changement du profil de l'application Tempo-back à test"
. ./changeProfile.sh "test"
echo "Changement du profil à test réussi !"

echo -e "\n\n----------------------------------------------------"
echo "------------------Tests Back Postman----------------"
echo -e "----------------------------------------------------\n\n"

echo "Changement du profil de l'application Tempo-back à dev"
. ./changeProfile.sh "dev"
echo "Changement du profil à dev réussi !"
echo "-------------------Fin tests Back-------------------"
