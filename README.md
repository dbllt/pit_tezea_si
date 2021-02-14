# pit_tezea_si


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

### Deployement

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

