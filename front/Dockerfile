FROM node:15.3.0-alpine3.10 AS build

WORKDIR /app

COPY ./package.json ./package.json
COPY ./package-lock.json ./package-lock.json

RUN npm install

COPY . .

RUN npm run build

FROM nginx:1.19.6-alpine

COPY --from=build /app/build /usr/share/nginx/html
