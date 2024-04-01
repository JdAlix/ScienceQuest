# On part de l'image long term service (en alpine pour le poids)
FROM node:lts-alpine

# Installe http-server (simple serveur pour contenu statique)
RUN npm install -g http-server

# On se rends dans (et on créé) le dossier /front
WORKDIR /front

# Copie du projet node dans le dossier /projet
COPY --chown=node:node science-quest/ .

# Met en variable d'environnement le port 443 pour http-server
ENV PORT=443

# Installe les dépendances nécéssaires 
RUN npm install

# Lance la construction du projet
RUN npm run build

# Expose le port 443
EXPOSE 443

# Lance le serveur sur le dossier dist (le projet est build dedans)
CMD ["http-server", "dist"]
