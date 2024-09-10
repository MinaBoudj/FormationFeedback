 ### STAGE 1: Build ###
FROM container-registry.charlie.cnieg.fr/proxy.docker.io/node:16 As builder

ARG NO_PROXY=*.cnieg.fr
ARG HTTP_PROXY=http://i64apt01.iegp.edfgdf.fr:8080
ARG HTTPS_PROXY=http://i64apt01.iegp.edfgdf.fr:8080

WORKDIR /app
COPY . .
COPY .npmrc ./

RUN npm install && npm run build

### STAGE 2: Run ###
FROM container-registry.charlie.cnieg.fr/proxy.docker.io/nginx:1

ENV BACKEND_URL=formation-back
ENV SECURITY_OAUTH2_REALM_BASEURL=https://login-intranet.cnieg.fr

RUN rm -rf /usr/share/nginx/html/* && rm -rf /etc/nginx/conf.d/*

COPY --from=builder /app/dist/formation-front /usr/share/nginx/html

COPY /config/default.conf /etc/nginx/conf.d/default.conf
COPY /config/entrypoint.sh .
RUN chmod +x entrypoint.sh
ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 80
