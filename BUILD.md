# Build & Run

Приложение написано с использованием java 17.

* [Build & Run](#build--run)
    * [Local](#local)
    * [Docker](#docker)
        * [Build](#build)
        * [Build & Export](#build--export)
        * [Compose](#compose)
        * [Deploy](#deploy)
            * [Первый запуск](#первый-запуск)
            * [Повторный запуск](#повторный-запуск)

## Local

Сборку можно выполнить командой:

```shell
mvn clean install
```

Приложение запускается следующей командой:

```shell
java -jar .\target\AjaxTest-0.0.1-SNAPSHOT.jar
```

## Docker

### Build

Для создания `docker image` можно воспользоваться следующей командой:

```shell
docker build --no-cache -t ajaxtest .
```

Чтоб не запоминать команду и не напутать с параметрами, в проекте есть скрипт с этой
командой: [build-helper](build-helper).

```shell
./build-helper
```

### Build & Export

Также, т.к. мы не используем какой-либо репозиторий для контейнеров есть
скрипт [build-and-export-helper](build-and-export-helper) который также собирает
новый `docker image` и сохраняет `image + docker-compose.yml` в архив `/target/ajaxtest-image-and-compose.tar.gz`
который дальше можно перемещать на другую машину.

```shell
./build-and-export-helper
```

### Compose

[docker-compose.yml](docker-compose.yml) файл определяет, какие контейнеры будут запущены и с какими параметрами.

### Deploy

#### Первый запуск

Для разворачивания на сервере в первый раз нужно выполнить следующую процедуру:

1. Создать директорию `/var/lib/ajaxtest` для хранения всех файлов приложения и дальнейшей работы:
    ```shell
    mkdir /var/lib/ajaxtest
    ```

2. Скопировать архив с приложением из пункта [Build & Export](#build--export) в папку `/var/lib/ajaxtest`.

3. Распаковать архив:
   ```shell
   tar -xzvf ajaxtest-image-and-compose.tar.gz
   ```
4. Импортировать `image` в `docker`:
   ```shell
   docker load < ajaxtest-image.tar
   ```
5. Запустить приложение с нужным профилем через `docker compose`:
   ```shell
   docker compose --profile PROFILE_NAME up -d
   ```
   Доступные профили: `default`, подробности в [Compose](#compose).

6. Радоваться что все получилось.

#### Повторный запуск

Для обновления приложения необходимо выполнить пункты `2.`, `3.`, `4.`, `5.`, `6.`.

```shell
# 2. Переместить новый архив в директорию /var/lib/ajaxtest
 
# 3. Распаковать архив
tar -xzvf ajaxtest-image-and-compose.tar.gz

# 4. Импортировать image
docker load < ajaxtest-image.tar

# 5. Запустить docker-compose
docker compose --profile PROFILE_NAME up -d

# 6. 🥳🎉
```