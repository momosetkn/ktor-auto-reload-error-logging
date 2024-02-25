# description

This project is reproduction of Ktor becoming unresponsive when a plugin errors during auto-reload.

# launch ktor

```bash
./gradlew build
./gradlew -t compilekotlin
```

execute below command in another console 

```bash
./gradlew run
```

its work well.

```bash
curl localhost:8080
```

# auto-reload

when you change the code, It becomes unresponsive, like below. and nothing logging.

```bash
curl localhost:8080
# curl: (52) Empty reply from server
```
