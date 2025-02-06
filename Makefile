.PHONY: *

# The first command will be invoked with `make` only and should be `build`
build:
	./mvnw verify -Pformat

all: clean build

ci:
	./mvnw clean verify

clean:
	./mvnw clean

format:
	./mvnw test-compile -Pformat

run:
	java -jar app/target/app-*.jar

update:
	./mvnw wrapper:wrapper versions:update-parent versions:update-properties versions:use-latest-versions

yolo:
	./mvnw install -DskipTests -Dquality.skip
