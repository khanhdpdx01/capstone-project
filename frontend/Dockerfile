# FROM node:16.17-alpine3.15 as build-stage
# WORKDIR /app
# COPY package*.json ./
# RUN npm install
# COPY ./ .
# RUN npm run build

# FROM nginx:1.20.0-alpine as production-stage

# RUN apk add --update coreutils

# # Add a user how will have the rights to change the files in code
# RUN addgroup -g 1500 nginxusers 
# RUN adduser --disabled-password -u 1501 nginxuser nginxusers 

# RUN mkdir /app
# COPY --from=build-stage /app/dist /app
# COPY nginx.conf /etc/nginx/nginx.conf

# WORKDIR /
# COPY docker_entrypoint.sh .
# COPY generate_env-config.sh .
# RUN chown nginxuser:nginxusers docker_entrypoint.sh
# RUN chown nginxuser:nginxusers generate_env-config.sh
# RUN chmod 777 docker_entrypoint.sh generate_env-config.sh
# RUN chown -R nginxuser:nginxusers /app
# RUN chown -R nginxuser:nginxusers /etc/nginx
# RUN chown -R nginxuser:nginxusers /tmp
# RUN chmod 777 /app
# RUN chmod 777 /tmp
# RUN chmod 777 /etc/nginx
    
# USER nginxuser
 
# EXPOSE 9093:9093
# CMD ["/bin/sh","docker_entrypoint.sh"]

FROM node:16.17-alpine3.15 as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY ./ .
CMD ["npm", "run", "serve"]

# FROM nginx:1.20.0-alpine as production-stage

# RUN apk add --update coreutils

# # Add a user how will have the rights to change the files in code
# RUN addgroup -g 1500 nginxusers 
# RUN adduser --disabled-password -u 1501 nginxuser nginxusers 

# RUN mkdir /app
# COPY --from=build-stage /app/dist /app
# COPY nginx.conf /etc/nginx/nginx.conf

# WORKDIR /
# COPY docker_entrypoint.sh .
# COPY generate_env-config.sh .
# RUN chown nginxuser:nginxusers docker_entrypoint.sh
# RUN chown nginxuser:nginxusers generate_env-config.sh
# RUN chmod 777 docker_entrypoint.sh generate_env-config.sh
# RUN chown -R nginxuser:nginxusers /app
# RUN chown -R nginxuser:nginxusers /etc/nginx
# RUN chown -R nginxuser:nginxusers /tmp
# RUN chmod 777 /app
# RUN chmod 777 /tmp
# RUN chmod 777 /etc/nginx
    
# USER nginxuser
 
# EXPOSE 9093:9093
# CMD ["/bin/sh","docker_entrypoint.sh"]