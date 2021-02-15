FROM node:15.3.0-alpine3.10 AS build

WORKDIR /app

COPY ./package.json ./package.json
COPY ./package-lock.json ./package-lock.json
RUN npm install

COPY . .
RUN npm run build


FROM bunkerity/bunkerized-nginx:1.2.2

COPY --from=build /app/build /www
