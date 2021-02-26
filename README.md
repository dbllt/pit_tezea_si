# PIT TEZEA SI

PIT TEZEA SI is an IT management app for TEZEA.


## Deployement

Start the whole application inside Docker (access at port 80, http):

```
docker-compose up --build
```

Starting the frontend separately in order to have live reload (access frontend at port 3000, backend at port 8080):

```
cd back/
docker-compose up --build -d
cd ../front
npm install
npm start
```

Start the whole application inside Docker (access at port 80/443, https):

```
docker-compose up -f docker-compose.production.yml --build
```

## Authentication

### Development

You can find an example file with http requests

`/test/authentication.rest`

### Production

Set Environment variable **APPLICATION_SECRET** with a secret.

You can generate a secret in a terminal using node :

```
$ node
> require('crypto').randomBytes(64).toString('hex')
```

## Authors

* **Dorian BOUILLET**
* **Sigrid DROOGH**
* **Nils RICHARD**
* **Mehdi AIT OUALI**
* **Thomas GUZIK**
* **Djamel BOUATMANE**
* **Maxime BEUCHER**
* **Alexis GAGOUD**
* **Théo JAMMES-BEUVE**

## Informations sur le projet

- Questions réponses avec le client : https://docs.google.com/document/d/1gbnKbhvlVFYuwoJiLHHVJ-RoDDTHbnxX16Kuw3qcFZc/edit?usp=sharing
- Spécifications des besoins client : https://docs.google.com/document/d/1aDL3acSm12unzHBQrcEsRBGM48mqHAF67EK8ELwp_DA/edit
- Diapo de soutenance : https://docs.google.com/presentation/d/1CyCq6M6sbabxgRNivIWOH5C1MjcOlNcspJwJ38ILHSU/edit



