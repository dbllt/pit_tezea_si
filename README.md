# pit_tezea_si

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
