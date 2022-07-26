### BUILD STAGE
# Image source
FROM node:16.15-alpine as build
# Init Settings
ARG BUILD_PATH="/usr/src/app"
# Docker working directory
WORKDIR $BUILD_PATH
# Copying file into APP directory of docker
COPY package*.json ${BUILD_PATH}
# Then install the NPM module
RUN npm install
# Copy current directory to APP folder
COPY . $BUILD_PATH
# Build Project
RUN npm run build

### PROD STATGE
# Image source
FROM node:16.15-alpine as real
# Docker working directory
ARG PROD_PATH="/usr/prod"
WORKDIR $PROD_PATH
# Copying file into APP directory of docker
# COPY package*.json ${PRODPATH}
# 
# RUN mkdir -p ./src/etc/exports
# VOLUME [ "/usr/src/app/src/etc/exports" ]
# COPY --from=build . /usr/prod/    # 빌드의 모든 결과가 복사된다. (리눅스 전체가 복사됨)
COPY --from=build /usr/src/app ${PROD_PATH}
# 
EXPOSE 3000
# 
CMD ["sh", "-c", "node dist/api/api.app main-api"]
# CMD ["ls", "-laF"]
# CMD ["sh"]